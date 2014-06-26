package ui.panes.overview;

import java.awt.*;

import javax.swing.*;

import ui.panes.entries.AbstractEntry;
import ui.panes.entries.ExpenseEntry;
import ui.panes.entries.GoalEntry;
import ui.panes.entries.IncomeEntry;

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
		AbstractEntry g = new ExpenseEntry();
		add(g, grid);
		
		grid.gridy++;
		AbstractEntry g1 = new IncomeEntry();
		add(g1, grid);
		
		grid.gridy++;
		AbstractEntry g2 = new GoalEntry();
		add(g2, grid);
	}

}
