/**
 * 
 */
package SwingDemo;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

/**
 * @author zfh1005
 */
public class BorderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BorderFrame frame = new BorderFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/*
 * A frame with a panel 
 * */
class BorderFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8076093017234961962L;
	
	public BorderFrame(){
		setTitle("BorderTest");
		setSize(WIDTH, HEIGTH);
		
		demoPanel = new JPanel();
		buttonPanel = new JPanel();
		group = new ButtonGroup();
		
		addRadioButton("Lower Bevel", BorderFactory.createLoweredBevelBorder());
		addRadioButton("Radio Bevel", BorderFactory.createRaisedBevelBorder());
		addRadioButton("Etched", BorderFactory.createEtchedBorder());
		addRadioButton("Line", BorderFactory.createLineBorder(Color.BLUE));
		addRadioButton("Matte", BorderFactory.createMatteBorder(10, 10, 10, 12, Color.BLUE));
		addRadioButton("Empty", BorderFactory.createEmptyBorder());
		
		Border etched = BorderFactory.createEtchedBorder();
		Border titled = BorderFactory.createTitledBorder(etched, "Border typed");
		buttonPanel.setBorder(titled);
		
		setLayout(new GridLayout(2, 1));
		add(buttonPanel);
		add(demoPanel);
	}
	
	public void addRadioButton(String buttonName, final Border b){
		JRadioButton button = new JRadioButton(buttonName);
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				demoPanel.setBorder(b);
				validate();
			}			
		});
		group.add(button);
		buttonPanel.add(button);
	}
	
	
	
	private int WIDTH = 600;
	private int HEIGTH = 200;
	
	private JPanel demoPanel;
	private JPanel buttonPanel;
	private ButtonGroup group;
}