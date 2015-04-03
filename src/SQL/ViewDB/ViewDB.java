/**
 * 
 */
package SQL.ViewDB;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @author zfh1005
 *
 */
public class ViewDB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JFrame frame = new ViewDBFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);				
			}
		});

	}

}
