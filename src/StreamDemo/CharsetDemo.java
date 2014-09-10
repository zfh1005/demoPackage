/**
 * 
 */
package StreamDemo;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;

/**
 * @author zfh1005
 *
 */
public class CharsetDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//get a charset's alias(another name)
		getCharsetAliasNameList("ISO-8859-1");
		
		System.out.println();
		
		//get system can used charset list
		getSystemCharsetNameList();
		
		System.out.println();
		
		//change unicode charset to another 
		changeUnicodeToAnther("x-windows-874");
		
		changeAnotherToUnicode("x-MacTurkish");

	}
	
	/*
	 * get a charset's alias(another name)
	 * @param cc the charset name
	 * 
	 * */
	public static void getCharsetAliasNameList(String cc){
		Charset cset = Charset.forName(cc);
		Set<String> aliasesSet = cset.aliases();
		System.out.println("The ISO-8859-1 alias name list:" );
		for(String aliString : aliasesSet){
			System.out.println(aliString);
		}
	}

	/*
	 * get system can used charset list
	 * */
	public static void getSystemCharsetNameList(){
		Map<String, Charset> charsets = Charset.availableCharsets();
		System.out.println("System can used charset list:" );
		for(String name : charsets.keySet()){
			System.out.println(name);
		}
	}
	
	/*
	 * change unicode charset to another 
	 * @param cc the charset system
	 * 
	 * */
	public static void changeUnicodeToAnther(String cc){
		Charset cset = Charset.forName(cc);
		ByteBuffer buffer = cset.encode("ahsdhadh123123123");
		byte[] bytes = buffer.array();
		System.out.println(bytes);
	}
	
	/*
	 * change another charset to unicode 
	 * @param cc the charset system
	 *  
	 * */
	public static void changeAnotherToUnicode(String cc){
		Charset cset = Charset.forName(cc);
		byte[] bytes = {Byte.valueOf("52"), Byte.valueOf("56")} ;
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		CharBuffer cBuffer = cset.decode(buffer);
		System.out.println(cBuffer.toString());
	}
}
