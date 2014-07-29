/**
 * 
 */
package SwingDemo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author zfh1005
 */
public class SampleSketch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SketchFrame frame = new SketchFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/*
 * A frame with a panel for sketching a figure
 * */
class SketchFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3086385924287683369L;
	
	public SketchFrame(){
		setTitle("Sketch");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		//add panel to frame
		ShetchPanel panel = new ShetchPanel();
		add(panel);
	}

	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 600;
}

/*
 * A panel for sketching with the keyboard
 * */
class ShetchPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5049892855975588355L;

	public ShetchPanel(){
		last = new Point2D.Double(100, 100);
		lines = new ArrayList<Line2D>();
		KeyHandler listener = new KeyHandler();
		addKeyListener(listener);
		setFocusable(true);	
	}
	
	public void add(int dx, int dy){
		//compute new end point
		Point2D end = new Point2D.Double(last.getX() + dx, last.getY() + dy);
		
		//add line segment
		Line2D line = new Line2D.Double(last, end);
		lines.add(line);
		repaint();
		
		//new end point
		last = end;		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		//draw all lines
		for(Line2D line:lines){
			g2.draw(line);
		}
	}
	
	
	private Point2D last;
	private ArrayList<Line2D> lines;
	private static final int SMALL = 10;
	private static final int LARCE = 30;
	
	private class KeyHandler implements KeyListener{
		
		/* 
		 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyPressed(KeyEvent arg0) {
			int keyCode = arg0.getKeyCode();
			
			//set distance
			int d;
			if(arg0.isShiftDown()){
				d = LARCE;				
			}
			else{
				d = SMALL;
			}
			
			//add line segment
			if(keyCode == KeyEvent.VK_LEFT){
				add(-d, 0);
			}
			else if(keyCode == KeyEvent.VK_RIGHT){
				add(d, 0);
			}
			else if(keyCode == KeyEvent.VK_UP){
				add(0, -d);
			}
			else if(keyCode == KeyEvent.VK_DOWN){
				add(0, d);
			}
		}

		/* )
		 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyReleased(KeyEvent arg0) {
			
		}

		/* 
		 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyTyped(KeyEvent arg0) {
			char keyChar  = arg0.getKeyChar();
			
			//set distance
			int d ;
			if(Character.isUpperCase(keyChar)){
				d = LARCE;
			}
			else{
				d = SMALL;
			}
			
			//add line segment
			if(keyChar == 'h'){
				add(d, 0);
			}
			else if(keyChar == 'l'){
				add(-d, 0);
			}
			else if(keyChar == 'k'){
				add(0, -d);
			}
			else if(keyChar == 'j'){
				add(0, d);
			}
		}
		
	}
	
}

