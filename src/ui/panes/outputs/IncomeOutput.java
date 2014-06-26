package ui.panes.outputs;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import db.DBInit;

@SuppressWarnings("serial")
public class IncomeOutput extends AbstractOutput{
	private JLabel fixed, work, chequing;
	float fixedValue, workValue, chequingValue;
	
	@Override
	//populates the JPanel
	public void addContents() {
		//Add Fixed expenses from DB
		ResultSet r;
		try {
			fixedValue = 0;
			workValue = 0;
			chequingValue = 0;
			
			r = DBInit.createStatement().executeQuery("SELECT SUM(amount) FROM fixed_income WHERE id = " + DBInit.getAccountNumber());
	
		if (r.next()){
		fixedValue = r.getFloat(1);
		
		} else showError("Unable to populate Fixed Income");
		
		r = DBInit.createStatement().executeQuery("SELECT SUM(amount) FROM payments WHERE id = " + DBInit.getAccountNumber() +
				" AND MONTH(CURDATE()) = MONTH(date)");
		
		if (r.next()){
		workValue = r.getFloat(1);
		} else showError("Unable to populate Work Income");
		
		r = DBInit.createStatement().executeQuery("SELECT chequing_balance FROM accounts WHERE id = " + DBInit.getAccountNumber());
	
		if (r.next()){
		chequingValue = r.getFloat(1);
		} else showError("Unable to populate Chequing Income");
		
		fixed = new JLabel("$" + Float.toString(fixedValue), SwingConstants.RIGHT);
		work = new JLabel("$" + Float.toString(workValue), SwingConstants.RIGHT);
		chequing = new JLabel("$" + Float.toString(chequingValue), SwingConstants.RIGHT);
		
		
		addInput("Fixed:", fixed);
		addInput("Work:",work);
		addInput("Chequing:", chequing);
		addTotal(fixedValue, workValue, chequingValue);
		
		//sets the total negative for addition purposes
		} catch (SQLException e) {
			//e.printStackTrace();
			showError(e.getLocalizedMessage());
			
		}
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
