/**
 * test stream input and output
 */
package StreamDemo.Base;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author zfh1005
 *
 */
public class TextFileTest {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Employee[] staffEmployees = new Employee[3];
		
		staffEmployees[0] = new Employee("Carl cre", 74000, 1987, 12, 12);
		staffEmployees[1] = new Employee("Harry hacker", 40000, 1988, 9, 6);
		staffEmployees[2] = new Employee("Tony Tim", 65000, 1991, 2, 29);
		try {
			//save all employee records to the file emplyee.dat
			PrintWriter out;
			out = new PrintWriter("d:\\emplyee.dat");			
			writeData(staffEmployees, out);
			out.close();
			
			//retrieve all records into a new array
			final Scanner in = new Scanner(new FileReader("d:\\emplyee.dat"));
			final Employee[] newStaff = readData(in);
			in.close();
			
			//print the newly read employee records
			for(final Employee e : newStaff){
				System.out.println(e);			
			}
		} 
		catch (final IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/*
	 * write all employees in an array to a print writer
	 * @param employees an array of employees
	 * @param out a print write
	 * */
	private static void writeData(Employee[] employees, PrintWriter out)throws IOException {
		//write number of employees
		out.println(employees.length);
		for(Employee e : employees){
			e.writeData(out);
		}
	}
	
	/*
	 * reads an array of employees from a scanner
	 * @param in the scanner
	 * @return the array of employee
	 * */
	private static Employee[] readData(Scanner in){
		//retrieve the array size
		int n = in.nextInt();
		in.nextLine();//consume newline
		
		Employee[] emplotees = new Employee[n];
		for(int i = 0; i < n; i++){
			emplotees[i] = new Employee();
			emplotees[i].readData(in);
		}
		return emplotees;
	}

}
