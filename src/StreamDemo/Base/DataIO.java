/**
 * 
 */
package StreamDemo.Base;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author zfh1005
 *
 */
public class DataIO {
	/*
	 * read some data from input stream
	 * @param size the data length need read 
	 * @param in the input stream
	 * */
	public static String readFixedString(int size, DataInput in)throws  IOException{
		StringBuilder b = new StringBuilder(size);
		int i = 0;
		boolean more = true;
		while(more && i < size){
			char ch = in.readChar();
			i++;
			if(ch == 0){
				more = false;				
			}
			else{
				b.append(ch);
			}
		}
		in.skipBytes(2 * (size - 1));
		return b.toString();
	}
	
	/*
	 * write some data to OutputStream
	 * @param s the data need to write
	 * @param size the write data length
	 * @param out the output stream
	 * */
	public static void writeFixedString(String s, int size, DataOutput out)throws IOException{
		for(int i = 0; i < size; i++){
			char ch = 0;
			if(i < s.length()){
				ch = s.charAt(i);
			}
			out.write(ch);
		}
	}

}
