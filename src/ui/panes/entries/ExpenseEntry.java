package ui.panes.entries;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class ExpenseEntry extends AbstractEntry {

	@Override
	protected String getTitle() {
		return "Daily Expense Input";
	}

	@Override
	protected Color getColor() {
		return Color.magenta;
	}

	@Override
	protected void setEntries(LinkedList<JLabel> e, LinkedList<JComponent> c) {
		e.add(new JLabel("Date"));
		e.add(new JLabel("Product/Service"));
		e.add(new JLabel("Type"));
		e.add(new JLabel("Cost"));
		e.add(new JLabel("Debitted"));
		
		//add components for each title
		
		//date
		String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		JLabel date1 = new JLabel(date);
		c.add(date1);
		
		//Empty for now, but fill in with stuff later
		for (int i = 1; i<e.size(); i++){
			c.add(new JLabel("TBD"));
		}
		
	}

}
