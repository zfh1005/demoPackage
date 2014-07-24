/**
 * 
 */
package SwingDemo;

import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;

/**
 * @author zfh1005
 */
public class GraphicsDraw {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DrawFrame frame = new DrawFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}

/*
 * A frame continue a panel with drawings
 * */
class DrawFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4878363336890446677L;
	public DrawFrame(){
		setTitle("GrawTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		Toolkit kit = Toolkit.getDefaultToolkit();
		
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;

		setLocation(screenWidth/4, screenHeight/4);
		
		//add panel to frame
		DrawPanel panel = new DrawPanel();
		add(panel);
	}
		
	public int getDefaultWidth() {
		return DEFAULT_WIDTH;
	}
	public int getDefaultHeight() {
		return DEFAULT_HEIGHT;
	}

	public final int DEFAULT_WIDTH = 400;
	public final int DEFAULT_HEIGHT = 400;
	
}

class DrawPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1833577666109443249L;
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		//draw a rectangle

		Rectangle2D rect= new Rectangle2D.Double(leftX, topY, width, height);
		g2.draw(rect);
		
		//draw the enclosed ellipse
		Ellipse2D ellipse = new Ellipse2D.Double();
		ellipse.setFrame(rect);
		g2.draw(ellipse);
		
		
		//draw a diagonal line		
		g2.draw(new Line2D.Double(leftX, topY, leftX + width, topY + height));
		
		//draw a circle with the same center
		
		double centerX = rect.getCenterX();
		double centerY = rect.getCenterY();
		double radius = 150;
		
		Ellipse2D circle = new Ellipse2D.Double();
		circle.setFrameFromCenter(centerX,  centerY, centerX + radius, centerY + radius);
		g2.draw(circle);		
	}

	
	private double leftX = 100;
	private double topY = 100;
	private double width = 200;
	private double height = 150;
	
}
