/**
 * 
 */
package functionDemo;



import java.io.File;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author zfh1005
 *
 */
public class JChooserFunctionDemo {

	public void JImageFileChoose(){
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"ImagesFile", "jpg", "gif", "bmp", "png");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(chooser);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " +chooser.getSelectedFile().getName());
		}
	}

	public void JPathChoose(){
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = chooser.showOpenDialog(chooser);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			String FilePath = chooser.getSelectedFile().getAbsolutePath();
			System.out.println("You chose path: " + FilePath);
			//refreshFileListRecursive(FilePath);	
			refreshFileListUnRecursive(FilePath);		
		}
	}

	/*
	 * 函数功能:使用递归方法遍历路径下的所有文件(包括子目录)
	 * @param pathName 指定需要遍历的目录
	 * @return void
	 * */
	public void refreshFileListRecursive(String pathName){
		ArrayList<String> fileList = new ArrayList<String>();
		File dir = new File(pathName);
		File[] listFile = dir.listFiles();
		if(listFile == null){
			return;
		}

		for(int i = 0; i < listFile.length; i++){
			if(listFile[i].isDirectory()){
				refreshFileListRecursive(listFile[i].getAbsolutePath());
			}
			else{
				String FilePath = listFile[i].getAbsolutePath();
				System.out.println("Path file: " + FilePath);
				fileList.add(listFile[i].getAbsolutePath());
			}
		}		
	}

	/*
	 * 函数功能:不使用递归方法遍历路径下的所有文件(包括子目录)
	 * @param pathName 指定需要遍历的目录
	 * @return void
	 * */
	public void refreshFileListUnRecursive(String pathName){
		LinkedList<File> list = new LinkedList<File>();	
		File dir = new File(pathName);
		File file[] = dir.listFiles();
		for(int i = 0; i < file.length; i++){
			if(file[i].isDirectory()){
				list.add(file[i]);	
			}
			else{
				System.out.println("Path file: " + file[i].getAbsolutePath());
			}
		}
		File temp;
		while(!list.isEmpty()){
			temp = list.removeFirst();
			if(temp.isDirectory()){
				file = temp.listFiles();				

				if(file == null){
					continue;
				}
				for(int i = 0; i < file.length; i++){
					if(file[i].isDirectory()){
						list.add(file[i]);
					}
					else{
						System.out.println("Path file: " + file[i].getAbsolutePath());
					}
				}
			}
			else{
				System.out.println("Path file: " + temp.getAbsolutePath());
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		JChooserFunctionDemo jfjd = new JChooserFunctionDemo();
		jfjd.JPathChoose();
		// TODO Auto-generated method stub	    
	}

}
