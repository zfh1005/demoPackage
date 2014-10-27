/**
 * 
 */
package XMLDemo.GridBagTest;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 *This program shows how to use an XML file to describe a gridbag layout
 * @author zfh1005
 *
 */
public class GridBagTest {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		/*
		 * if don't change args type to 'final' there is a error
		 * "Cannot refer to the non-final local variable args defined in an enclosing scope"		 
		 */
		EventQueue.invokeLater(new Runnable() {			
			@Override
			public void run() {
				String filenameString = (args.length == 0 ? "fontdialog.xml" : args[0]);
				JFrame frame = new FontFrame(filenameString);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);				
			}
		});
	}
}
