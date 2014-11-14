/**
 * 
 */
package XMLDemo.XMLWriteTest;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @author zfh1005
 *
 */
public class XMLWriteTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				XMLWriteFrame frame = new XMLWriteFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});

	}

}
