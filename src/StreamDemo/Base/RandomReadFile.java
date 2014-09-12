/**
 * 
 */
package StreamDemo.Base;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author zfh1005
 *
 */
public class RandomReadFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Employee[] staffEmployees = new Employee[3];
		
		staffEmployees[0] = new Employee("Carl cre", 74000, 1987, 12, 12);
		staffEmployees[1] = new Employee("Harry hacker", 40000, 1988, 9, 6);
		staffEmployees[2] = new Employee("Tony Tim", 65000, 1991, 2, 29);

		try{
			//save all employee records to the file employee.dat
			DataOutputStream outputStream = new DataOutputStream(new FileOutputStream("d:\\emplyee.dat"));
			for(Employee e : staffEmployees){
				e.writeData(outputStream);
			}
			
			//retrieve all records into a new array
			RandomAccessFile in = new RandomAccessFile("d:\\emplyee.dat", "r");
			
			//computer the array size
			int n = (int)(in.length()) / Employee.RECORD_SZIE;
			Employee[] newStaff = new Employee[n];
			
			//read employee in reverse order
			for(int i = n - 1; i >= 0; i--){
				newStaff[i] = new Employee();
				in.seek( i * Employee.RECORD_SZIE);
				newStaff[i].readData(in);
			}
			in.close();
			
			//print the newly read employee records
			for(Employee e : newStaff){
				System.out.println(e);
			}
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

}
