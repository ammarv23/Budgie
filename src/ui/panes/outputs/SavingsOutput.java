package ui.panes.outputs;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ui.displays.DebugPrinter;
import db.DBInit;

@SuppressWarnings("serial")
public class SavingsOutput extends AbstractOutput{
	private JLabel eSavings, tfsa, rrsp;
	private float eValue, tfValue, rrValue; 
	
	@Override
	//populates the JPanel
	public void addContents() {
		
		ResultSet r;

		try {
			eValue = 0;
			tfValue = 0;
			rrValue = 0;
			
			r = DBInit.createStatement().executeQuery("SELECT name, amount FROM savings WHERE id = " + DBInit.getAccountNumber());
			
			if (r.next()){
			eValue = r.getFloat(2);
			eSavings = new JLabel("$" + Float.toString(eValue), SwingConstants.RIGHT);
			addInput(r.getString(1) + ":", eSavings);

			r.next();
			rrValue = r.getFloat(2);
			rrsp = new JLabel("$" + Float.toString(rrValue), SwingConstants.RIGHT);
			addInput(r.getString(1) + ":", rrsp);
			
			r.next();
			tfValue = r.getFloat(2);
			tfsa = new JLabel("$" + Float.toString(tfValue), SwingConstants.RIGHT);
			addInput(r.getString(1) + ":", tfsa);
			}else DebugPrinter.printDebug("Issue with SavingsResult");
		
		//addInput("TFSA:",tfsa);
		addTotal(eValue, tfValue, rrValue);
			} catch (SQLException e) {
				e.printStackTrace();
				showError(e.getLocalizedMessage());
			
			}
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
