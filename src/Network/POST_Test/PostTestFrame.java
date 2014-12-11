/**
 * 
 */
package Network.POST_Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFrame;

/**
 * Program UI frame
 * @author zfh1005
 *
 */
public class PostTestFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3655975987319552883L;

	/*
	 * Makes a POST request and returns the server response.
	 * 
	 * @param urlString the URL to post to 
	 * @param nameValuePairs is a map of name/value pairs to supply in the request.
	 * @return the server reply(either from the input stream or the error stream)
	 *
	 * */
	public static String doPost (String urlString, Map<String, String> nameValuePairs)
	throws IOException{
		URL url = new URL(urlString);
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		
		PrintWriter outPrintWriter = new PrintWriter(connection.getOutputStream());
		boolean firstBoolean = true;
		for(Map.Entry<String, String> pair : nameValuePairs.entrySet()){
			if(firstBoolean){
				firstBoolean = false;
			}
			else{
				outPrintWriter.print("&");
			}
			String name = pair.getKey();
			String value = pair.getValue();
			outPrintWriter.print(name);
			outPrintWriter.print('=');
			outPrintWriter.print(URLEncoder.encode(value, "UTF-8"));
		}
		
		outPrintWriter.close();
		Scanner in;
		StringBuilder response = new StringBuilder();
		try{
			in = new Scanner(connection.getInputStream());			
		}
		catch(IOException IoException){
			if(!(connection instanceof HttpURLConnection)){
				throw IoException;
			}
			InputStream errInputStream = ((HttpURLConnection) connection).getErrorStream();
			if(errInputStream == null){
				throw IoException;
			}
			in = new Scanner(errInputStream);
		}
		
		while(in.hasNextLine()){
			response.append(in.nextLine());
			response.append("\n");
		}
		
		in.close();
		return response.toString();
	}
}
