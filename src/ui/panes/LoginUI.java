package ui.panes;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.BudgieUI;
import db.DBInit;

/**
 * 
 * @author Ammar Vahanvaty May 21/ 2014
 *
 */

@SuppressWarnings("serial")
public class LoginUI extends JFrame{
	private JPanel titlePanel, inputField;
	private JTextField username, password;
	
	
	public LoginUI(String title, FrameController control){
		super (title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(500, 500));
		setLocationRelativeTo(null);
		//setAlwaysOnTop(true);
		
		createHomePage();
	}
	
	//create a JFrame for the homepage with logo, username and password login and enter keys
	public void createHomePage(){
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints(); 
		//insert username and password fields
		JLabel title;

		//build the title panel
		titlePanel = new JPanel();
		title = new JLabel("Budgie");
		title.setSize(new Dimension(40, 40));
		titlePanel.add(title);
		
		c.anchor = GridBagConstraints.CENTER;
		c.gridy = 0;
		c.weighty = .5;
		add(titlePanel, c);
		
		inputField = addinputField();

		//positioning of the grid field
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 1;
		c.ipady = 10;
		c.weighty = .5;
		add(inputField, c);
				
		pack();
		
		
	}
	
	private JPanel addinputField() {
		JLabel userLabel, passLabel;
		JButton enter,cancel;
		//Input fields for username and password
	    inputField = new JPanel();
		inputField.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.VERTICAL;
		
		//allow the user to hit enter and automatically complete the entries without clicking
		username = new JTextField(10);
		//password = new JTextField(10);
		
		username.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				int key = arg0.getKeyCode();
				if (key == KeyEvent.VK_ENTER){
				login(username);
				}
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}
			
		});
		
		//give enter and cancel buttons their functionality
		enter = new JButton("Enter");
		enter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			login(username);
			}
			
			
		});
		
		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				username.setText(null);
				//password.setText(null);
				username.requestFocus();
				
			}
			
			
		});
		
		userLabel = new JLabel("Username: ");
		//passLabel = new JLabel("Password: ");
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		inputField.add(userLabel, gbc);
		
	/*	gbc.gridx = 0;
		gbc.gridy = 1;
		inputField.add(passLabel, gbc);*/
	
		gbc.gridx = 1;
		gbc.gridy = 0;
		inputField.add(username, gbc);
		
/*		gbc.gridx = 1;
		gbc.gridy = 1;
		inputField.add(password, gbc);*/
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		inputField.add(enter,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		inputField.add(cancel,gbc);
		
		return inputField;
		
	}
	
	//checks the name for authenticity and attempts login
	private void login(JTextField user){
		String username = user.getText();
		user.setText(null);
		user.requestFocus();
		
		//Validate username
		if (username.isEmpty()){
			System.out.println("Empty values!");
		}
		else if (!username.matches("[a-zA-Z]+")){
			System.out.println("Improper name!");
		}
		
		else {
		DBInit.connectDB(username);
		FrameController s = BudgieUI.getSwitcher();
		JFrame main = new MainUI("Budgie", s);
		BudgieUI.setMain(main);
		s.setFrame(main);
		FrameController.switchFrames();
		}
	}

}
