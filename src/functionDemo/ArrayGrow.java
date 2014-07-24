/**
 * 
 */
package functionDemo;
import java.lang.reflect.Array;

/**
 * @author zfh1005
 */
public class ArrayGrow {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a = {1, 2, 3};
		arrayPrint(a);
		a = (int[]) ArrayGrow(a);
		arrayPrint(a);

	}
	
	/*
	 * This method grows an array by allocating a new array of the same
	 * type and copying all elements.
	 * @param a the array to grow.This can be an object array or a fundamental  type array
	 * @return a larger array that contain all elements of a .
	 * 
	 * */
	
	static Object ArrayGrow(Object a){
		Class cl = a.getClass();
		if(! cl.isArray()){
			return null;
		}
		Class comType = cl.getComponentType();
		int length = Array.getLength(a);
		//the new length can change to any new one
		int newLength = length * 11 / 10 + 10;
		Object newArray = Array.newInstance(comType, newLength);
		System.arraycopy(a, 0, newArray, 0, length);
		return newArray;
	}
	
	/*
	 * 
	 * A convenience method to print all elements in an array
	 * @param a	the array to print, can be an object array or a fundamental type array
	 * 
	 * */
	static void arrayPrint(Object a){
		Class cl = a.getClass();
		if(! cl.isArray()){
			return;
		}
		Class comType = cl.getComponentType();
		int length = Array.getLength(a);
		System.out.print(comType.getName() + "[" + length + "] = { ");
		for(int i = 0; i < Array.getLength(a); i++){
			System.out.print(Array.get(a, i) + " ");
		}
		System.out.println("}");		
	}

}