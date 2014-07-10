/**
 * 
 */
package ScreenCaptureToolsDemo;

import java.awt.EventQueue;

/**
 * @author zfh1005
 *
 */
public class Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() { 
			@Override
			public void run() {
				CaptureScreenTools ssw=new CaptureScreenTools();
				ssw.setVisible(true);
			}
		});
	}

}


