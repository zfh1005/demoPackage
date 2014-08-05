/**
 * 
 */
package SwingDemo;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author zfh1005
 */
public class CricleLayoutTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		CricleLayoutFrame frame = new CricleLayoutFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}

/*
 * A frame with a panel
 * */
class CricleLayoutFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5087035351487412153L;

	public CricleLayoutFrame(){
		setTitle("CricleLayout");
		setLayout(new CricleLayout());
		
		add(new JButton("Yellow"));
		add(new JButton("Red"));
		add(new JButton("Blue"));
		add(new JButton("Green"));
		add(new JButton("Orange"));
		add(new JButton("Fuchsia"));
	}
}


/*
 * a layout manager that lays out components along a circle
 * 
 * */
class CricleLayout implements LayoutManager{

	/* 
	 * @see java.awt.LayoutManager#addLayoutComponent(java.lang.String, java.awt.Component)
	 */
	@Override
	public void addLayoutComponent(String arg0, Component arg1) {
		
	}

	/* 
	 * @see java.awt.LayoutManager#layoutContainer(java.awt.Container)
	 */
	@Override
	public void layoutContainer(Container arg0) {
		setSize(arg0);
		
		//compute center of the circle
		Insets insets = arg0.getInsets();
		int width = arg0.getSize().width - insets.left - insets.right;
		int height = arg0.getSize().height - insets.left - insets.right;
		
		int xcenter = insets.left + width / 2;
		int ycenter = insets.top + height / 2;
		
		//compute radios of the circle
		int xradius = (width -  maxComponentWidth) / 2;
		int yradius = (height -  maxComponentHeight) / 2;
		int radius = Math.max(xradius, yradius);
		
		//layout components along the circle
		
		int n  = arg0.getComponentCount();
		for(int i = 0; i < n; i++){
			Component c = arg0.getComponent(i);
			if(c.isVisible()){
				double angle = 2 * Math.PI * i / n;
				
				//center point of component
				int x = xcenter + (int)(Math.cos(angle) * radius);
				int y = ycenter + (int)(Math.sin(angle) * radius);
				
				//move component so that its center is (x,y)
				//add its size is its preferred size
				Dimension d = c.getPreferredSize();
				c.setBounds(x - d.width / 2, y - d.height / 2, d.width, d.height);
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.LayoutManager#minimumLayoutSize(java.awt.Container)
	 */
	@Override
	public Dimension minimumLayoutSize(Container arg0) {
		setSize(arg0);
		Insets insets = arg0.getInsets();
		int width = minWidth + insets.left + insets.right;
		int height = minHeight + insets.left + insets.right;
		return new Dimension(width, height);
	}

	/* 
	 * @see java.awt.LayoutManager#preferredLayoutSize(java.awt.Container)
	 */
	@Override
	public Dimension preferredLayoutSize(Container arg0) {
		setSize(arg0);
		Insets insets = arg0.getInsets();
		int width = preferredWidth + insets.left + insets.right;
		int height = preferredHeight + insets.top + insets.bottom;
		return new Dimension(width, height);		
	}

	/* 
	 * @see java.awt.LayoutManager#removeLayoutComponent(java.awt.Component)
	 */
	@Override
	public void removeLayoutComponent(Component arg0) {
		
	}
	
	public void setSize(Container parent){
		if (sizesSet){
			return;
		}
		int n = parent.getComponentCount();
		
		minWidth = 0;
		minHeight = 0;
		preferredWidth = 0;
		preferredHeight = 0;
		maxComponentWidth = 0;
		maxComponentHeight = 0;
		
		//add set the preferred size to the sum of the componnent sizes
		for(int i = 0; i < n; i++){
			Component c = parent.getComponent(i);
			if(c.isVisible()){
				Dimension d = c.getPreferredSize();
				maxComponentWidth = Math.max(maxComponentWidth, d.width);
				maxComponentHeight = Math.max(maxComponentHeight, d.height);
				
				preferredWidth += d.width;
				preferredHeight += d.height;			
			}
		}
		minWidth = preferredWidth / 2;
		minHeight = preferredHeight / 2;
		sizesSet = true;
	}
	
	private int minWidth = 0;
	private int minHeight = 0;
	private int preferredWidth = 0;
	private int preferredHeight = 0;
	private boolean sizesSet = false;
	private int maxComponentWidth = 0;
	private int maxComponentHeight = 0;
}
