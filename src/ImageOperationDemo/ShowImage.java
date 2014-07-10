/**
 * 
 */
package ImageOperationDemo;


import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
/**
 * @author zfh1005
 *
 */
public class ShowImage extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 201407090001L;
	
	private String ImageName;

	public ShowImage(String FileName){
		File file = new File(FileName);
		if(!file.exists()){
			return;
		}
		int width = getImageWidth(FileName);
		int height = getImageHeight(FileName);			
		setSize(width, height);
		setVisible(true);
		this.ImageName = FileName;

	}
	
	public int getImageWidth(String FileName){
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
	
	public int getImageHeight(String FileName){
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

	@Override
	public void paint(Graphics g){
		Image image = getToolkit().getImage(ImageName);
		g.drawImage(image, 0, 0, this);

	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String fileName = "c:\\demo.jpg";
		ShowImage image = new ShowImage(fileName);
	}

}
