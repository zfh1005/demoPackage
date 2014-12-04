/**
 * 
 */
package Network.InterruptibleSocketTest;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JTextArea;

/**
 * This class handles the client input for one server socket connection.
 * @author zfh1005
 *
 */
public class TestServerHandler implements Runnable{

	/*
	 * Constructs a handler.
	 * */
	public TestServerHandler(Socket iSocket, JTextArea message){
		incomingSocket = iSocket;
		messageArea = message;
	}
	
	/* 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try{
			OutputStream outputStream = incomingSocket.getOutputStream();
			PrintWriter outPrintWriter = new PrintWriter(outputStream, true);
			while(counter < 100){
				counter ++;
				if(counter <= 10){
					outPrintWriter.println(counter);
				}
				Thread.sleep(100);
			}
			
			incomingSocket.close();
			messageArea.append("Closing server\n.");
		}
		catch(Exception exception){
			messageArea.append("\nTestServerHandler.run: " + exception);
		}		
	}	
	
	private Socket incomingSocket;
	private int counter;
	private JTextArea messageArea;

}
