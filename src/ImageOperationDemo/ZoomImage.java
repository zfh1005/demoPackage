/**
 * 
 */
package ImageOperationDemo;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @author zfh1005
 *
 */
public class ZoomImage {
	private String ImageName;
	private double Rate;
	
	
	public ZoomImage(String fileName, double rate){
		this.ImageName = fileName;
		this.Rate = rate;
	}
	
	public void ZoomAndSaveImage(String saveToImageName){

		Image beforeImage = Toolkit.getDefaultToolkit().getImage(this.ImageName);
		double iTempWidth = getOldImageWidth(this.ImageName);
		double iTempHeight = getOldImageHeight(this.ImageName);
		iTempWidth *= this.Rate;
		iTempHeight *= this.Rate;
		BufferedImage bImage = new BufferedImage((int)iTempWidth, (int)iTempHeight, BufferedImage.TYPE_INT_BGR);
		
		Graphics2D g2D = bImage.createGraphics();
		g2D.drawImage(beforeImage, 0, 0, (int)iTempWidth, (int)iTempHeight, null);
		
		SaveImage(bImage, saveToImageName);
	}

	public void SaveImage(BufferedImage image, String saveToImageName){
		try {			
			ImageIO.write(image, "jpg", new FileOutputStream(saveToImageName));
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getOldImageWidth(String FileName){
		int iWidth = 0;
		File file = new File(FileName);
		Image scr;
		try {
			scr = javax.imageio.ImageIO.read(file);
			iWidth = scr.getWidth(null);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return iWidth;		
	}
	
	public int getOldImageHeight(String FileName){
		int iHeight = 0;
		File file = new File(FileName);
		Image scr;
		try {
			scr = javax.imageio.ImageIO.read(file);
			iHeight = scr.getHeight(null);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return iHeight;		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		// TODO Auto-generated method stub
		String fileName = "c:\\demo.jpg";
		ZoomImage zImage = new ZoomImage(fileName, 0.4);
		zImage.ZoomAndSaveImage("c:\\11111.jpg");
		

	}

}
