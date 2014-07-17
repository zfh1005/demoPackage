/**
 * 
 */
package functionDemo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Pattern; 
import java.util.regex.Matcher; 

/**
 * @author zfh1005
 *
 */


public class javaRegexTest {  
	/*
	 * function:死循环不断要求输入检查正则表达式 
	 * 
	 * */
	public void regexLoopTest() throws IOException{
		while (true) { 
			//Scanner input = new Scanner(System.in);
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("waiting input regex:");
			//String sRegex = input.next();
			String sRegex = input.readLine();
			System.out.print("waiting input search string:");
			//String sSearchString = input.next();
			String sSearchString = input.readLine();

			Pattern pattern = Pattern.compile(sRegex);  
			Matcher matcher =  pattern.matcher(sSearchString);  

			boolean found = false;  

			while (matcher.find()) {  
				System.out.println(matcher.group() + " is start at index: " + matcher.start() + "; ending at index: " + matcher.end());  
				found = true;
			}  

			if(!found){  
				System.out.println("No match found.");  
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
	public boolean regexCheck(String reg, String string){
		boolean result = false;
		Pattern pattern = Pattern.compile(reg);  
		Matcher matcher =  pattern.matcher(string); 
		result = matcher.matches();
		return result;
	}
	
	/*
	 * @function	检查整数和0
	 * @param	string 待check的字串
	 * @return	true	找到
	 * 
	 * */	
	public boolean checkIntNumber(String string){
		//String reg = "(^-?)[1-9][0-9]*|(^0$)";
		String reg = "/(^-?)[1-9]\b*|(^0$)/";
		System.out.println(reg);
		return regexCheck(reg, string);
		
	}
	
	/*
	 * @function	检查浮点数和0
	 * @param	string 待check的字串
	 * @return	true	找到
	 * 
	 * */	
	public boolean checkfloatNumber(String string){
	  //String reg = "(((^-?)[1-9][0-9]*).?[0-9]*[1-9])|((^-?)0[.][0-9]*[1-9])|(^0$)";
		String reg = "/(((^-?)[1-9]\b*).?\b*[1-9])|((^-?)0[.]\b*[1-9])|(^0$)/";
		return regexCheck(reg, string);
		
	}
	
	/*
	 * @function	检查是否满足mail地址格式
	 * @param	string 待check的字串
	 * @return	true	找到
	 * 
	 * 常见格式:zF12@126.com
	 * 
	 * */	
	public boolean checkMailAddress(String string){
		//String reg = "\\w+\\@\\w+\\.(com|cn|com.cn|net|org|gov|gov.cn|edu|edu.cn)";
		String reg = "/[0-9a-zA-Z]+\\@[0-9a-zA-Z]+\\.(com|cn|com.cn|net|org|gov|gov.cn|edu|edu.cn)/";
		return regexCheck(reg, string);
		
	}
	
	/*
	 * @function	检查IP格式(v4)
	 * @param	string 待check的字串
	 * @return	true	找到
	 * 
	 * */	
	public boolean checkIPv4(String string){
		String reg = "/(([1-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}" + 
					 "([1-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])/";
		//String reg = "(([1-9]|[1-9]\\b|1\\b{2}|2[0-4]\\b|25[0-5])\\.){3}" + 
		// 				"([1-9]|[1-9]\\b|1\\b{2}|2[0-4]\\b|25[0-5])";
		return regexCheck(reg, string);
		
	}
	
	/*
	 * @function	检查IP格式(v6)
	 * @param	string 待check的字串
	 * @return	true	找到
	 * @bug	目前只能匹配标准格式的IPv6地址,对于压缩简化过后的地址没处理
	 * 
	 * */	
	public boolean checkIPv6(String string){
		String reg = "/(([1-9a-fA-F]|[1-9a-fA-F][0-9a-fA-F]{1,3})\\:){7}" + 
					 "([1-9a-fA-F]|[1-9a-fA-F][0-9a-fA-F]{1,3})/";
		return regexCheck(reg, string);
		
	}
	
	/*
	 * @function	检查身份证格式
	 * @param	string 待check的字串
	 * @return	true	找到
	 *
	 * 限制:收尾不为0; 15 / 18 位; 18位时最后一位可以是x/X
	 * 
	 * */	
	public boolean checkIDCard(String string){
		//String reg = "/^[1-9](([0-9]{14}$)|([0-9]{16}[0-9xX])$)/";
		String reg = "/^[1-9](([0-9]{14}$)|([0-9]{16}[0-9xX])$)/";
		return regexCheck(reg, string);		
	}
	
	/*
	 * @function	检查工厂工号
	 * @param	string 待check的字串
	 * @return	true	找到
	 * 
	 * 常见格式:123456 / F123456 / A123456
	 * 
	 * */	
	public boolean checkFactoryID(String string){
		String reg = "/^((F|A)?)[0-9]{6}$/";
		return regexCheck(reg, string);
		
	}
	
	/*
	 * @function	检查汉字
	 * @param	string 待check的字串
	 * @return	true	找到
	 * 	 
	 * 
	 * */	
	public boolean checkChinese(String string){
		String reg = "/[\u4e00-\u9fa5]/";
		return regexCheck(reg, string);
		
	}
	
	/*
	 * @function	检查用户名(取值范围:0-9; a-z; A-Z; 汉字; )
	 * @param	string 待check的字串
	 * @return	true	找到
	 * 	 
	 * 
	 * */	
	public boolean checkUsername(String string, int min, int max){
		String temp = String.format("{%d\\,%d}",min, max);
		String reg = "/[0-9a-zA-Z._]/" + temp ;
		return regexCheck(reg, string);
		
	}
	

	public static void main(String[] args){  		
		javaRegexTest jrt = new javaRegexTest();
		try {			
			jrt.regexLoopTest();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	} 
}  