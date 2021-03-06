/**
 * 
 */
package Network.URLConnectionTest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author zfh1005
 *
 */
public class URLConnectionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			String urlNameString;
			if(args.length > 0){
				urlNameString = args[0];
			}
			else {
				urlNameString = "http://java.orcal.com";
			}
			
			URL url = new URL(urlNameString);
			URLConnection connection = url.openConnection();
			
			//set user name, password if specified on command line
			
			if(args.length > 2){
				String userName = args[1];
				String passwordString = args[2];
				String inputString = userName + ":" + passwordString;
				String encodingString = base64Encode(inputString);
				connection.setRequestProperty("Authorization", "Basic" + encodingString);
			}
			
			connection.connect();
			
			//print header fields
			Map<String, List<String>> headers = connection.getHeaderFields();
			for(Map.Entry<String, List<String>> entry : headers.entrySet()){
				String keyString = entry.getKey();
				for(String valueString : entry.getValue()){
					System.out.println(keyString + ":" + valueString);
				}
			}
			
			//print convenience function
			System.out.println("-----------------");
			System.out.println("getContentType: " + connection.getContentType());
			System.out.println("getContentLength: " + connection.getContentLength());
			System.out.println("getContentEncoding: " + connection.getContentEncoding());
			System.out.println("getDate: " + connection.getDate());
			System.out.println("getExpiration: " + connection.getExpiration());
			System.out.println("getLastModified: " + connection.getLastModified());
			System.out.println("-----------------");
			
			Scanner inScanner = new Scanner(connection.getInputStream());
			
			//print first ten lines of contents
			for (int n = 0; inScanner.hasNextLine() && n <= 10; n++) {
				System.out.println(inScanner.nextLine());
			}
			if(inScanner.hasNextLine()){
				System.out.println("...");
			}
		}
		catch(Exception exception){
			System.out.println(exception);
		}

	}
	
	public static String base64Encode(String string){
		ByteArrayOutputStream bOutputStream = new ByteArrayOutputStream();
		Base64OutputStream outputStream = new Base64OutputStream(bOutputStream);
		try {
			outputStream.write(string.getBytes());
			outputStream.flush();
		}
		catch (IOException e) {
			System.out.println(e);
		}		
		return bOutputStream.toString();
	}

}
