/**
 * 
 */
package SwingDemo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;



/**
 * @author zfh1005
 */
public class ActionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ActionFrame bf = new ActionFrame();
		bf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bf.setVisible(true);
	}

}

/*
 * a frame with a button panel
 * */
class ActionFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8019069859833188217L;
	public ActionFrame(){
		setTitle("ActionTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		//add panel to frame
		ActionPanel panel = new ActionPanel();
		add(panel);
	}

	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 300;
}


class ActionPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4986977103595765443L;
	
	public ActionPanel(){
		Action redAction = new ColorAction("Red", null, Color.RED);
		Action greenAction = new ColorAction("Green", null, Color.GREEN);
		Action blueAction = new ColorAction("Blue", null, Color.BLUE);

		JButton redButton = new JButton(redAction);
		JButton greenButton = new JButton(greenAction);
		JButton blueButton = new JButton(blueAction);

		add(redButton);
		add(greenButton);
		add(blueButton);
		
		InputMap imap = getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		imap.put(ctrlRKey, "panel.red");
		imap.put(ctrlGKey, "panel.green");
		imap.put(ctrlBKey, "panel.blue");
		
		ActionMap amap = getActionMap();
		amap.put("panel.red", redAction);
		amap.put("panel.green", greenAction);		
		amap.put("panel.blue", blueAction);
		
		
	}

	class ColorAction extends AbstractAction{
		/**
		 * 
		 */
		private static final long serialVersionUID = 4114105592409181724L;

		public ColorAction(String name, Icon icon, Color c){
			putValue(Action.NAME , name);
			putValue(Action.SMALL_ICON, icon);
			putValue("color", c);
			putValue(Action.SHORT_DESCRIPTION, "Set panel color to" + name.toLowerCase());
		}

		/* 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Color c = (Color) getValue("color");
			setBackground(c);
		}
	}
	
	KeyStroke ctrlRKey = KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK);
	KeyStroke ctrlGKey = KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK);
	KeyStroke ctrlBKey = KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK);
}

