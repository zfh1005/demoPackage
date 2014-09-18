/**
 * 
 */
package StreamDemo.RegexDemo;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author zfh1005
 *
 */

/*
 * This program display all URLs in a web page by matching a regular expression
 * that describes the <a herf=...> HTML tag. Start the program as <br>
 * java HrefMatch URL 
 * 
 * */
public class HrefMatch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			//get URL string from command line or use default
			String urlString;
			if(args.length > 0){
				urlString = args[0];
			}
			else {
				urlString = defalString;
			}
			
			//open reader for URL
			InputStreamReader inputStreamReader = new InputStreamReader(new URL(urlString).openStream());
			
			//read contents into string builder
			StringBuilder inBuilder = new StringBuilder();
			int ch;
			while ((ch = inputStreamReader.read()) != -1){
				inBuilder.append(ch);
			}
			
			//search for all occurrences of pattern 
			String patternString = "<a\\s+href\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>";
			Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(inBuilder);
			
			while(matcher.find()){
				int start = matcher.start();
				int end = matcher.end();
				String matcString = inBuilder.substring(start, end);
				System.out.println(matcString);
			}
					
		}
		catch(IOException exception){
			exception.printStackTrace();
		}
		catch(PatternSyntaxException exception){
			exception.printStackTrace();
		}

	}
	
	private static String defalString = "http://java.sun.com";

}
