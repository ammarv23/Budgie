package ui.panes.entries;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.border.Border;

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
			
			c.setBorder(BorderFactory.createMatteBorder(1,0 , 0, 0, Color.black));
			
			Border usedBorder = BorderFactory.createCompoundBorder(actualBorder, paddingBorder);
			s.setBorder(usedBorder);
			
			add(s,gbc);
			
			gbc.gridx++;
		}
		
		
		
/*		while(!entries.isEmpty()){
			JLabel e = entries.pop();
			Border paddingBorder = BorderFactory.createEmptyBorder(5,10,5,10);
			Border actualBorder;
			
			if (entries.size() >= 1) actualBorder = BorderFactory.createMatteBorder(0,0,0,1, Color.black);
			else actualBorder = BorderFactory.createMatteBorder(0,0,0,0, Color.black);
			
			Border usedBorder = BorderFactory.createCompoundBorder(actualBorder, paddingBorder);
			e.setBorder(usedBorder);
			add(e, gbc);
			gbc.gridy++;
			
			//Add data components directly under the entries
			JComponent c = data.pop();
			c.setBorder(usedBorder);
			
			add(c, gbc);
			
			//set gbc to the next point
			gbc.gridx++;
			gbc.gridy--;
			
		}*/
		
		
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
		
	
		
	}

	protected abstract void setEntries(LinkedList<JLabel> entries2, LinkedList<JComponent> data2);

	protected abstract String getTitle();
	
	protected abstract Color getColor();
	
}
