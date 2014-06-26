package ui.panes.outputs;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ui.displays.DebugPrinter;
import db.DBInit;

@SuppressWarnings("serial")
public class ExpensesOutput extends AbstractOutput{

	private JLabel fixed, variable, goal;
	private float fixedValue, varValue, goalValue;
	
	@Override
	//populates the JPanel
	public void addContents(){
		
		//Add Fixed expenses from DB
			ResultSet r;
			try {
				
			fixedValue = 0;
			varValue = 0;
			goalValue = 0;
			
				r = DBInit.createStatement().executeQuery("SELECT SUM(amount) FROM expenses WHERE user_id = " + DBInit.getAccountNumber());
		
			if (r.next()){
			fixedValue = r.getInt(1);
			
			fixed = new JLabel("$" + Float.toString(fixedValue), SwingConstants.RIGHT);
			} else DebugPrinter.printDebug("Unable to obtain resultSet at expenses");
			
		//Add Variable(transaction) expenses for the current month from DB
			ResultSet var = DBInit.createStatement().executeQuery("SELECT SUM(amount) FROM transaction WHERE id = '" + DBInit.getAccountNumber()
					+ "' AND MONTH(CURDATE()) = MONTH(date)");
			if (var.next()){
			varValue = var.getInt(1);
			
			variable = new JLabel("$" + Float.toString(varValue), SwingConstants.RIGHT);
			}else DebugPrinter.printDebug("Unable to obtain resultSet at transaction");
			
		//Return the amount in goals for the current month
			//result set contains full goal amount and month breakdown to reach goal
			ResultSet goals = DBInit.createStatement().executeQuery("SELECT amount, DATEDIFF(end_date, start_date)/30 as 'interval' from goals where id = "
					+ DBInit.getAccountNumber() + " AND CURDATE() < end_date");
			
			if (goals.next()){
				
			do {	
			goalValue += goals.getInt(1) / goals.getInt(2);
			}while (goals.next());
			
			}else{
				DebugPrinter.printDebug("Unable to obtain resultSet at goals");
				goalValue = 0;
			}
			goal = new JLabel("$" + Float.toString(goalValue), SwingConstants.RIGHT);
			
		
		addInput("Fixed:", fixed);
		addInput("Variable:",variable);
		addInput("Goal:", goal);
		addTotal(fixedValue, varValue, goalValue);
		super.total = -super.total;
			} catch (SQLException e) {
				e.printStackTrace();
				showError(e.getLocalizedMessage());
				
			}
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
