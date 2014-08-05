/**
 * 
 */
package SwingDemo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * @author zfh1005
 */
public class DialogTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		DialogFrame frame = new DialogFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/*
 * a JFrame 
 * */

class DialogFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7355079599532337164L;
	
	public DialogFrame(){
		setTitle("Dialog Test");
		setSize(WIDTH, HEIGHT);
		
		//construct a file menu
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		//add about and exit menu items
		//the about item shows the about dialog
		JMenuItem aboutItem = new JMenuItem("Ablut");
		aboutItem.addActionListener(
				new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(dialog == null){
					dialog = new AboutDialog(DialogFrame.this);
				}				
				dialog.setVisible(true);
			}
			
		});
		fileMenu.add(aboutItem);
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
			
		});
		fileMenu.add(exitItem);
		
		
	}	
	
	private int WIDTH = 300;
	private int HEIGHT = 200;	
	private AboutDialog dialog;
	
}


class AboutDialog extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2481491313912616164L;

	public AboutDialog(JFrame owner){
		super(owner, "About DialogTest", true);
		
		//add HTML label to center
		add(new JLabel(
			"<html><h1><i>Core Java</i></h1></hr>By cay horstamann</html>"), BorderLayout.CENTER);
		
		//ok button closes the dialog
		JButton ok = new JButton("Ok");
		ok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
			
		});
		
		//add ok button to sourthern border
		JPanel panel = new JPanel();
		panel.add(ok);
		add(panel, BorderLayout.SOUTH);
		setSize(200, 150);	
		
	}	
	
}
