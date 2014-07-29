/**
 * 
 */
package SwingDemo;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author zfh1005
 */
public class Calculator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CalculatorFrame bf = new CalculatorFrame();
		bf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bf.setVisible(true);
	}

}

/*
 * a frame with a button panel
 * */
class CalculatorFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8019069859833188217L;
	public CalculatorFrame(){
		setTitle("CalculatorTest");
		//setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		//add panel to frame
		CalculatorPanel panel = new CalculatorPanel();
		add(panel);
		pack();
	}

	//private static final int DEFAULT_WIDTH = 400;
	//private static final int DEFAULT_HEIGHT = 300;
}


class CalculatorPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2403300694647433682L;
	
	public CalculatorPanel(){
		setLayout(new BorderLayout());
		
		result = 0;
		lastCommand = "=";
		start = true;
		
		//add the display
		
		display = new JButton("0");
		display.setEnabled(false);
		add(display, BorderLayout.NORTH);
		
		ActionListener insert = new InsertAction();
		ActionListener command = new CommandAction();
		
		//add the buttons in a 4x4 grid
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(4, 4));
		
		addButton("7", insert);
		addButton("8", insert);
		addButton("9", insert);
		addButton("/", command);
		
		addButton("4", insert);
		addButton("5", insert);
		addButton("6", insert);
		addButton("*", command);
		
		addButton("1", insert);
		addButton("2", insert);
		addButton("3", insert);
		addButton("-", command);
		
		addButton("0", insert);
		addButton(".", insert);
		addButton("=", command);
		addButton("+", command);
		
		add(panel, BorderLayout.CENTER);
		
	}
	
	/*
	 * add a button to the center panel
	 * */
	
	private void addButton(String label, ActionListener listener){
		JButton button = new JButton(label);
		button.addActionListener(listener);
		panel.add(button);		
	}
	
	private class InsertAction implements ActionListener{

		/* 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String input = arg0.getActionCommand();
			if(start){
				display.setText("");
				start = false;
			}
			display.setText(display.getText() + input);
			
		}
		
	}
	
	
	private class CommandAction implements ActionListener{

		/* 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String command = arg0.getActionCommand();
			if(start){
				if(command.equals("-")){
					display.setText(command);
					start = false;
				}
				else{
					lastCommand = command;
				}
			}
			else{
				calculate(Double.parseDouble(display.getText()));
				lastCommand = command;
				start = true;
			}			
		}		
	}
	
	/*
	 * Carries out the pending calculation
	 * 
	 * */
	public void calculate(double x){
		if(lastCommand.equals("+")){
			result += x;
		}
		else if(lastCommand.equals("-")){
			result -= x;
		}
		else if(lastCommand.equals("/")){
			result /= x;
		}
		else if(lastCommand.equals("*")){
			result *= x;
		}
		else if(lastCommand.equals("=")){
			result = x;
		}
		
		display.setText("" + result);
		
	}

	private JButton display;
	private JPanel panel;
	private double result;
	private String lastCommand;
	private boolean start;

}

















