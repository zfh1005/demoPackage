/**
 * 
 */

/**
 * @author zfh1005
 *
 */

package ScreenCaptureToolsDemo;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CaptureFullScreen {
	
	public void CaptureScreen(final String OutputFileName){
		try {
			
			Robot robot = new Robot();
			Dimension dis = Toolkit.getDefaultToolkit().getScreenSize();			
			BufferedImage bi = robot.createScreenCapture(new Rectangle(dis.width, dis.height));
			try {
				ImageIO.write(bi, "jpg", (new File(OutputFileName)));
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		} 
		catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CaptureFullScreen cfs = new CaptureFullScreen();
		cfs.CaptureScreen("c:\\test.jpg");

	}

}
