/**
 * 
 */

/**
 * @author zfh1005
 *
 */

package ScreenCaptureToolsDemo;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ImageOperationDemo.CaptureImage;

public class CaptureRangeScreen {
	
	public void CaptureFixedPixel(int x, int y, int width, int height){
		try {
			Robot robot = new Robot();
			BufferedImage bi = robot.createScreenCapture(new Rectangle(x, y, width, height));
			try {
				ImageIO.write(bi, "jpg", (new File("c:\\test.jpg")));
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CaptureRangeScreen ci = new CaptureRangeScreen();
		ci.CaptureFixedPixel(100, 100, 500, 400);
		
	}

}
