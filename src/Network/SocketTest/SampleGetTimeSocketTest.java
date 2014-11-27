/**
 * 
 */
package Network.SocketTest;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * This program makes a socket connection to the atomic clock in Boulder, Colorado, and
 * prints the time that the server sends.
 * @author zfh1005
 *
 */
public class SampleGetTimeSocketTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			Socket socket = new Socket("time-A.timefreq.bldrdoc.gov", 13);
			try {
				InputStream inputStream = socket.getInputStream();
				Scanner in = new Scanner(inputStream);
				
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
