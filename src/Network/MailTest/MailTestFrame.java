/**
 * 
 */
package Network.MailTest;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import org.omg.CORBA.COMM_FAILURE;

import SwingDemo.GBC;

/**
 * The frame for the mail GUI
 * @author zfh1005
 *
 */
public class MailTestFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4579387454147561471L;
	public MailTestFrame(){
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle("MailTest");
		
		setLayout(new GridBagLayout());
		
		add(new JLabel("From:"), new GBC(0, 0).setFill(GBC.HORIZONTAL));
		frommField = new JTextField(20);
		add(frommField, new GBC(1, 0).setFill(GBC.HORIZONTAL).setWeight(100, 0));
	
		add(new JLabel("To:"), new GBC(0, 1).setFill(GBC.HORIZONTAL));
		toField = new JTextField(20);
		add(toField, new GBC(1, 1).setFill(GBC.HORIZONTAL).setWeight(100, 0));
		
		add(new JLabel("SMTP Server: "), new GBC(0, 2).setFill(GBC.HORIZONTAL));
		smtpServer = new JTextField(20);
		add(smtpServer, new GBC(1, 2).setFill(GBC.HORIZONTAL).setWeight(100, 0));
		
		messageArea = new JTextArea();
		add(new JScrollPane(), new GBC(0, 3, 2, 1).setFill(GBC.BOTH).setWeight(100, 100));
		
		comnArea = new JTextArea();
		add(new JScrollPane(), new GBC(0, 4, 2, 1).setFill(GBC.BOTH).setWeight(100, 100));
	
		JPanel buttonpPanel = new JPanel();
		add(buttonpPanel, new GBC(0, 5, 2, 1));
		
		JButton sendbButton = new JButton("Send");
		buttonpPanel.add(sendbButton);
		sendbButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new SwingWorker<Void, Void>() {
					@Override
					protected Void doInBackground() throws Exception {
						comnArea.setText("");
						sendMail();
						return null;
					}
				}.execute();
			}
		});
	}
	
	/*
	 * Sends the mail message that has been authored in the GUI.
	 * */
	public void sendMail() {
		try {
			Socket socket = new Socket(smtpServer.getText(), 25);
			
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			inScanner = new Scanner(inputStream);
			outPrintWriter = new PrintWriter(outputStream, true);
			
			String hostName = InetAddress.getLocalHost().getHostName();
			
			receive();
			
			send("Hello" + hostName);
			receive();
			send("Mail From: < " + frommField.getText() + ">");
			receive();
			send("Mail To: < " + toField.getText() + ">");	
			receive();
			send("DATA");	
			receive();
			send(messageArea.getText());
			send(".");
			receive();
			socket.close();
		}
		catch (Exception e) {
			comnArea.append("Error:" + e);
		}
	}
	
	public void send(String string) throws IOException {
		comnArea.append(string);
		comnArea.append("\n");
		outPrintWriter.print(string.replace("\n", "\r\n"));
		outPrintWriter.print("\r\n");
		outPrintWriter.flush();
	}
	
	public void receive() throws IOException{
		String line = inScanner.nextLine();
		comnArea.append(line);
		comnArea.append("\n");
	}
	
	private Scanner inScanner;
	private PrintWriter outPrintWriter;
	private JTextField frommField;
	private JTextField toField;
	private JTextField smtpServer;
	private JTextArea messageArea;
	private JTextArea comnArea;
	
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 300;
	
	
}
