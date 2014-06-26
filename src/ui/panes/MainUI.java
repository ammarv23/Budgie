package ui.panes;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ui.displays.DebugPrinter;
import ui.panes.overview.DBLayout;
import ui.panes.overview.EntriesLayout;
import ui.panes.overview.OverviewLayout;
import ui.panes.overview.UpdateManager;

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
		//setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		
		tabs = new JTabbedPane();
		populateTabs();
	}
	
	private void populateTabs(){
		label2 = new EntriesLayout();
		
		JPanel thirdTab = new JPanel();
		
		button1 = new JButton("this is a button1");
		button1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				FrameController.switchFrames();
			}
			
		});
		
		thirdTab.add(new DBLayout());
		thirdTab.add(button1);
		
		OverviewLayout ovr = new OverviewLayout();
		
		tabs.addTab("Overview", ovr);
		tabs.addTab("Inputs", label2);
		tabs.addTab("Tab 3", thirdTab);
		
		tabs.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent arg0) {
				JTabbedPane sourcePane = (JTabbedPane) arg0.getSource();
				
				int index = sourcePane.getSelectedIndex();
				DebugPrinter.printDebug("Index is: " + index);
				
				//only update on the first index change
				if (index == 0){
					UpdateManager.updateAll();
				}
			}
			
			
		});
		
		add(tabs);
		pack();
	}

}
