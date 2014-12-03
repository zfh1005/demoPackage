/**
 * 
 */
package Network.InterruptibleSocketTest;

import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;

/**
 * A multithreaded server that listens to port 8189 and sends number to this client,
 * simulating a hanging server after 10 numbers.
 * @author zfh1005
 *
 */
public class TestServer implements Runnable{

	public TestServer(JTextArea message){
		messageArea = message;
	}
	/* 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try{
			ServerSocket serverSocket = new ServerSocket(8189);
			while(true){
				Socket incomingSocket = serverSocket.accept();
				Runnable runnable = new TestServerHandler(incomingSocket, messageArea);
				Thread thread = new Thread(runnable);
				thread.start();
			}
		}
		catch(Exception exception){
			messageArea.append("\nTestServer.run:" + exception);
		}		
	}
	
	private JTextArea messageArea;
}
