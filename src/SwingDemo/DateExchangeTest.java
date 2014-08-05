/**
 * 
 */
package SwingDemo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * @author zfh1005
 */
public class DateExchangeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		DataExchangeFrame frame = new DataExchangeFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/*
 * a JFrame 
 * */

class DataExchangeFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6412620980811256811L;
	/**
	 * 
	 */

	public DataExchangeFrame(){
		setTitle("DateExchange");
		setSize(WIDTH, HEIGHT);

		//construct a file menu
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);


		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}

		});
		fileMenu.add(exitItem);
		

		//construct a connect menu
		JMenuItem connectItem = new JMenuItem("Connect");
		connectItem.addActionListener(new ConnectAction());
		menuBar.add(connectItem);
		
		
		textArea = new JTextArea();
		add(new JScrollPane(textArea), BorderLayout.CENTER);

	}

	private int WIDTH = 300;
	private int HEIGHT = 200;
	private PasswordChooser dialog = null;
	private JTextArea textArea;
	
	/*
	 * the connect action pops up the password dialog
	 * */
	
	private class ConnectAction implements ActionListener{

		/* 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//if first time, construct dialog
			if(dialog == null){
				dialog = new PasswordChooser();
			}
			
			//set default values
			dialog.setUser(new User("", null));
			
			//pop up dialog
			if(dialog.showDialog(DataExchangeFrame.this, "Connect")){
				//if accepted, retrieve user input
				User u = dialog.getUser();
				textArea.append(
						"user name = " + u.getName() +
						", password = " + (new String(u.getPassword())) +
						"\n"
						);
			}			
		}
	}
}

class PasswordChooser extends JPanel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -442297511224002116L;
	public PasswordChooser(){
		setLayout(new BorderLayout());
		
		//construct a panel with user name and password fields
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		panel.add(new JLabel("User name:"));
		panel.add(username = new JTextField(""));
		panel.add(new JLabel("Password"));
		panel.add(password = new JPasswordField(""));
		add(panel, BorderLayout.CENTER);
		
		//create Ok buttons that terminate the dialog
		okButton = new JButton("Ok");
		okButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ok = true;
				dialog.setVisible(false);
			}			
		});
		
		//create cancel buttons that terminate the dialog
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dialog.setVisible(false);
			}			
		});
		
		//add buttons to southern border
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		add(buttonPanel, BorderLayout.SOUTH);
		
	}
	
	/*
	 * set use dialog default information
	 * */
	public void setUser(User u){
		username.setText(u.getName());
	}
	
	/*
	 * get the user dialog entries
	 * 
	 * */
	public User getUser(){
		return new User(username.getText(), password.getPassword());
		
	}
	
	/*
	 * show the chooser panel in a dialog
	 * 
	 * */
	public boolean showDialog(Component parent, String title){
		ok = false;
		
		//locate the owner frame
		JFrame owner = null;
		if(parent instanceof JFrame){
			owner = (JFrame) parent;
		}
		else{
			owner = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, parent);
		}
		
		//if first time, or if owner has changed, make new dialog
		if((dialog == null) || (dialog.getOwner() != owner)){
			dialog = new JDialog(owner, true);
			dialog.add(this);
			dialog.getRootPane().setDefaultButton(okButton);
			dialog.pack();
		}
		
		//set title and show dialog
		dialog.setTitle(title);
		dialog.setVisible(true);
		return ok;			
		
	}
	
	private JTextField username;
	private JPasswordField password;
	private JButton okButton;
	private boolean ok;
	private JDialog dialog;
	
}

class User{
	public User(String Name, char[] Password){
		name = Name;
		password = Password;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char[] getPassword() {
		return password;
	}
	public void setPassword(char[] password) {
		this.password = password;
	}


	private String name;
	private char[] password;
}




