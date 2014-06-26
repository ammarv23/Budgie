package ui.panes.outputs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.Border;

import ui.panes.FrameController;

@SuppressWarnings("serial")
public abstract class AbstractOutput extends JPanel{
	protected GridBagConstraints gbc = new GridBagConstraints();
	protected float total;
	
	public AbstractOutput() {
		setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		try {
			addContents();
		} catch (SQLException e) {
			showError(e.getLocalizedMessage());
		}
		
		Border line = BorderFactory.createLineBorder(getColour());
		Border title = BorderFactory.createTitledBorder(line, getTitle());
		setBorder(title);
		
		//total field
		
		
	}
	
	protected abstract String getTitle();

	protected abstract Color getColour();
	
	protected abstract void addContents() throws SQLException;
	
	protected void addInput(String label, JComponent comp){
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.ipady = 10;
		JLabel b = new JLabel(label);
		JSeparator j = new JSeparator(SwingConstants.HORIZONTAL);
		j.setPreferredSize(new Dimension(5,1));
		b.setAlignmentX(LEFT_ALIGNMENT);
		
		add(b, gbc);
		
		gbc.gridx++;
		add(comp,gbc);
		
		gbc.gridx--;
		gbc.gridy++;
		
		//add seperator below completed line
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(j,gbc);
		
		gbc.gridy++;
	}
	
	protected void addTotal(int... totalValues){
		JLabel total1 = new JLabel("Total:");
		total1.setFont(new Font("sansserif", Font.BOLD, 14));
		
		gbc.ipady = 0;
		add(total1, gbc);
		
		int sum = 0;
		
		for (int i: totalValues){
			sum += i;
		}
		
		JLabel totalVal = new JLabel("$ " + Integer.toString(sum), SwingConstants.RIGHT);
		totalVal.setFont(new Font("sansserif", Font.BOLD, 14));
		
		gbc.gridx++;
		
		total = sum;
		
		add(totalVal, gbc);
		
	}
	
	
	protected void showError(String message){
		JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
		System.exit(ERROR);
	}
	
	protected void showInfo(String message){
		JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public float getTotal(){
		return this.total;
	}
	
	public void setTotal(float total){
		this.total = total;
	}

	public boolean update(){
		//wipe the previous data.
		removeAll();
		System.out.println(this.getTitle() + " checking in");
		gbc.gridx = 0;
		gbc.gridy = 0;
		try {
			addContents();
		} catch (SQLException e) {
			showError(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return true;
	}


}
