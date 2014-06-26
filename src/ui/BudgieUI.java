package ui;

/**
 * 
 * @author Ammar Vahanvaty May 21/ 2014
 *
 */

import javax.swing.*;

import db.DBInit;
import ui.panes.FrameController;
import ui.panes.LoginUI;
import ui.panes.MainUI;

@SuppressWarnings("serial")
public class BudgieUI extends JPanel {
	static JFrame login, main;
	static FrameController switcher;
	
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				switcher = new FrameController();
				login = new LoginUI("Budgie", switcher);
				switcher.setFrame(login);
				
			}
		
		});
	}
	
	public static void setMain(JFrame mains){
		main = mains;
	}
	
	public static FrameController getSwitcher(){
		return switcher;
	}

}
