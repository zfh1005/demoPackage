/**
 * 
 */
package StreamDemo.RegexDemo;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author zfh1005
 *
 */
public class RegexTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter pattern: ");
		String patternString = in.nextLine();
		
		Pattern pattern = null;
		
		try{
			pattern = Pattern.compile(patternString);
		}
		catch(PatternSyntaxException exception){
			System.out.println("Pattern syntax error");
			System.exit(1);
		}
		
		while (true) {
			System.out.println("Entrr string to match: ");
			String inputString = in.nextLine();
			if((inputString == null) || (inputString.equals(""))){
				return;
			}
			
			Matcher matcher = pattern.matcher(inputString);
			if(matcher.matches()){
				System.out.println("Match");
				int g = matcher.groupCount();
				if(g > 0){
					for(int i = 0; i < inputString.length(); i++){
						for(int j = 1; j <= g; j++){
							if(i == matcher.start(j)){
								System.out.print('(');
							}							
						}
						System.out.print(inputString.charAt(i));
						for(int j = 1; j <= g; j++){
							if(i == matcher.end(j)){
								System.out.print(')');
							}							
						}
					}
					System.out.println();
				}
			}
			else {
				System.out.println("No Match");
			}
			
		}

	}

}
