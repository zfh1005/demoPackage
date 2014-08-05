/**
 * 
 */
package ExceptionDemo;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

/**
 * @author zfh1005
 *
 */
public class LoggingImageViewer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		
		if((System.getProperty("java.util.logging.config.class") == null) &&
				(System.getProperty("java.util.logging.config.file") == null)	){
			Logger.getLogger("").setLevel(Level.ALL);
			final int LOG_ROTATION_COUNT = 10;
			try {
				Handler handler = new FileHandler("%h/LoggingImageViewer.log", 0, LOG_ROTATION_COUNT);
				Logger.getLogger("").addHandler(handler);
			}			 
			catch (IOException e) {
				Logger.getLogger("DemoProject.ExceptionDemo").log(Level.SEVERE, "can't craete log file handle", e);
			}
		}
		
		Handler windowHandler = new WindowHandler();
		windowHandler.setLevel(Level.ALL);
		Logger.getLogger("DemoProject.ExceptionDemo").addHandler(windowHandler);
		
		JFrame frame = new ImageViewerFrame();
		frame.setTitle("LoggingImage");
		frame.setSize(300, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Logger.getLogger("DemoProject.ExceptionDemo").fine("showing frame");
		frame.setVisible(true);	
		
		
	}
}
