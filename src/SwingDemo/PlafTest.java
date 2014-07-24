/**
 * 
 */
package SwingDemo;

import javax.swing.*;
import java.awt.event.*;

/**
 * @author zfh1005
 */
public class PlafTest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PlafFrame frame = new PlafFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class PlafFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8019069859833188217L;
	public PlafFrame(){
		setTitle("PlatTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		//add panel to frame
		PlafPanel panel = new PlafPanel();
		add(panel);
	}

	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 300;
}


class PlafPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3023469589433740327L;

	public PlafPanel(){
		UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
		for(UIManager.LookAndFeelInfo info : infos){
			makeButton(info.getName(), info.getClassName());
		}
	}
	
	void makeButton(String name, final String plafName){
		//add button to panel
		JButton button = new JButton(name);
		add(button);
		
		//set button icon
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				try {
					UIManager.setLookAndFeel(plafName);
					SwingUtilities.updateComponentTreeUI(PlafPanel.this);
				} 
				catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
			}
		}
		);
	}
	
}


