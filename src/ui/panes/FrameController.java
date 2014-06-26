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
	
	public void setFrame(JFrame frame){
		if (a == null){
		a = frame;
		a.setVisible(true);
		}
		else {
			b = frame;
		}
	
	}
	
	public void printFrameName(){
		System.out.println(a.getTitle());
		System.out.println(a.getTitle());
	}

}
