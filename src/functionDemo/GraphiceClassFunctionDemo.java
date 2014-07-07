package functionDemo;

/**
 * @author zfh1005
 *
 */

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

public class GraphiceClassFunctionDemo extends Applet {

	/**
	 * serialVersionUID format:
	 * year(4)+month(2)+data(2)+orderCode(4)L
	 */
	private static final long serialVersionUID = 201407070001L;
	
	int i = 1;
	
	@Override
	public void init(){
		setBackground(Color.black);
		getBackground();
	}
	
	@Override
	public void paint(Graphics g){
		g.setColor(Color.red);
		g.drawLine(10, 20, 30, 40);
		g.drawRect(30, 40, 10, 20);
		g.setColor(Color.yellow);
		g.fillRect(40, 60, 10, 20);		
		g.drawString("Updata", 50, 90);
		g.drawOval(80, 120, 30, 20);
		g.fillOval(110, 140, 20, 30);		
		g.drawArc(130, 170, 30, 30, 0, 50);		
		g.copyArea(80, 120, 32, 22, 90, 80);
		
		int px1[] = {250, 290, 210, 250};
		int py1[] = {210, 250, 250, 210};
		g.drawPolygon(px1, py1, 3);
		
		g.setColor(getBackground());		
		g.fillRect(40, 50, 40, 40);

	}	
	
	public static void main(String[] args) {
		GraphiceClassFunctionDemo gcfd = new GraphiceClassFunctionDemo();
	
	}

}
