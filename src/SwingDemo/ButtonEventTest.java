/**
 * 
 */
package SwingDemo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author zfh1005
 */
public class ButtonEventTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ButtonFrame bf = new ButtonFrame();
		bf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bf.setVisible(true);
	}

}

/*
 * a frame with a button panel
 * */
class ButtonFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8019069859833188217L;
	public ButtonFrame(){
		setTitle("ButtonTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		//add panel to frame
		//ButtonPanel_1 panel = new ButtonPanel_1();
		//ButtonPanel_2 panel = new ButtonPanel_2();
		ButtonPanel_3 panel = new ButtonPanel_3();
		add(panel);
	}

	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 300;
}


/*
 * a panel with three buttons
 * */

class ButtonPanel_1 extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6596506527373351814L;

	public ButtonPanel_1(){
		makeButton("Red", Color.RED );
		makeButton("Green", Color.GREEN );
		makeButton("Blue", Color.BLUE );
	}
	
	public void makeButton(String name, Color background){
		JButton button = new JButton(name);
		add(button);
		ColorAction action = new ColorAction(background);
		button.addActionListener(action);
	}


	private class ColorAction implements ActionListener{
		public ColorAction(Color c){
			backgrounColor = c;
		}

		/* 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//set panel background color
			setBackground(backgrounColor);
		}
		private Color backgrounColor;
	}
}

class ButtonPanel_2 extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1710228415715783550L;

	public ButtonPanel_2(){
		redButton = new JButton("Red");
		greenButton = new JButton("Green");
		blueButton = new JButton("Blue");
		
		add(redButton);
		add(greenButton);
		add(blueButton);
		
		redButton.addActionListener(this);
		greenButton.addActionListener(this);
		blueButton.addActionListener(this);		
	}
	
	
	/* 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//EventObject event = new EventObject(this);
		Object source = arg0.getSource();
		if(source == redButton){			
			setBackground(Color.RED);
		}
		if(source == greenButton){			
			setBackground(Color.GREEN);
		}
		if(source == blueButton){			
			setBackground(Color.BLUE);
		}
	}

	private JButton redButton;
	private JButton greenButton;
	private JButton blueButton;	
}


class ButtonPanel_3 extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1710228415715783550L;

	public ButtonPanel_3(){
		redButton = new JButton(redString);
		greenButton = new JButton(greenString);
		blueButton = new JButton(blueString);
		
		add(redButton);
		add(greenButton);
		add(blueButton);
		
		redButton.addActionListener(this);
		greenButton.addActionListener(this);
		blueButton.addActionListener(this);
	}
	
	/* 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//EventObject event = new EventObject(this);
		String command = arg0.getActionCommand();
		if(command.contains(redString)){			
			setBackground(Color.RED);
		}
		if(command.contains(greenString)){				
			setBackground(Color.GREEN);
		}
		if(command.contains(blueString)){			
			setBackground(Color.BLUE);
		}
	}

	private JButton redButton;
	private JButton greenButton;
	private JButton blueButton;	
	
	private String redString = "Red";
	private String greenString = "Green";
	private String blueString = "Blue";
	
}
