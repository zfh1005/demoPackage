/**
 * 
 */
package SwingDemo;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * @author zfh1005
 */
public class RadioButtonTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		RadioButtonFrame frame = new RadioButtonFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/*
 * A frame with a panel for sketching a figure
 * */
class RadioButtonFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 421197509690318440L;

	public RadioButtonFrame(){
		setTitle("RadioButton");
		setSize(WIDTH, WEITH);
		
		//add the sample text label
		label = new JLabel("The quick brown fox jumps over the dog!") ;
		label.setFont(new Font("Serif", Font.PLAIN, DEFAULT_SIZE));
		add(label, BorderLayout.CENTER);
		
		//add the radio buttons
		buttonPanel = new JPanel();
		group = new ButtonGroup();
		
		addRadioButton("Small", 8);
		addRadioButton("Medium", 15);
		addRadioButton("Large", 30);
		addRadioButton("Extra Large", 40);
		
		add(buttonPanel, BorderLayout.SOUTH);		
	}
	
	
	/**
	 * @param string
	 * @param i
	 */
	private void addRadioButton(String name, final int size) {
		boolean selected = (size == DEFAULT_SIZE);
		JRadioButton button = new JRadioButton(name, selected);
		group.add(button);
		buttonPanel.add(button);
		
		//add listener sets label size
		ActionListener listener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				label.setFont(new Font("Serif", Font.PLAIN, size));				
			}			
		};
		button.addActionListener(listener);	
	}
	private static final int WIDTH = 600;
	private static final int WEITH = 400;
	private JPanel buttonPanel;
	private ButtonGroup group;
	private JLabel label;
	
	private static final int DEFAULT_SIZE = 20;
}
