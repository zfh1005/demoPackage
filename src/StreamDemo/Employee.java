/**
 * a simple employee class
 */
package StreamDemo;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;



/**
 * @author zfh1005
 *
 */
public class Employee {
	/*
	 * constructor
	 * */
	public Employee(String n, double s, int y, int m, int d){
		name = n;
		salary = s;
		GregorianCalendar calendar = new GregorianCalendar(y, m - 1, d);
		hireDay = calendar.getTime();		
	}
	
	/**
	 * constructor
	 */
	public Employee() {
		
	}

	@Override
	public String toString(){
		return getClass().getName() + "[name= " + name + ", salary= " +salary + ", hireDay= " + hireDay + "]";
	}
	
	public void raiseSalary(Double byPercent){
		double raise = salary * byPercent / 100;
		salary += raise;
	}

	/*
	 * write employee data to a print writer
	 * @param out the Print writer
	 * */
	public void writeData(PrintWriter out){		
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(hireDay);
		out.println(name + "|" + salary + "|" + 
		calendar.get(calendar.YEAR) + "|" +
		calendar.get(calendar.MONTH) + "|" +
		calendar.get(Calendar.DAY_OF_MONTH) + "|" );				
	}
	
	/*
	 * read employee data from a buffered reader
	 * @param in the scanner
	 * */
	public void readData(Scanner in){
		String line = in.nextLine();
		String[] tokensStrings = line.split("\\|");
		name = tokensStrings[0];
		salary = Double.parseDouble(tokensStrings[1]);
		int y = Integer.parseInt(tokensStrings[2]);
		int m = Integer.parseInt(tokensStrings[3]);
		int d = Integer.parseInt(tokensStrings[4]);
		GregorianCalendar calendar = new GregorianCalendar(y, m - 1, d);
		hireDay = calendar.getTime();
				
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}	
	
	/**
	 * @return the salary
	 */
	public double getSalary() {
		return salary;
	}

	/**
	 * @return the hireDay
	 */
	public Date getHireDay() {
		return hireDay;
	}

	
	public String name;
	public double salary;
	public Date hireDay;
}
