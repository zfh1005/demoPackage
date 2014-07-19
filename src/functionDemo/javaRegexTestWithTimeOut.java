/**
 * 
 */
package functionDemo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern; 
import java.util.regex.Matcher; 

/**
 * @author zfh1005
 *
 */
public class javaRegexTestWithTimeOut {

	/*
	 * function:死循环不断要求输入检查正则表达式 
	 * 
	 * */
	public void regexLoopTest() {
		while (true) { 
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

			System.out.print("waiting input regex:");
			String sRegex;
			try {
				sRegex = input.readLine();

				System.out.print("waiting input search string:");
				String sSearchString = input.readLine();

				System.out.print("timeout time:");
				String sTime = input.readLine();

				long start = System.nanoTime();

				Matcher matcher = createMatcherWithTimeout(sSearchString, sRegex, Integer.parseInt(sTime));

				boolean found = false;  

				while (matcher.find()) {  
					System.out.println(matcher.group() + " start at index: " + matcher.start() + "; ending at index: " + matcher.end());  
					System.out.println( "Used time:" + ((System.nanoTime() - start) / 2000000d) + "ms");
					found = true;
				}  

				if(!found){  
					System.out.println("No match found.");  
				}  
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * @function	正则表达式检查
	 * @param	reg	正则表达式
	 * @param	string	待check的字串
	 * @return	true	找到
	 * 
	 * */
	public boolean regexCheck(String reg, String string, int timeInMill){
		boolean result = false;
		Matcher matcher = createMatcherWithTimeout(string, reg, timeInMill);
		result = matcher.matches();
		return result;
	}	


	public static Matcher createMatcherWithTimeout(String stringToMatch, String regularExpression, int timeoutMillis) {
		Pattern pattern = Pattern.compile(regularExpression);
		return createMatcherWithTimeout(stringToMatch, pattern, timeoutMillis);
	}

	public static Matcher createMatcherWithTimeout(String stringToMatch, Pattern regularExpressionPattern, int timeoutMillis) {
		CharSequence charSequence = new TimeoutRegexCharSequence(stringToMatch, timeoutMillis, stringToMatch,
				regularExpressionPattern.pattern());
		return regularExpressionPattern.matcher(charSequence);
	}

	private static class TimeoutRegexCharSequence implements CharSequence {

		private final CharSequence inner;

		private final int timeoutMillis;

		private final long timeoutTime;

		private final String stringToMatch;

		private final String regularExpression;

		public TimeoutRegexCharSequence(CharSequence inner, int timeoutMillis, String stringToMatch, String regularExpression) {
			super();
			this.inner = inner;
			this.timeoutMillis = timeoutMillis;
			this.stringToMatch = stringToMatch;
			this.regularExpression = regularExpression;
			timeoutTime = System.currentTimeMillis() + timeoutMillis;
		}

		@Override
		public char charAt(int index) {
			if (System.currentTimeMillis() > timeoutTime) {
				throw new RuntimeException("Timeout occurred after " + timeoutMillis + "ms while processing regular expression '"
						+ regularExpression + "' on input '" + stringToMatch + "'!");
			}
			return inner.charAt(index);
		}

		@Override
		public int length() {
			return inner.length();
		}

		@Override
		public CharSequence subSequence(int start, int end) {
			return new TimeoutRegexCharSequence(inner.subSequence(start, end), timeoutMillis, stringToMatch, regularExpression);
		}

		@Override
		public String toString() {
			return inner.toString();
		}
	}


	// demonstrates behavior for regular expression running into catastrophic backtracking for given input
	public static void main(String[] args) {
		javaRegexTestWithTimeOut jrt = new javaRegexTestWithTimeOut();
		try {
			jrt.regexLoopTest();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
