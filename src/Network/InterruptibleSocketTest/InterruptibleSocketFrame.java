/**
 * 
 */
package Network.InterruptibleSocketTest;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import javax.management.loading.PrivateClassLoader;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author zfh1005
 *
 */
public class InterruptibleSocketFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5557583240527263900L;

	public InterruptibleSocketFrame(){
		setSize(WIDTH, HEIGHT);
		setTitle("InterruptibleSocketTest");
		
		JPanel northJPanel = new JPanel();
		add(northJPanel, BorderLayout.NORTH);
		
		messageArea = new JTextArea();
		add(new JScrollPane(messageArea));
		
		//interruptibleButton  button
		interruptibleButton = new JButton("Interruptible");		
		northJPanel.add(interruptibleButton);
		
		//interruptibleButton ActionListener
		interruptibleButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				interruptibleButton.setEnabled(false);
				blockingButton.setEnabled(false);
				cancelButton.setEnabled(true);
				
				connectThread = new Thread(new Runnable() {					
					@Override
					public void run() {
						try{
							connectInterruptibly();
						}
						catch(IOException exception){
							messageArea.append("\nInterruptibleSocketFrame.connectInterruptibly: " + exception);
						}						
					}
				});
				connectThread.start();
				
			}
		});
		
		//blocking button
		blockingButton = new JButton("Blocking");
		northJPanel.add(blockingButton);
		
		//blockingButton ActionListener
		blockingButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				interruptibleButton.setEnabled(false);
				blockingButton.setEnabled(false);
				cancelButton.setEnabled(true);
				
				connectThread = new Thread(new Runnable() {					
					@Override
					public void run() {
						try{
							connectBlocking();
						}
						catch(IOException exception){
							messageArea.append("\nInterruptibleSocketFrame.connectBlocking: " + exception);
						}						
					}
				});
				connectThread.start();				
			}
		});
		
		cancelButton = new JButton("Cancel");
		cancelButton.setEnabled(false);
		northJPanel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				connectThread.interrupt();
				cancelButton.setEnabled(false);				
			}
		});
		
		server = new TestServer();
		new Thread(server).start();
	}
	
	/*
	 * Connects to the test server, using interruptible I/O
	 * */
	public void connectInterruptibly() throws IOException {
		messageArea.append("Interruptible:\n");
		SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8189));
		try {
			in = new Scanner(channel);
			while(!Thread.currentThread().isInterrupted()){
				messageArea.append("Reading ");
				if(in.hasNextLine()){
					String line = in.nextLine();
					messageArea.append(line);
					messageArea.append("\n");
				}
			}
		} 
		finally{
			channel.close();
			EventQueue.invokeLater(new Runnable() {				
				@Override
				public void run() {
					messageArea.append("Channel closed\n");
					interruptibleButton.setEnabled(true);
					blockingButton.setEnabled(true);					
				}
			});
		}
	}
	
	/*
	 * Connects to the test server, using blocking I/O
	 * */
	public void connectBlocking() throws IOException {
		messageArea.append("Blocking:\n");
		Socket socket = new Socket("localhost", 8189);
		try{
			in = new Scanner(socket.getInputStream());
			while(!Thread.currentThread().isInterrupted()){
				messageArea.append("Reading ");
				if(in.hasNextLine()){
					String line = in.nextLine();
					messageArea.append(line);
					messageArea.append("\n");
				}
			}
		}
		finally{
			socket.close();
			EventQueue.invokeLater(new Runnable() {				
				@Override
				public void run() {
					messageArea.append("Socket closed\n");
					interruptibleButton.setEnabled(true);
					blockingButton.setEnabled(true);
					
				}
			});
		}
	}
	
	private Scanner in;
	private JButton interruptibleButton;
	private JButton blockingButton;
	private JButton cancelButton;
	private JTextArea messageArea;
	private TestServer server;
	private Thread connectThread;
	
	private final static int WIDTH =  300;
	private final static int HEIGHT =  300;
}
