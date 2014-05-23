package ui.panes.outputs;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ExpensesOutput extends AbstractOutput{
	private JLabel fixed, variable, goal;
	
	@Override
	//populates the JPanel
	public void addContents() {
		fixed = new JLabel("Test1", SwingConstants.RIGHT);
		variable = new JLabel("Test2", SwingConstants.RIGHT);
		goal = new JLabel("Test3", SwingConstants.RIGHT);
		
		addInput("Fixed:", fixed);
		addInput("Variable:",variable);
		addInput("Goal:", goal);
		addTotal();
	}

	@Override
	protected Color getColour() {
		return Color.black;
	}

	@Override
	protected String getTitle() {
		return "Monthly Expenses";
	}

}
