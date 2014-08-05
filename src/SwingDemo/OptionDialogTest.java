/**
 * 
 */
package SwingDemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * @author zfh1005
 */
public class OptionDialogTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		OptionDialogFrame frame = new OptionDialogFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}

/*
 * a panel 
 * */

class ButtonPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6423360377739558380L;

	/*
	 * contructs a button panel
	 * 
	 * */
	
	public ButtonPanel(String title, String[] options){
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),title));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		group = new ButtonGroup();
		//make one radio button for each option
		for(int i = 0; i < options.length; i++){
			JRadioButton b = new JRadioButton(options[i]);
			b.setActionCommand(options[i]);
			add(b);
			group.add(b);
			b.setSelected(i == 0);			
		}		
	}
	
	/*
	 * get the currently selected option.
	 * @return the label of the currently selected radio button
	 * */
	public String getSelection(){
		return group.getSelection().getActionCommand();
	}
	
	private ButtonGroup group;
}



class OptionDialogFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5440953701887729757L;
	
	public OptionDialogFrame(){
		setTitle("OptionDialog");
		setSize(WIDTH, HEIGHT);
		
		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(2, 3));
		
		typePanel = new ButtonPanel("Type",
				new String[]{
				"Message", "Confirm", "Option", "Iput"
		});
		
		messageTypePanel = new ButtonPanel("Message type",
				new String[]{
				"ERROR_MESSAGE", "INFORMATION_MESSAGE", "WARNING_MESSAGE", "QUESTION_MESSAGE", "PLAIN_MESSAGE"
		});
		
		messagePanel = new ButtonPanel("Message",
				new String[]{
				"String", "Icon", "Component", "Other", "Object[]"
		});
		
		optionTypePanel = new ButtonPanel("Confirm",
				new String[]{
				"DEFAULT_OPTION", "YES_NO_OPTION", "YES_NO_CANCEL_OPTION", "OK_CANCEL_OPTION"
		});
		
		optionsPanel = new ButtonPanel("Option",
				new String[]{
				"String[]", "Icon[]", "Object[]"
		});
		
		inputPanel = new ButtonPanel("input",
				new String[]{
				"Text field", "Combo box"
		});
		
		gridPanel.add(typePanel);
		gridPanel.add(messagePanel);
		gridPanel.add(messageTypePanel);
		gridPanel.add(optionsPanel);
		gridPanel.add(optionTypePanel);
		gridPanel.add(inputPanel);

		//add a panel with a show button
		JPanel showPanel = new JPanel();
		JButton showButton = new JButton("Show");
		showButton.addActionListener(new ShowAction());
		showPanel.add(showButton);
		
		add(gridPanel, BorderLayout.CENTER);
		add(showPanel, BorderLayout.SOUTH);
	}
	
	
	/*
	 * 
	 * get currently selected message
	 * 	
	**/
	public Object getMessage(){
		String s = messagePanel.getSelection();
		if(s.equals("String")){
			return messageString;
		}
		else if(s.equals("Icon")){
			return messageIcon;
		}
		else if(s.equals("Component")){
			return messageComponent;			
		}
		else if(s.equals("Object[]")){
			return new Object[]{
					messageString, 
					messageIcon, 
					messageComponent, 
					messageObject
			};
		}
		else if(s.equals("Other")){
			return messageObject;
		}
		else{
			return null;
		}
	}
	
	/*
	 * 
	 * get currently selected option
	 * 	
	**/
	public Object[] getOption(){
		String s = optionsPanel.getSelection();
		if(s.equals("String[]")){
			return new String[] {
					"Red", 
					"Green", 
					"Blue"
			};
		}
		else if(s.equals("Icon")){
			return new Icon[]{
				new ImageIcon("Red.gif"),
				new ImageIcon("Green.gif"),
				new ImageIcon("Blue.gif")
			};
		}
		else if(s.equals("Object[]")){
			return new Object[]{
				messageString, 
				messageIcon,
				messageComponent,
				messageObject
			};
		}
		else{
			return null;
		}
	}
	
	/*
	 * 
	 * get currently selected type
	 * 	
	**/
	public int getType(ButtonPanel panel){
		String s = panel.getSelection();
		try {
			return JOptionPane.class.getField(s).getInt(null);
		} 
		catch (IllegalArgumentException | IllegalAccessException
				| NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	
	
	
	/*
	 * the action listener for the show button shows 
	 * 
	 * */
	private class ShowAction implements ActionListener{

		/* 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(typePanel.getSelection().equals("Confirm")){
				JOptionPane.showConfirmDialog(
						OptionDialogFrame.this, 
						getMessage(),
						"Title", 
						getType(optionTypePanel), 
						getType(messageTypePanel));
			}
			else if(typePanel.getSelection().equals("Input")){
				if(inputPanel.getSelection().equals("Test field")){
					JOptionPane.showConfirmDialog(
							OptionDialogFrame.this, 
							getMessage(),
							"Title", 
							getType(optionTypePanel));
				}
				else{
					JOptionPane.showInputDialog(
							OptionDialogFrame.this, 
							getMessage(),
							"Title", 
							getType(messageTypePanel), 
							null, 
							new String[]{"Red", "Green", "Blue"}, 
							"Blue");
				
				}
			}
			else if(typePanel.getSelection().equals("Message")){
				JOptionPane.showConfirmDialog(
						OptionDialogFrame.this, 
						getMessage(),
						"Title", 
						getType(optionTypePanel));
			
			}
			else if(typePanel.getSelection().equals("Option")){
				JOptionPane.showOptionDialog(
						OptionDialogFrame.this, 
						getMessage(),
						"Title", 
						getType(optionTypePanel), 
						getType(messageTypePanel), 
						null, 
						getOption(), 
						getOption()[0]);			
			}
		}
		
	}
	
	
	private int WIDTH = 600;
	private int HEIGHT = 400;
	
	private ButtonPanel typePanel;
	private ButtonPanel messagePanel;
	private ButtonPanel messageTypePanel;
	private ButtonPanel optionsPanel;
	private ButtonPanel optionTypePanel;
	private ButtonPanel inputPanel;

	private String messageString = "message";
	private Icon messageIcon = new ImageIcon("blue.gif");
	private Object messageObject = new Date();
	private Component messageComponent = new SamplePanel();	
	
}



class SamplePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5062953216484146013L;

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		Rectangle2D rect = new Rectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1);
		g2.setPaint(Color.YELLOW);
		g2.fill(rect);
		g2.setPaint(Color.BLUE);
		g2.draw(rect);
	}
	
	public Dimension getMininumSize(){
		return new Dimension(10, 10);
	}
	
}




