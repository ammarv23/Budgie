package ui.panes.entries;

import java.awt.*;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExpenseEntry extends AbstractEntry {

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
				new JLabel("Cost"), new JLabel("Debitted")};
	
		for (int i = 0; i<jList.length; i++){
			jList[i].setAlignmentX(CENTER_ALIGNMENT);
			e.add(jList[i]);
		}
		
		
		//add components for each title
		
		//date
		String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		JLabel date1 = new JLabel(date);
		date1.setAlignmentX(CENTER_ALIGNMENT);
		c.add(date1);
		
		//Empty for now, but fill in with stuff later
		for (int i = 1; i<e.size(); i++){
			JTextField tbd = new JTextField(5);
			tbd.setAlignmentX(CENTER_ALIGNMENT);
			c.add(tbd);
		}
		
		
	}

}
