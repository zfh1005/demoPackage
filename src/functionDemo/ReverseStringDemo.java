/**
 * 
 */
package functionDemo;

/**
 * @author zfh1005
 *
 */
public class ReverseStringDemo implements Reverser{
	
	/*
	 * 使用StringBuilder 的方式转置字符串
	 * */	
	public String reverseStringUserStringBuilder(String str){
		if((str.length() <= 0)||(str == null)){
			return str;
		}
		StringBuilder result = new StringBuilder(str);
		for(int i = 0; i <(str.length() / 2); i++){
			int swapIndex = str.length() -1 - i;
			char swapChar = result.charAt(swapIndex);
			result.setCharAt(swapIndex, result.charAt(i));
			result.setCharAt(i, swapChar);
		}
		return result.toString();
	}

	/*
	 * 使用char[] 数组的方式转置字符串
	 * */
	public String reverseStringUserCharArray(String str){
		if((str.length() <= 1)||(str == null)){
			return str;
		}
		char[] result = str.toCharArray();
		int right = result.length  - 1;
		for(int left = 0; left < right; left++){
			char swap = result[left];
			result[left] = result[right];
			result[right--] = swap;			
		}	
		return new String(result);
	}
	
	/*
	 * 使用StringBuffer 数组的方式转置字符串
	 * */
	public String reverseStringUserStringBuffer(String str){
		if((str.length() <= 1)||(str == null)){
			return str;
		}
		StringBuffer result = new StringBuffer(str.length());
		for(int i = str.length() - 1; i >= 0; i--){
			result.append(str.charAt(i));			
		}
		return result.toString();
	}
	
	/*
	 * 使用递归的方式转置字符串
	 * */
	public String reverseStringUserRecursive(String str){
		if((str.length() <= 1)||(str == null)){
			return str;
		}
		return (reverseStringUserRecursive(str.substring(1)) + str.charAt(0));
	}
	
	/*
	 * 使用StrinBuffer中的reverse接口的方式转置字符串
	 * */
	public String reverseStringUserReverse(String str){	
		if((str.length() <= 1)||(str == null)){
			return str;
		}
		return new StringBuffer(str).reverse().toString();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReverseStringDemo rsd = new ReverseStringDemo();
		
		String result =rsd.reverseStringUserCharArray("123456");
		System.out.println("123456:" + result.toString());
		
		result = rsd.reverseStringUserRecursive("123456");
		System.out.println("123456:" + result.toString());
		
		result = rsd.reverseStringUserReverse("123456");
		System.out.println("123456:" + result.toString());
		
		result = rsd.reverseStringUserStringBuffer("123456");
		System.out.println("123456:" + result.toString());
		
		result = rsd.reverseStringUserStringBuilder("123456");
		System.out.println("123456:" + result.toString());
		
		// TODO Auto-generated method stub

	}

}
