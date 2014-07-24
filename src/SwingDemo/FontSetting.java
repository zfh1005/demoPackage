/**
 * 
 */
package SwingDemo;

import java.awt.GraphicsEnvironment;

/**
 * @author zfh1005
 */
public class FontSetting {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FontSetting fs = new FontSetting();
		fs.getSystemFontList();

	}
	
	public String[] getSystemFontList(){
		String[] fontList = GraphicsEnvironment
				.getLocalGraphicsEnvironment()
				.getAvailableFontFamilyNames();
		//print system font
		for(String name : fontList){
			System.out.println(name);
		}
		return fontList;
	}

}
