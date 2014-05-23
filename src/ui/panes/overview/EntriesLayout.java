package ui.panes.overview;

import java.awt.*;

import javax.swing.*;

import ui.panes.entries.ExpenseEntry;

public class EntriesLayout extends JPanel{
	GridBagConstraints grid = new GridBagConstraints();
	
	public EntriesLayout(){
		
		//Set layout and initial grid C position
		setLayout(new GridBagLayout());
		grid.gridx = 0;
		grid.gridy = 0;
		grid.weightx = 1;
		grid.weighty = 1;
		grid.anchor = GridBagConstraints.NORTH;
		
		//Add first component
		ExpenseEntry g = new ExpenseEntry();
		g.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		add(g, grid);
		
	}

}
