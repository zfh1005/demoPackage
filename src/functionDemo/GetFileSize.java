/**
 * 
 */
package functionDemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

/**
 * @author zfh1005
 *
 */
public class GetFileSize {

	/*
	 * GUI choose file path 
	 * @return String type; file Path name choose
	 * */
	public String JPathChoose(){
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = chooser.showOpenDialog(chooser);
		String FilePath = null;
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			FilePath = chooser.getSelectedFile().getAbsolutePath();
			System.out.println("You chose path: " + FilePath);
		}
		return FilePath;
	}

	/*
	 * function: used recursive way get pathName file list  
	 * @param pathName point Recursive path name
	 * @return ArrayList file list
	 * 
	 * */
	public ArrayList<String> refreshFileListRecursive(String pathName){
		ArrayList<String> fileList = new ArrayList<String>();
		File dir = new File(pathName);
		File[] listFile = dir.listFiles();
		if(listFile == null){
			return null;
		}

		for(int i = 0; i < listFile.length; i++){
			if(listFile[i].isDirectory()){
				refreshFileListRecursive(listFile[i].getAbsolutePath());
			}
			else{
				String File = listFile[i].getAbsolutePath();
				fileList.add(File);
				String stringData = null;
				
				//check file size
				long fileLength = CheckFileSize(File);
				
				stringData = File + "," + fileLength + "\n";
				System.out.print(stringData);
				StringBufferWriteFile("c:\\111111111111.txt", stringData);							
			}
		}	
		return fileList;
	}


	/*
	 * check file size
	 * @param filename need check filename
	 * @return long type size; file size in bytes
	 * 
	 * */
	public long CheckFileSize(String fileName){
		long s = 0;
		File file = new File(fileName);
		s = file.length();		
		return s;
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

	/*print result*/
	private void printResult(){
		String pathName = JPathChoose();
		ArrayList<String> fileList = refreshFileListRecursive(pathName);		
	}

	/**
	 * @param args
	 */ 
	public static void main(String[] args) {
		GetFileSize gfs = new GetFileSize();
		gfs.printResult();
	}
}
