package ui.panes.entries;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.*;

import db.DBInit;

public class ExpenseEntry extends AbstractEntry {
	private java.sql.Date date;
	private JTextField vendor, type, amt;
	private JComboBox<String> typeList;

	@Override
	protected String getTitle() {
		return "Daily Expense Input";
	}

	@Override
	protected Color getColor() {
		return Color.magenta;
	}

	//TODO: have the borders match for the label and the component below.
	
	@Override
	protected void setEntries(LinkedList<JLabel> e, LinkedList<JComponent> c) {
		JLabel[] jList = {new JLabel("Date"), new JLabel("Product/Service"), new JLabel("Type"), 
				new JLabel("Cost")};
	
		for (int i = 0; i<jList.length; i++){
			jList[i].setAlignmentX(CENTER_ALIGNMENT);
			e.add(jList[i]);
		}
		
		
		//add components for each title
		
		//date
		
		String dateString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		date = java.sql.Date.valueOf(dateString);
		JLabel date1 = new JLabel(dateString);
		date1.setAlignmentX(CENTER_ALIGNMENT);
		c.add(date1);
		
		//Empty for now, but fill in with stuff later
			vendor = new JTextField(5);
			vendor.setAlignmentX(CENTER_ALIGNMENT);
			c.add(vendor);
			
		String[] typeStrings = {"Groceries", "Dining", "Gas", "Entertainment", "Clothes", "Transport", "Parking", "Misc"};	
		typeList = new JComboBox<String>(typeStrings);
		
		c.add(typeList);
		
		//Amount indication
		JPanel amountPanel = new JPanel();
		JLabel dollar = new JLabel("$");
		amt = new JTextField(5);
		
		amountPanel.add(dollar);
		amountPanel.add(amt);
		
		c.add(amountPanel);
		
		
	}

	//submits the information in the field.
	@Override
	protected void submit() {
		//Insert the value into the database
		try {
			String insertString = "insert into transaction (id, date, vendor, type, amount) Values ("
					+ "?,?,?,?,?)";
			
			PreparedStatement prep = DBInit.createPreparedStatement(insertString);
			
			//populate query with data from data comp
			prep.setInt(1, DBInit.getAccountNumber());
			prep.setDate(2, date);
			prep.setString(3, vendor.getText());
			prep.setString(4, (String) typeList.getSelectedItem());
			prep.setString(5, amt.getText());
			
			prep.execute();
			System.out.println("insertion successful");
			
		} catch (SQLException e) {
			showError(e.getMessage());
			e.printStackTrace();
		}
		
	}

	@Override
	protected void clear() {
		vendor.setText(null);
		type.setText(null);
		amt.setText(null);
	}

}
