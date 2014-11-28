/**
 * 
 */
package Network.SocketTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * This program implements a simple server socket that
 * listens to port 8189 and echoes back all client input.
 * @version 1.0.0.1 
 * @author zfh1005
 *
 */
public class EchoServerSocket {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			//establish server socket
			ServerSocket serverSocket = new ServerSocket(8189);
			
			//wait for client connection
			Socket incomingSocket = serverSocket.accept();
			
			try {
				InputStream inputStream = incomingSocket.getInputStream();
				OutputStream outputStream = incomingSocket.getOutputStream();
				
				Scanner inScanner = new Scanner(inputStream);
				PrintWriter outPrintWriter = new PrintWriter(outputStream, true);
				
				outPrintWriter.println("Hello! Entry BYE to exit.");
				
				//echo client input
				boolean done = false;
				while(!done && inScanner.hasNextLine()){
					String line = inScanner.nextLine();
					outPrintWriter.println("Echo: " + line);
					if(line.trim().equals("BYE")){
						done = true;
					}
				}
			}
			finally{
				incomingSocket.close();
			}
		}
		catch(IOException exception){
			exception.printStackTrace();
		}

	}

}
