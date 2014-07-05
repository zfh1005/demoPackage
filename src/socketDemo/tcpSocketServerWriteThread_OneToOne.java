package socketDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class tcpSocketServerWriteThread_OneToOne implements Runnable{
	Socket s;
	BufferedReader br = null;
	private String clientWriteBuffer = null;
	private PrintStream ps;

	public tcpSocketServerWriteThread_OneToOne(Socket s){
		this.s = s;
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} 
		catch (IOException e) {			
			e.printStackTrace();
			try {
				s.close();
			} 
			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	

	@Override
	public void run() {
		try {
			String line = null;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			while((line = br.readLine()) != null){
				ps.println(line + "\n");
			}
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public String returnWriteBuffer(){
		return this.clientWriteBuffer;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
