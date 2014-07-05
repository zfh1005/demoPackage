package socketDemo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class tcpSocketServer_OneToOne {
	ServerSocket ss;
	
	public void initServerSocket(){
		try{
			this.ss = new ServerSocket(30000);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public void readThread(){
		try {
			while(true){				
				Socket s;
				s = ss.accept();
				tcpSocketServerReadThread_OneToOne ssrt = new tcpSocketServerReadThread_OneToOne(s);
				// Start Socket read thread
				new Thread(ssrt).start();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}		

	}

	public void writeThread(){
		try {
			while(true){				
				Socket s;
				s = ss.accept();
				tcpSocketServerWriteThread_OneToOne sswt = new tcpSocketServerWriteThread_OneToOne(s);
				// Start Socket write thread
				new Thread(sswt).start();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {		
	

	}

}
