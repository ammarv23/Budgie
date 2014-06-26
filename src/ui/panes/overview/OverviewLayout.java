package ui.panes.overview;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.*;

import ui.panes.FrameController;
import ui.panes.outputs.AbstractOutput;
import ui.panes.outputs.ExpensesOutput;
import ui.panes.outputs.IncomeOutput;
import ui.panes.outputs.ProfitOutput;
import ui.panes.outputs.SavingsOutput;

@SuppressWarnings("serial")
public class OverviewLayout extends JPanel {
	float expenseAmt;
	private UpdateManager u;

	public OverviewLayout(){
		setLayout(new GridBagLayout());
		GridBagConstraints grid = new GridBagConstraints();
		grid.weightx = 1;
		grid.weighty = 1;
		grid.anchor = GridBagConstraints.CENTER;
		JLabel title = new JLabel("Overview");

		add(title, grid);

		grid.gridy = 1;
		grid.insets = new Insets(0,20,0,20);
		grid.fill = GridBagConstraints.BOTH;
		//add profit/loss overview
		ExpensesOutput e1 = new ExpensesOutput();
		IncomeOutput e2 = new IncomeOutput();
		SavingsOutput e3 = new SavingsOutput();
		ProfitOutput p = new ProfitOutput(e1.getTotal());

		//Update manager handles all updating when overview tab is switched to.
		u = new UpdateManager(e1,e2,e3,p);

		add(p, grid);

		grid.insets = new Insets(0,0,0,0); //reset insets

		//adding three expense trackers
		JPanel expenseOverview = new JPanel();
		expenseOverview.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;

		gbc.gridx = 0;
		expenseOverview.add(e1, gbc);

		gbc.gridx = 1;
		expenseOverview.add(e2, gbc);

		gbc.gridx = 2;
		expenseOverview.add(e3, gbc);

		grid.gridy++;
		grid.anchor = GridBagConstraints.SOUTH;
		grid.fill = GridBagConstraints.HORIZONTAL;

		add(expenseOverview, grid);

		//add Exit button
		JButton exit = new JButton("Exit");

		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				FrameController.switchFrames();
			}
		});

		grid.gridy++;
		grid.gridwidth = 1;
		grid.fill = GridBagConstraints.NONE;
		grid.anchor = GridBagConstraints.SOUTHEAST;

		add(exit,grid);

	}
		
}
