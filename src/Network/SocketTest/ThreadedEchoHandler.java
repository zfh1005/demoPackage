/**
 * 
 */
package Network.SocketTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * This class handles the client input for one server socket connection.
 * 
 * @author zfh1005
 *
 */
public class ThreadedEchoHandler implements Runnable {

	/*
	 * Constructs a handler
	 * @param i the incomming socket
	 * @param c the counter for the handler(used in prompts)
	 */
	public ThreadedEchoHandler(Socket i, int c) {
		incomingSocket = i;
		iCount = c;
	}

	/*
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			try {
				
				InputStream inputStream = incomingSocket.getInputStream();
				OutputStream outputStream = incomingSocket.getOutputStream();

				Scanner inScanner = new Scanner(inputStream);
				PrintWriter outPrintWriter = new PrintWriter(outputStream, true);

				outPrintWriter.println("Hello! Entry BYE to exit.");

				// echo client input
				boolean done = false;
				while (!done && inScanner.hasNextLine()) {
					String line = inScanner.nextLine();
					outPrintWriter.println("Echo: " + line);
					System.out.println("RCV " + String.valueOf(iCount) + ":" + line);
					if (line.trim().equals("BYE")) {
						done = true;
					}
				}
			} 
			finally {
				incomingSocket.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private Socket incomingSocket;
	private int iCount;
}
