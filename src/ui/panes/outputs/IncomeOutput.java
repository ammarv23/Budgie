package ui.panes.outputs;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class IncomeOutput extends AbstractOutput{
	private JLabel fixed, work, chequing;
	
	@Override
	//populates the JPanel
	public void addContents() {
		fixed = new JLabel("Test1", SwingConstants.RIGHT);
		work = new JLabel("Test2", SwingConstants.RIGHT);
		chequing = new JLabel("Test3", SwingConstants.RIGHT);
		
		
		addInput("Fixed:", fixed);
		addInput("Work:",work);
		addInput("Chequing:", chequing);
		addTotal();
	}

	@Override
	protected Color getColour() {
		return Color.blue;
	}

	@Override
	protected String getTitle() {
		return "Monthly Income";
	}

}
