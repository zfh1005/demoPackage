/**
 * 
 */
package ExceptionDemo;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * @author zfh1005
 *
 */
public class RobotTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ButtonFrame frame = new ButtonFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		//attach a robot to the screen device
		GraphicsEnvironment envi = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice screen = envi.getDefaultScreenDevice();
		
		try {
			Robot robot = new Robot(screen);
			run(robot);
		} 
		catch (AWTException e) {
			e.printStackTrace();
		}

	}
	
	
	public  static void run(Robot robot){
		//simulate a space bar press
		robot.keyPress(' ');
		robot.keyRelease(' ');
		
		
		//simulate a tab key followed by a space
		robot.delay(2000);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyPress(' ');
		robot.keyRelease(' ');
		
		//simulate a mouse click over the rightmost button
		robot.delay(2000);
		robot.mouseMove(200, 50);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.keyRelease(InputEvent.BUTTON1_MASK);
		
		//capture the screen and show the resulting image
		robot.delay(2000);
		BufferedImage image = robot.createScreenCapture(new Rectangle(0, 0, 400, 300));
		
		ImageFrame frame = new ImageFrame(image);
		frame.setVisible(true);
		
	}

}

/*
 * a frame with a button panel
 * */
class ButtonFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8019069859833188217L;
	public ButtonFrame(){
		setTitle("ButtonTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		//add panel to frame
		ButtonPanel_1 panel = new ButtonPanel_1();
		
		add(panel);
	}

	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 300;
}


/*
 * a panel with three buttons
 * */

class ButtonPanel_1 extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6596506527373351814L;

	public ButtonPanel_1(){
		makeButton("Red", Color.RED );
		makeButton("Green", Color.GREEN );
		makeButton("Blue", Color.BLUE );
	}
	
	public void makeButton(String name, Color background){
		JButton button = new JButton(name);
		add(button);
		ColorAction action = new ColorAction(background);
		button.addActionListener(action);
	}


	private class ColorAction implements ActionListener{
		public ColorAction(Color c){
			backgrounColor = c;
		}

		/* 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//set panel background color
			setBackground(backgrounColor);
		}
		private Color backgrounColor;
	}
}


class ImageFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1652053953051444647L;
	public ImageFrame(Image image){
		setTitle("Capture");
		setSize(WIDTH, HEIGHT );
		
		JLabel label = new JLabel(new ImageIcon(image));
		add(label);
	}
	
	private final int WIDTH = 450;
	private final int HEIGHT = 350;
}
