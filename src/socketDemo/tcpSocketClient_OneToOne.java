package socketDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class tcpSocketClient_OneToOne {
	private static int ipSubStringLength = 4;
	private int iPort;
	private String serverIP;
	// private boolean bConnectState = false;
	private String clientReadBuffer = "";
	Socket tcpSocket;

	public int getPort() {
		return tcpSocket.getPort();
	}

	public boolean setPort(int iPort) {
		if ((iPort > 0) && (iPort < 65536)) {
			this.iPort = iPort;
			return true;
		}
		return false;
	}

	public String getServerIP() {
		return tcpSocket.getInetAddress().toString();
	}

	public boolean setServerIP(String IP) {
		String[] IPString = IP.split(".");
		if (IPString.length == ipSubStringLength) {
			for (int i = 0; i < ipSubStringLength; i++) {
				int iValue = Integer.parseInt(IPString[i]);
				// IP substring value must in [0, 255]
				if ((iValue < 0) || (iValue > 255)) {
					return false;
				}
			}
			this.serverIP = IP;
			return true;

		} else {
			// IP format error
			return false;
		}
	}

	public boolean getConnectState() {
		return tcpSocket.isConnected();
	}

	// connect
	public boolean connectServer() {
		try {
			tcpSocket = new Socket(this.serverIP, this.iPort);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (tcpSocket.isConnected() == true) {
			return true;
		} else {
			return false;
		}
	}

	// read data
	public void readDataFromServer() {
		tcpSocketClientReadThread_OneToOne scrt = new tcpSocketClientReadThread_OneToOne(tcpSocket);
		// Start Socket read thread
		new Thread(scrt).start();
		clientReadBuffer = scrt.returnReadBuffer();
	}

	// write data
	public void writeDataToServer() {
		try {
			String line = null;
			// get socket output stream
			PrintStream ps = new PrintStream(tcpSocket.getOutputStream());
			// get keyboard input
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			while ((line = br.readLine()) != null) {
				ps.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// write data
	public void writeDataToServer(String line) {
		try {
			// get socket output stream
			PrintStream ps = new PrintStream(tcpSocket.getOutputStream());
			ps.println(line + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// close
	public void close() {
		try {
			tcpSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		tcpSocketClient_OneToOne tcpSocketClient = new tcpSocketClient_OneToOne();

		tcpSocketClient.setServerIP(args[0]);
		tcpSocketClient.setPort(Integer.parseInt(args[1]));

		if (tcpSocketClient.getConnectState() == false) {
			// connect
			if (tcpSocketClient.connectServer() == false) {
				System.out.println("Connect " + tcpSocketClient.serverIP
						+ "Failed!");
			}
		}

		// read
		tcpSocketClient.readDataFromServer();

		// write
		tcpSocketClient.writeDataToServer();

		// close
		tcpSocketClient.close();
		// TODO Auto-generated method stub
	}

}
