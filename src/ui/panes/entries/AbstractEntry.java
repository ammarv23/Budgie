package ui.panes.entries;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.border.Border;

import ui.displays.DebugPrinter;

public abstract class AbstractEntry extends JPanel{
	private GridBagConstraints gbc = new GridBagConstraints();
	private LinkedList<JLabel> entries = new LinkedList<JLabel>();
	private LinkedList<JComponent> data = new LinkedList<JComponent>();
	
	
	public AbstractEntry(){
		setLayout(new GridBagLayout());
		
		//defaults grid constraints
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		
		setEntries(entries, data);
		DebugPrinter.printDebug("Data contains " + data.size() + " elements");
		
		//adds all components and stretches the title to match the new component size
		gbc.gridy++;
	
		while(!entries.isEmpty()){
			JPanel s = new JPanel();
			s.setLayout(new BoxLayout(s, BoxLayout.Y_AXIS));
			Border paddingBorder = BorderFactory.createEmptyBorder(2,5,2,5);
			Border actualBorder;
			
			JLabel e = entries.pop();
			
			if (entries.size() >= 1) actualBorder = BorderFactory.createMatteBorder(0,0,0,1, Color.black);
			else actualBorder = BorderFactory.createMatteBorder(0,0,0,0, Color.black);
			
			//set dividing border
			
			s.add(e);
			
			JComponent c = data.pop();
			
			s.add(c);
			
			//c.setBorder(BorderFactory.createMatteBorder(1,0 , 0, 0, Color.black));
			
			Border usedBorder = BorderFactory.createCompoundBorder(actualBorder, paddingBorder);
			s.setBorder(usedBorder);
			
			add(s,gbc);
			
			gbc.gridx++;
			
		}
			
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		//place title with thick black border on upper panel
		JLabel title = new JLabel(getTitle(), JLabel.CENTER);
		title.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
		title.setOpaque(true);
		title.setBackground(getColor());
		//set insets
		gbc.gridwidth = entries.size();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(title, gbc);
		

		//add enter and clear butttons
		
		gbc.gridy = 2;
		JPanel buttonPanels = new JPanel();
		JButton enter, clear;
		
		enter = new JButton("Enter");
		clear = new JButton("Clear");
		
		enter.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				submit();
			}
			
		});
		
		clear.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				clear();
			}	
		});
		
		buttonPanels.add(enter);
		
		buttonPanels.add(clear);
		
		add(buttonPanels,gbc);
		
		setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		
	}
	
	protected void showError(String message){
		JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	protected void showInfo(String message){
		JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
	}

	protected abstract void setEntries(LinkedList<JLabel> entries2, LinkedList<JComponent> data2);

	protected abstract String getTitle();
	
	protected abstract Color getColor();
	
	protected abstract void submit();
	
	protected abstract void clear();
	
}
