package ui.panes.outputs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.border.Border;

import db.DBInit;

@SuppressWarnings("serial")
public class ProfitOutput extends AbstractOutput{
	JLabel profit;
	float sum;
	private LinkedList<Float> expense;
	
	public ProfitOutput(float... f){
		super();
		for (float f1: f){
		expense.add(f1);	
		}
		
		addSuperTotals();
	}


	@Override
	protected String getTitle() {
		return "Profit/Loss";
	}


	@Override
	protected Color getColour() {
		if (sum < 0){
			return Color.red;
		}
		else return Color.green;
	}


	@Override
	
	//Sums the total of chequing and savings balance and siplays it for the account holder.
 public void addContents() {
		try {
			expense = new LinkedList<Float>();
			Statement s = DBInit.createStatement();
			ResultSet r = s.executeQuery("SELECT chequing_balance FROM accounts WHERE"
					+ " id = " + DBInit.getAccountNumber());
			
			profit = new JLabel();
			sum = 0;
			r.next();
			float chequing = r.getInt(1);
			
			//sum = chequing + savings - expenses - varexp;

			//profit.setText("$ " + Float.toString(sum));
			profit.setFont(new Font("Arial", Font.PLAIN, 20));
			profit.setToolTipText("<html>Chequing balance is: " + chequing + "<br>" +
								"</html>");
			
			
			//add(j);
					
		} catch (SQLException e) {
			e.printStackTrace();
			showError(e.getLocalizedMessage());
		
		}
		
	}


	private void addSuperTotals() {
		//gather all the totals in expense and subtract from sum
		
		for (float f1: expense){
			sum += f1;
		}
		
		//change border depending on value of sum
			Border line = BorderFactory.createLineBorder(getColour());
			Border title = BorderFactory.createTitledBorder(line, getTitle());
			setBorder(title);
		
		profit.setText("$ " + Float.toString(sum));
		
		add(profit);
	}


	@Override
	public boolean update() {
		return false;
	}
	
	public boolean updateWithValues(LinkedList<Float> totals){
		sum = 0;
		removeAll();
		addContents();
		
		for (float f1: totals){
			expense.add(f1);
		}
		
		addSuperTotals();
		return true;
		
	}



}
