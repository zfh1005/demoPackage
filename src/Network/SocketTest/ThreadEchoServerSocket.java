/**
 * 
 */
package Network.SocketTest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This program implements a multithread server that
 * listens to port 8189 and echo back all client input.
 * @version 1.0.0.1
 * @author zfh1005
 *
 */
public class ThreadEchoServerSocket {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			int i = 1;
			ServerSocket serverSocket = new ServerSocket(8189);
			
			while(true){
				Socket incomingSocket = serverSocket.accept();
				System.out.println("Spawning " + i);
				Runnable runnable = new ThreadedEchoHandler(incomingSocket, i);
				Thread thread = new Thread(runnable);
				thread.start();
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
