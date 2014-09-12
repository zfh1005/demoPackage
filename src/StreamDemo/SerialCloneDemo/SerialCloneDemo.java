/**
 * 
 */
package StreamDemo.SerialCloneDemo;

/**
 * @author zfh1005
 *
 */
public class SerialCloneDemo {
	public static void main(String[] args){
		Employee harryEmployee = new Employee("Harry Hacker", 35004, 1989, 12, 22);
		
		//clone harry
		Employee harryEmployee2 = (Employee) harryEmployee.clone();
		
		//mutate harry 
		harryEmployee.raiseSalary(10);
		
		//now harry and the clone are different
		System.out.println(harryEmployee);
		System.out.println(harryEmployee2);
	}
	
}
