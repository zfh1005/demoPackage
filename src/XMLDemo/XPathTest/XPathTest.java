/**
 * 
 */
package XMLDemo.XPathTest;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @author zfh1005
 *
 */

/*
 * This program evaluates XPath expressions
 * @version 1.01 2014-11-01
 * 
 * */
public class XPathTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JFrame frame = new XPathFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});

	}

}
