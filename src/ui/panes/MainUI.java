package ui.panes;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import ui.panes.overview.EntriesLayout;
import ui.panes.overview.OverviewLayout;

/**
 * 
 * @author Ammar Vahanvaty May 21/ 2014
 *
 */

//Container for the main components of Budgie, all the form will go here

@SuppressWarnings("serial")
public class MainUI extends JFrame{
	private JTabbedPane tabs;
	private EntriesLayout label2;
	private JButton button1;
	
	public MainUI(String title, FrameController control){
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(500, 500));
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		
		tabs = new JTabbedPane();
		populateTabs();
	}
	
	private void populateTabs(){
		label2 = new EntriesLayout();
		
		button1 = new JButton("this is a button1");
		button1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				FrameController.switchFrames();
			}
			
		});
		
		tabs.addTab("Debits", new OverviewLayout());
		tabs.addTab("Tab 2", label2);
		tabs.addTab("Tab 3", button1);
		
		add(tabs);
		pack();
	}

}
