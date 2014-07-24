/**
 * 
 */
package SwingDemo;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author zfh1005
 */
public class ImageTest {
	public static void main(String[] args){
		ImageFrame frame = new ImageFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/*
 * a frame with an image panel
 * */

class ImageFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1982820980108181203L;
	
	public ImageFrame(){
		setTitle("ImageTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		//add panel to frame
		ImagePanel panel = new ImagePanel();
		add(panel);
	}
	
	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 300;
	
}
/*
 * a panel that displays a tiled image
 * */

class ImagePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3576673348256565555L;

	public ImagePanel(){
		String iamgePath = "C:\\Documents and Settings\\ZFH\\EclipseProjects\\demoFunction\\image\\111.jpg"; 
		
		//acquire the image
		try {
			image = ImageIO.read(new File(iamgePath));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(image == null){
			return;
		}
		
		int iamgeWidth = image.getWidth(this);
		int iamgeHeight =image.getHeight(this);
		
		//draw the image in the upper-left corner
		g.drawImage(image, 0, 0, null);
		
		//tile the image across the panel
		for(int i = 0; i * iamgeWidth <= getWidth(); i++){
			for(int j = 0; j * iamgeWidth <= getWidth(); j++){
				if(i +j > 0){
					g.copyArea(0, 0, iamgeWidth, iamgeHeight, i * iamgeWidth, j * iamgeHeight);
				}
			}
		}
	}
	
	private Image image;
	
}