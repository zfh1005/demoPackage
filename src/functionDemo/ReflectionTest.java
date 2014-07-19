/**
 * 
 */
package functionDemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * @author zfh1005
 */
public class ReflectionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String name;
		if(args.length > 0){
			name = args[0];
		}
		else{
			Scanner in = new Scanner(System.in);
			System.out.println("Input class name(e.g. java.util.Date): ");
			name = in.next();
		}
		
		//print class name and supper class name
		try {
			Class cl = Class.forName(name);
			Class suppercl = cl.getSuperclass();
			System.out.print("class " + name);
			if(suppercl != null && suppercl != Object.class){
				System.out.print("extends " + suppercl.getName());
			}
			System.out.print("\n{\n");
			printConstructors(cl);
			System.out.println();
			printMethods(cl);
			System.out.println();
			printField(cl);
			System.out.print("}");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	/*
	 * 
	 * print all methods of a Constructors
	 * @param cl a Class
	 * 
	 * */
	public static void printConstructors(Class cl){
		Constructor[] cst = cl.getDeclaredConstructors();
		System.out.println("	//printConstructors");
		for(Constructor c: cst){
			String name = c.getName();
			System.out.print("	" + Modifier.toString(c.getModifiers()));
			System.out.print(" " + name + "(");
			
			//print parameter types
			Class[] paramTypes = c.getParameterTypes();
			for(int j = 0; j < paramTypes.length; j++){
				if(j > 0 ){
					System.out.print(", ");		
				}				
				System.out.print(paramTypes[j].getName());
			}			
			System.out.println(");");
		}
	}
	
	/*
	 * 
	 * print all methods of a class
	 * @param cl a Class
	 * 
	 * */
	public static void printMethods(Class cl){
		System.out.println("	//printMethods");
		Method[] mt = cl.getDeclaredMethods();
		for(Method m: mt){
			Class retType = m.getReturnType();
			String name = m.getName();
			System.out.print("	" + Modifier.toString(m.getModifiers()));
			System.out.print(" " + retType.getName() + " " + name + "(");
			
			//print parameter types
			Class[] paramTypes = m.getParameterTypes();
			for(int j = 0; j < paramTypes.length; j++){
				if(j > 0 ){
					System.out.print(", ");		
				}				
				System.out.print(paramTypes[j].getName());
			}			
			System.out.println(");");
		}
	}

	/*
	 * 
	 * print all field of a class
	 * @param cl a Class
	 * 
	 * */
	public static void printField(Class cl){
		System.out.println("	//printField");
		Field[] field = cl.getFields();
		for(Field f: field){
			Class type = f.getType();
			String name = f.getName();
			System.out.print("	" + Modifier.toString(f.getModifiers()));
			System.out.print(" " + type.getName() + " " + name + "(");
		}
	}

}
