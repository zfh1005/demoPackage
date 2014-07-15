package socketDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class tcpSocketClientReadThread_OneToOne implements Runnable{	
	BufferedReader br = null;
	private String clientReadBuffer = null;
	
	public tcpSocketClientReadThread_OneToOne(Socket s){
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void run() {
		String bf = null;
		try {
			while((bf = br.readLine()) != null){
				System.out.println("Receive Server Message:" + bf );	
				clientReadBuffer += bf;
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public String returnReadBuffer(){
		return this.clientReadBuffer;
	}
	
	public static void main(String[] args) {

	}
}
