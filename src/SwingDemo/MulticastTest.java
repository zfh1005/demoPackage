/**
 * 
 */
package SwingDemo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author zfh1005
 */
public class MulticastTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MulticastFrame bf = new MulticastFrame();
		bf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bf.setVisible(true);
	}

}

/*
 * a frame with a button panel
 * */
class MulticastFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8019069859833188217L;
	public MulticastFrame(){
		setTitle("MulticastTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		//add panel to frame
		MulticastPanel panel = new MulticastPanel();
		add(panel);
	}

	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 300;
}


class MulticastPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6895537786339517183L;

	public MulticastPanel(){
		
		//add "New" button		
		JButton newButton = new JButton("New");
		add(newButton);
		
		//add "Close" button
		final JButton closeButton = new JButton("Close");
		add(closeButton);
		
		ActionListener newListener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BlankFrame frame = new BlankFrame(closeButton);
				frame.setVisible(true);
				
			}
			
		};		
		newButton.addActionListener(newListener);		
	}	
}


class BlankFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7635204349052202879L;
	
	
	public BlankFrame(final JButton closeButton){
		counter ++;
		setTitle("Frame" + counter);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLocation(SPACING * counter , SPACING * counter);
		
		closeListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				closeButton.removeActionListener(closeListener);
				dispose();	
				counter = 0;
			}
			
		};
		closeButton.addActionListener(closeListener);
		
	}
	
	private ActionListener closeListener;
	private static final int DEFAULT_WIDTH = 200;
	private static final int DEFAULT_HEIGHT = 150;
	private static final int SPACING = 40;
	private static int counter = 0;
	
	
}


