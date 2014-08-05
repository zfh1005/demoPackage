/**
 * 
 * EventTrace 
 * trace AWT component's event.
 * User guide
 * EventTrace et = new EventTrace();
   et.add(frame);
   et.add(TextArea)
   ...
 * 
 */

package ExceptionDemo;

import java.awt.Component;
import java.awt.Container;
import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * @author zfh1005
 *
 */
public class EventTrace {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventTrace et = new EventTrace();
		et.add(null);
	}
	
	public EventTrace(){
		handler = new InvocationHandler(){
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				System.out.println(method + ":" + args[0]);
				return null;
			}			
		};		
	}
	
	public void add(Component c){
		try {
			BeanInfo info = Introspector.getBeanInfo(c.getClass());
			EventSetDescriptor[] eventSets = info.getEventSetDescriptors();
			for(EventSetDescriptor eventSet : eventSets){
				addListener(c, eventSet);
			}
		} 
		catch (IntrospectionException e) {
			e.printStackTrace();
		}
		
		if(c instanceof Container){
			for(Component comp : ((Container) c).getComponents()){
				add(comp);
			}
		}
	}
	
	public void addListener(Component cc, EventSetDescriptor eventSet){
		Object proxy = Proxy.newProxyInstance(null, 
				new Class[]{eventSet.getListenerType()}, 
				handler);
		Method addListenerMethod = eventSet.getAddListenerMethod();
		
		try {
			addListenerMethod.invoke(cc, proxy);
		} 
		catch (IllegalAccessException e) {
			e.printStackTrace();
		} 
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		} 
		catch (InvocationTargetException e) {
			e.printStackTrace();
		}		
	}
	
	private InvocationHandler handler;

}
