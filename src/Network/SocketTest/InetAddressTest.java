/**
 * 
 */
package Network.SocketTest;

import java.net.InetAddress;


/**
 * This program demonstrates the InterAddress class.
 * Supply a host name as command line argument, or run
 * without command line argument to see the address of the local host.
 * @version 1.0.0.1
 * @author zfh1005
 *
 */
public class InetAddressTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			if(args.length > 0){
				String host = args[0];
				InetAddress[] address = InetAddress.getAllByName(host);
				for(InetAddress address2 : address){
					System.out.println(address2);
				}
			}
			else {
				InetAddress localHostAddress = InetAddress.getLocalHost();
				System.out.println(localHostAddress);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
