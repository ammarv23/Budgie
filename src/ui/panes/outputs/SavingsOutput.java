package ui.panes.outputs;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class SavingsOutput extends AbstractOutput{
	private JLabel eSavings, tfsa, rrsp;
	
	@Override
	//populates the JPanel
	public void addContents() {
		eSavings = new JLabel("Test1", SwingConstants.RIGHT);
		tfsa = new JLabel("Test2", SwingConstants.RIGHT);
		rrsp = new JLabel("Test3", SwingConstants.RIGHT);
		
		
		addInput("eSavings:", eSavings);
		addInput("TFSA:",tfsa);
		addInput("RRSP:", rrsp);
		addTotal();
	}

	@Override
	protected Color getColour() {
		return Color.yellow;
	}

	@Override
	protected String getTitle() {
		return "Monthly Savings";
	}

}
