/**
 * 
 */
package SwingDemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 * @author zfh1005
 */
public class ToolBarTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ToolBarFrame frame = new ToolBarFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/*
 * A frame with a panel for sketching a figure
 * */
class ToolBarFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2194595684397365143L;
	public ToolBarFrame(){
		setTitle("ToolBar");
		setSize(WIDTH, HEIGTH);
		
		//add panel
		panel = new JPanel();
		
		//set up action
		Action redAction = new ColorAction("Red", new ImageIcon("Red.gif"), Color.RED);
		Action greenAction = new ColorAction("Green", new ImageIcon("Green.gif"), Color.GREEN);
		Action blueAction = new ColorAction("Blue", new ImageIcon("Blue.gif"), Color.BLUE);
	
		Action exitAction = new AbstractAction("Exit", new ImageIcon("Exit.gif")){

			/**
			 * 
			 */
			private static final long serialVersionUID = 5810958459376360164L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		};
		exitAction.putValue(Action.SHORT_DESCRIPTION, "Exit");
		
		//populate toolbar		
		JToolBar bar = new JToolBar();
		bar.add(redAction);
		bar.add(greenAction);
		bar.add(blueAction);		
		bar.addSeparator();		
		bar.add(exitAction);		
		add(bar, BorderLayout.NORTH);
		
		//populate menu
		JMenu menu = new JMenu("Color");
		menu.add(greenAction);
		menu.add(redAction);
		menu.add(blueAction);
		menu.add(exitAction);
		JMenuBar menuBar  = new JMenuBar();
		menuBar.add(menu);
		setJMenuBar(menuBar);
	}
		
	
	
	class ColorAction extends AbstractAction{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 8563500362563151184L;

		public ColorAction(String name, Icon icon, Color c){
			putValue(Action.NAME, name);
			putValue(Action.SMALL_ICON, icon);
			putValue(Action.SHORT_DESCRIPTION, name + "background");
			putValue("Color", c);
		}

		/* 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Color c = (Color)getValue("Color");
			panel.setBackground(c);
		}
	}
	
	
	private int WIDTH = 300;
	private int HEIGTH = 200;
	private JPanel panel;

}
