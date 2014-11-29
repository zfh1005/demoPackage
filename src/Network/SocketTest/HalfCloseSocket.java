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
 * This class is demo show half-close function.
 * @author zfh1005
 *
 */
public class HalfCloseSocket {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			Socket socket = new Socket("127.0.0.1", 8189);
			try {
				InputStream inputStream = socket.getInputStream();
				OutputStream outputStream = socket.getOutputStream();
				
				Scanner in = new Scanner(inputStream);
				PrintWriter outPrintWriter = new PrintWriter(outputStream, true);
				
				//send request data
				outPrintWriter.println("Hello");
				outPrintWriter.flush();
				//half-closed socket
				socket.shutdownOutput();
				
				//read response data
				while(in.hasNextLine()){
					String line = in.nextLine();
					System.out.print(line);
				}
				in.close();
			} 
			finally{
				socket.close();
			}
		}
		catch(IOException exception){
			exception.printStackTrace();
		}
	}

}
