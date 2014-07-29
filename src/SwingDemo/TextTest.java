/**
 * 
 */
package SwingDemo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
/**
 * @author zfh1005
 */
public class TextTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		TextFrame frame = new TextFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/*
 * A frame with a panel for sketching a figure
 * */
class TextFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3086385924287683369L;

	public TextFrame(){
		setTitle("TextTest");
		//setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		DocumentListener listener = new ClockFieldListener();
		//add panel to frame
		JPanel panel = new JPanel();

		panel.add(new JLabel("Hours:"));
		hourField = new JTextField("12", 5);
		panel.add(hourField);
		hourField.getDocument().addDocumentListener(listener);

		panel.add(new JLabel("Minutes:"));
		minuteField = new JTextField("00", 5);
		panel.add(minuteField);
		minuteField.getDocument().addDocumentListener(listener);

		add(panel, BorderLayout.SOUTH);

		//add the clock panel
		clock = new ClockPanel();
		add(clock, BorderLayout.CENTER);
		pack();

	}

	/*
	 *set the clock to the value stored in the text field
	 * */
	public void setClock(){
		int hours = Integer.parseInt(hourField.getText().trim());
		int minutes = Integer.parseInt(minuteField.getText().trim());
		clock.settime(hours, minutes);

	}

	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 600;

	private JTextField hourField ;
	private JTextField minuteField ;
	private ClockPanel clock;

	private class ClockFieldListener implements DocumentListener{
		/*
		 * @see javax.swing.event.DocumentListener#changedUpdate(javax.swing.event.DocumentEvent)
		 */
		@Override
		public void changedUpdate(DocumentEvent arg0) {
		}

		/* 
		 * @see javax.swing.event.DocumentListener#insertUpdate(javax.swing.event.DocumentEvent)
		 */
		@Override
		public void insertUpdate(DocumentEvent arg0) {
			setClock();
		}

		/* 
		 * @see javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.DocumentEvent)
		 */
		@Override
		public void removeUpdate(DocumentEvent arg0) {
			setClock();
		}
	}
}

class ClockPanel extends JPanel{
	
	public ClockPanel(){
		setPreferredSize(new Dimension(2 * RADIUS, 2 * RADIUS + 1));
	}
	
	@Override
	public void paintComponent(Graphics g){
		//draw the circular boundary
		 super.paintComponent(g);
		 Graphics2D g2 = (Graphics2D) g;
		 Ellipse2D circle = new Ellipse2D.Double(0, 0, 2 * RADIUS, 2 * RADIUS);
		 g2.draw(circle);
		 
		 //draw the hour hand
		 double hourAngle = Math.toRadians(90 - 360 * minutes / (12 * 60));
		 drawHand(g2, hourAngle, HOUR_HAND_LENGTH);
		 
		//draw the minute hand
		 double minuteAngle = Math.toRadians(90 - 360 * minutes / (60));
		 drawHand(g2, minuteAngle, MINUTES_HAND_LENGTH);
		 
	}

	/**
	 * @param g2
	 * @param angle
	 * @param handLength
	 */
	private void drawHand(Graphics2D g2, double angle,
			double handLength) {
		Point2D end = new Point2D.Double(
				RADIUS + handLength * Math.cos(angle),
				RADIUS + handLength * Math.sin(angle));
		Point2D center = new Point2D.Double(RADIUS, RADIUS);
		g2.draw(new Line2D.Double(center, end));
		
	}

	/**
	 * setting time and display on the clock
	 * @param h
	 * @param m
	 */
	public void settime(int h, int m) {
		minutes = h * 60 + m; 
		repaint();

	}
	
	
	private int RADIUS = 200;
	private double minutes = 0;
	private double MINUTES_HAND_LENGTH = 0.8 * RADIUS;
	private double HOUR_HAND_LENGTH = 0.6 * RADIUS;

}


/*
 * A frame with a panel for sketching a figure
 * */



