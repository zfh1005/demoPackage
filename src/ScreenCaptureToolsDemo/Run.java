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
		EventQueue.invokeLater(new Runnable() { 
			@Override
			public void run() {
				CaptureScreenTools ssw=new CaptureScreenTools();
				ssw.setVisible(true);
			}
		});
	}

}


