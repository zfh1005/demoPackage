/**
 * 
 */
package SQL.QueryDB;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @author zfh1005
 * @date 2015/03/31
 * @version v0.0.1
 *
 */
public class QueryDB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JFrame frame = new QueryDBFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);				
			}
		});
	}
}
