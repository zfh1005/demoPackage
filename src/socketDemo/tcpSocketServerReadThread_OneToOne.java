package socketDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class tcpSocketServerReadThread_OneToOne implements Runnable{
	Socket s;
	BufferedReader br = null;
	private String clientReadBuffer = null;
	
	public tcpSocketServerReadThread_OneToOne(Socket s){
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
		String bf = null;
		while((bf = readFromClinet()) != null){
			clientReadBuffer += bf;
		}
		try {
			PrintStream ps = new PrintStream(s.getOutputStream());
			ps.println(br.readLine());
		} 
		catch (IOException e) {			
			e.printStackTrace();
			try {
				s.close();
			} 
			catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private String readFromClinet(){
		try {
			return br.readLine();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String returnReadBuffer(){
		return this.clientReadBuffer;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
