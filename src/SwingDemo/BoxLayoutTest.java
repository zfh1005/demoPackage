/**
 * 
 */
package SwingDemo;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author zfh1005
 */
public class BoxLayoutTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BoxLayoutFrame frame = new BoxLayoutFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/*
 * A frame with a panel
 * */
class BoxLayoutFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9191217261141737051L;

	public BoxLayoutFrame(){
		setTitle("BoxLayout");
		setSize(WIDTH, HEIGTH);
		
		//construct the top harizontal box
		JLabel label = new JLabel("Name:");
		JTextField textField = new JTextField(10);
		textField.setMaximumSize(textField.getPreferredSize());
		
		Box hbox1 = Box.createHorizontalBox();
		hbox1.add(label);
		//separate with a 10-pixel strut
		hbox1.add(Box.createHorizontalStrut(10));
		hbox1.add(textField);
		
		//construct the middle harizontal box
		JLabel label2 = new JLabel("Password:");
		JTextField textField2 = new JTextField(10);
		textField.setMaximumSize(textField2.getPreferredSize());
		
		Box hbox2 = Box.createHorizontalBox();
		hbox2.add(label2);
		//separate with a 10-pixel strut
		hbox2.add(Box.createHorizontalStrut(10));
		hbox2.add(textField2);
		
		//construct the button harizontal box
		JButton button1 = new JButton("Ok");		
		JButton button2 = new JButton("Cancel");
		
		Box hbox3 = Box.createHorizontalBox();
		hbox3.add(button1);
		//use "glue" to push the two buttons aport
		hbox3.add(Box.createGlue());
		hbox3.add(button2);
		
		//add the three harizontal box inside a vertical box
		Box vbox = Box.createVerticalBox();
		vbox.add(hbox1);
		vbox.add(hbox2);
		vbox.add(Box.createGlue());
		vbox.add(hbox3);
		
		add(vbox, BorderLayout.CENTER);
		
	}
	
	
	private int WIDTH = 300;
	private int HEIGTH = 200;

}
