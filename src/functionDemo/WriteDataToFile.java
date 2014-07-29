/**
 * 
 */
package functionDemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * @author zfh1005
 *
 */
public class WriteDataToFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WriteDataToFile wd = new WriteDataToFile();
		wd.PrintStreamWriteFile("c:\\1.544444444455.txt", "45a634sdf4asdfasdfasdfsdfaasfasdfasf");
	}
	
	/*
	 * StringBuffer write file
	 * @param FileName	write data to where
	 * @param Data data to write
	 * */
	public void StringBufferWriteFile(String FileName, String Data){
		File file = new File(FileName);
		if(!file.exists()){
			try {
				file.createNewFile();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileOutputStream out = new FileOutputStream(file, true);
			StringBuffer sb = new StringBuffer();
			sb.append(Data);
			try {
				out.write(sb.toString().getBytes("utf-8"));
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			try {
				out.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void PrintStreamWriteFile(String FileName, String Data){
		File file = new File(FileName);
		if(!file.exists()){
			try {
				file.createNewFile();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			FileOutputStream out = new FileOutputStream(FileName);
			PrintStream p = new PrintStream(out);
			p.print(Data);
			p.close();			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
