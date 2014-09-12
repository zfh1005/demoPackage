/**
 * 
 */
package StreamDemo.ObjectStreamDemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



/**
 * @author zfh1005
 *
 */
public class ObjectStreamDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Employee harryEmployee = new Employee("Harry Hacker", 50000, 1989, 10, 1);
		Manager carlManager= new Manager("Carl Crcker", 89000, 1986, 10, 5);
		carlManager.setSecretaryEmployee(harryEmployee );
		
		Manager tonyManager = new Manager("Tony Tester", 40000, 1990, 3, 16);
		tonyManager.setSecretaryEmployee(harryEmployee);
		
		Employee[] staffEmployees = new Employee[3];
		staffEmployees[0] = carlManager;
		staffEmployees[1] = harryEmployee;
		staffEmployees[2] = tonyManager;
		
		try{
			//save all employee records to the file employee.data
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("d:\\employee.dat"));
			outputStream.writeObject(staffEmployees);
			outputStream.close();
			
			//retrieve all records into a new array
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("d:\\employee.dat"));
			Employee[] newStaffEmployees = (Employee[]) inputStream.readObject();
			inputStream.close();
			
			//raise secretry's salary
			newStaffEmployees[1].raiseSalary(10);
			
			//print the newly read employee records
			for(Employee e : newStaffEmployees){
				System.out.println(e);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

}
