package ui.panes.outputs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public abstract class AbstractOutput extends JPanel{
	private GridBagConstraints gbc = new GridBagConstraints();
	
	public AbstractOutput(){
		Border line = BorderFactory.createLineBorder(getColour());
		Border title = BorderFactory.createTitledBorder(line, getTitle());
		setBorder(title);
		
		setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		addContents();
		
		//total field
		
		
	}
	
	protected abstract String getTitle();

	protected abstract Color getColour();
	
	protected abstract void addContents();
	
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
	
	protected void addTotal(){
		JLabel total = new JLabel("Total:");
		total.setFont(new Font("sansserif", Font.BOLD, 14));
		
		gbc.ipady = 0;
		add(total, gbc);
		
	}

}
