/**
 * 
 */
package StreamDemo.FileStreamDemo;

import java.io.File;
import java.io.IOException;

/**
 * @author zfh1005
 *
 */

/*
 * get directories file length
 * */
public class GetDirectoriesFileLength {

	/**
	 * @param args
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		//if no arguments provided, start at the parent directory
		if(args.length == 0){
			args = new String[]{defaultPathString};
		}
		
		try{
			File pathName = new File(args[0]);
			String[] fileNamesStrings = pathName.list();
			
			//enumerate all files in the directory
			for(int i = 0; i < fileNamesStrings.length; i++){
				File file = new File(pathName.getPath(), fileNamesStrings[i]);
				
				//if the file is again a directory, call the main recursively
				if(file.isDirectory()){
					main(new String[]{file.getPath()});
				}
				
				//if the file is a file, get file full path and space
				if(file.isFile()){
					System.out.println(file.getCanonicalPath() + file.separator + file.getName() + "\t\t" + file.length());
				}
			}
		}
		catch(IOException exception){
			exception.printStackTrace();
		}
	}
	
	private static String defaultPathString  = "f:\\code"; 

}
