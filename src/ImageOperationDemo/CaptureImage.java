/**
 * 
 */

/**
 * @author zfh1005
 *
 */
package ImageOperationDemo;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;


public class CaptureImage {
	
	public void CaptureFixedPixel(int x, int y, int width, int height, String OutputFileName){
		try {
			Robot robot = new Robot();
			BufferedImage bi = robot.createScreenCapture(new Rectangle(x, y, width, height));
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
	
	public void CaptureFullScreen(String OutputFileName){
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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CaptureImage ci = new CaptureImage();
		ci.CaptureFixedPixel(100, 100, 500, 400, "c:\\test.jpg");
		ci.CaptureFullScreen("c:\\test--2.jpg");
		
	}
}
