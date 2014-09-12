/**
 * 
 */
package StreamDemo.SerialCloneDemo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author zfh1005
 *
 */

/*
 * a class whose clone method uses serialization.
 * */
public class SerialCloneable implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6910034744225904660L;

	public Object clone(){
		try{
			//save the object to a byte array
			ByteArrayOutputStream bOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream outputStream = new ObjectOutputStream(bOutputStream);
			outputStream.writeObject(this);
			outputStream.close();
			
			//read a clone of the object from the byte array
			ByteArrayInputStream bInputStream = new ByteArrayInputStream(bOutputStream.toByteArray());
			ObjectInputStream inputStream = new ObjectInputStream(bInputStream);
			Object retObject = inputStream.readObject();
			inputStream.close();
			
			return retObject;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
