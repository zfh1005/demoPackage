/**
 * 
 */
package SwingDemo;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author zfh1005
 */
public class MouseTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		MouseFrame frame = new MouseFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/*
 * A frame with a panel for sketching a figure
 * */
class MouseFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3086385924287683369L;
	
	public MouseFrame(){
		setTitle("MouseTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		//add panel to frame
		MousePanel panel = new MousePanel();
		add(panel);
	}

	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 600;
}

class MousePanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2939659134096480179L;
	public MousePanel(){
		squares = new ArrayList<Rectangle2D>();
		current = null;
		addMouseListener(new MouseHandler());
		addMouseMotionListener(new MouseMotionHandler());
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		//draw all squares
		for(Rectangle2D r : squares ){
			g2.draw(r);
		}		
	}
	 
	/*
	 * find the first square containing a point
	 * @param p a point
	 * @return the first square that continue p
	 * 
	 **/
	public Rectangle2D find(Point2D p){
		for(Rectangle2D r : squares){
			if(r.contains(p)){
				return r;
			}
		}
		return null;		
	}
	
	/*
	 * adds a square to the collection
	 * @param p the center of the square
	 * 
	 * */
	public void add(Point2D p){
		double x = p.getX();
		double y = p.getY();
		
		current = new Rectangle2D.Double(
				x - SIDELENGTH / 2,
				y - SIDELENGTH / 2,
				SIDELENGTH,
				SIDELENGTH);
		squares.add(current);
		repaint();		
	}
	
	/*
	 * remove a squares from the collection
	 * @param s the squares to remove
	 * */
	public void remove(Rectangle2D s){
		if(s == null){
			return;
		}
		if(s == current){
			current = null;
			squares.remove(s);
			repaint();
		}		
	}
	
	private class MouseHandler extends MouseAdapter{
		public void mousePressed(MouseEvent event){
			current = find(event.getPoint());
			if(current == null){
				add(event.getPoint());
			}
		}
		
		public void mouseClicked(MouseEvent event){
			current = find(event.getPoint());
			if((current == null) && (event.getClickCount() >= 2)){
				remove(current);
			}
		}
		
	}
	
	public static final int SIDELENGTH = 20;
	public ArrayList<Rectangle2D> squares;
	public Rectangle2D current;
	
	private class MouseMotionHandler implements MouseMotionListener{

		/* 
		 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseMoved(MouseEvent arg0) {
			//set the mouse cursor to cross hairs if it is inside a rectangle
			if(find(arg0.getPoint()) == null){
				setCursor(Cursor.getDefaultCursor());
			}
			else{
				setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			}
		}

		/* 
		 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseDragged(MouseEvent arg0) {
			if(current != null){
				int x = arg0.getX();
				int y = arg0.getY();
				
				//drag the rectangle to center it at (x,y)
				current.setFrame(
						x - SIDELENGTH / 2,
						y - SIDELENGTH / 2,
						SIDELENGTH,
						SIDELENGTH);
				repaint();
			}			
		}		
	}
}



