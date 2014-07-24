/**
 * 
 */
package functionDemo;

import java.util.*;

/**
 * @author zfh1005
 */
public class EnumTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a size(e.g.:(SMALL, MEDIUM, LARCE))");
		String input = in.next().toUpperCase();
		
	}
	
	public enum Color {
	    RED("R", 1), GREEN("G", 2), YELLO("Y", 3), BLANK("B", 4) ;
	    

	    //
	    private Color(String name, int index) {
	        this.name = name;
	        this.index = index;
	    }

	    
	    public static String getName(int index) {
	        for (Color c : Color.values()) {
	            if (c.getIndex() == index) {
	                return c.name;
	            }
	        }
	        return null;
	    }

	    // get / set 
	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public int getIndex() {
	        return index;
	    }

	    public void setIndex(int index) {
	        this.index = index;
	    }
	    
	    @Override
	    public String toString(){
	    	return this.index + "_" + this.name;
	    }
	    
	    //
	    private String name;
	    private int index;
	}

}



















