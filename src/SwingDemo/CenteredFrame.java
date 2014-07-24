/**
 * 
 */
package SwingDemo;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

/**
 * @author zfh1005
 */
public class CenteredFrame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		CeneredFrame frame = new CeneredFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}

class CeneredFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8083986526884121485L;

	public CeneredFrame(){
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;

		setSize(screenWidth/2, screenHeight/2);
		setLocation(screenWidth/4, screenHeight/4);
		Image img = kit.getImage("icon.gif");
		setIconImage(img);
		setTitle("CeneredFrame");
		FontPanel panel = new FontPanel();
		add(panel);
	}	
}

class FontPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4987017067174066363L;
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		String message = "Hello world!";
		
		Font f = new Font("Arial", Font.BOLD, 44);
		g2.setFont(f);
	
		//measure the size of the message
		FontRenderContext context = g2.getFontRenderContext();
		Rectangle2D bounds = f.getStringBounds(message, context);
		
		//set(x, y) = top left corner of text
		
		double x = (getWidth() - bounds.getWidth()) / 2;
		double y = (getHeight() - bounds.getHeight()) / 2;
		
		//add ascent to y to reach the base line
		double ascent = -bounds.getY();
		double baseY = y + ascent;
		
		//draw message
		g.drawString(message, (int)x, (int)baseY);
		
		//draw the baseline
		g2.draw(new Line2D.Double(x, baseY, x + bounds.getWidth(), baseY));
		
		//draw the enclosing rectangle
		Rectangle2D rect = new Rectangle2D.Double(x, y, bounds.getWidth(), bounds.getHeight());
		g2.draw(rect);
	}
}


