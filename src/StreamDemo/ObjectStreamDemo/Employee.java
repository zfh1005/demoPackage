/**
 * 
 */
package StreamDemo.ObjectStreamDemo;

import java.io.DataInput;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author zfh1005
 *
 */
public class Employee implements Serializable{
	public Employee(){
		
	}
	
	public Employee(String n, double s, int y, int m, int d){
		name = n;
		salary = s;
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
	
	public void raiseSalary(double byPercent){
		double raise = salary * byPercent / 100;
		salary += raise;
	}

	@Override
	public String toString(){
		return getClass().getName() + "[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
	}


	private String name;
	private double salary;
	private Date hireDay;
}
