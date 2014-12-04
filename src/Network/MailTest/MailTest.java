/**
 * 
 */
package Network.MailTest;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @author zfh1005
 *
 */
public class MailTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JFrame frame = new MailTestFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
				frame.setVisible(true);
			}
		});

	}

}
