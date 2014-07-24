/**
 * 
 */
package functionDemo;

import java.lang.reflect.*;
import java.util.*;

/**
 * @author zfh1005
 */
public class ProxyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Object[] elements = new Object[1000];
		
		//fill elements with proxies for the 1...1000
		for(int i = 0; i < elements.length; i++){
			Integer value = i + 1;
			Class[] interfaces = value.getClass().getInterfaces();
			InvocationHandler handler = new TraceHandler(value);
			Object proxy = Proxy.newProxyInstance(null, interfaces, handler);
			elements[i] = proxy;
			
			
		}
		
		//construct a random integer
		Integer key = new Random().nextInt(elements.length) + 1;
		
		//Search for the key
		int result = Arrays.binarySearch(elements, key);
		
		//print match if found
		if(result >= 0){
			System.out.println(elements[result]);
		}
	}
	
	public static class TraceHandler implements InvocationHandler{
		public TraceHandler(Object t){
			target  = t;
		}

		/* (non-Javadoc)
		 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
		 */
		@Override
		public Object invoke(Object arg0, Method arg1, Object[] arg2)
				throws Throwable {
			//print implicit argument
			System.out.print(target);
			System.out.print("." + arg1.getName() + "(");
			//print explicit arguments
			if(arg2 != null){
				for(int i = 0; i < arg2.length; i ++){
					System.out.print(arg2[i]);
					if(i < arg2.length - 1){
						System.out.print(", ");
					}					
				}
			}
			System.out.println(")");
			//invoke actual method
			return arg1.invoke(target, arg2);
		}		
		private Object target;		
	}

}
