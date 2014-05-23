package ui.panes;

import javax.swing.JFrame;

/**
 * 
 * @author Ammar Vahanvaty May 21/ 2014
 *
 */

public class FrameController {
	private static JFrame a;
	private static JFrame b;
	
	public FrameController(){
		a = null;
		b = null;
	}
	
	public static void switchFrames(){
		if (a.isVisible()){
			b.setBounds(a.getBounds());
			a.setVisible(false);
			b.setVisible(true);
		}else{
			a.setBounds(b.getBounds());
			b.setVisible(false);
			a.setVisible(true);
		}
	}
	
	public void setFrames(JFrame frame, JFrame frame2){
		a = frame;
		b = frame2;
		a.setVisible(true);
	}
	
	public void printFrameName(){
		System.out.println(a.getTitle());
		System.out.println(a.getTitle());
	}

}
