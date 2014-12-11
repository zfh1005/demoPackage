/**
 * 
 */
package Network.POST_Test;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @author zfh1005
 *
 */
public class POST_Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JFrame frame = new PostTestFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);				
			}
		});

	}

}
