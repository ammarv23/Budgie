package ui.panes.outputs;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.*;

@SuppressWarnings("serial")
public class ProfitOutput extends AbstractOutput{
	JLabel profit;


	@Override
	protected String getTitle() {
		return "Profit/Loss";
	}


	@Override
	protected Color getColour() {
		return Color.green;
	}


	@Override
	protected void addContents() {
		profit = new JLabel("profit");
		add(profit);
	}

}
