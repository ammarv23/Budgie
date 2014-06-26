package ui.panes.outputs;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import db.DBInit;

@SuppressWarnings("serial")
public class ExpensesOutput extends AbstractOutput{

	private JLabel fixed, variable, goal;
	private int fixedValue, varValue, goalValue;
	
	@Override
	//populates the JPanel
	public void addContents(){
		variable = new JLabel("Test2", SwingConstants.RIGHT);
		goal = new JLabel("Test3", SwingConstants.RIGHT);
		
		//Add Fixed expenses from DB
			ResultSet r;
			try {
				r = DBInit.createStatement().executeQuery("SELECT SUM(amount) FROM expenses WHERE user_id = " + DBInit.getAccountNumber());
		
			r.next();
			fixedValue = r.getInt(1);
			
			fixed = new JLabel("$" + Integer.toString(fixedValue), SwingConstants.RIGHT);
			
		//Add Variable(transaction) expenses for the current month from DB
			ResultSet var = DBInit.createStatement().executeQuery("SELECT SUM(amount) FROM transaction WHERE id = '" + DBInit.getAccountNumber()
					+ "' AND MONTH(CURDATE()) = MONTH(date)");
			var.next();
			varValue = var.getInt(1);
			
			variable = new JLabel("$" + Integer.toString(varValue), SwingConstants.RIGHT);
			
		//Return the amount in goals for the current month
			//result set contains full goal amount and month breakdown to reach goal
			ResultSet goals = DBInit.createStatement().executeQuery("SELECT amount, DATEDIFF(end_date, start_date)/30 as 'interval' from goals where id = "
					+ DBInit.getAccountNumber() + " AND CURDATE() < end_date");
			
			goals.next();
			goalValue = goals.getInt(1) / goals.getInt(2);
			
			//System.out.println("goal amount for the month is: " + goalValue);
			
			goal = new JLabel("$" + Integer.toString(goalValue), SwingConstants.RIGHT);
			
		
		addInput("Fixed:", fixed);
		addInput("Variable:",variable);
		addInput("Goal:", goal);
		addTotal(fixedValue, varValue, goalValue);
			} catch (SQLException e) {
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
