/**
 * 
 */
package StreamDemo.ObjectStreamDemo;

/**
 * @author zfh1005
 *
 */
public class Manager extends Employee{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1255674870131876934L;

	/*
	 * constructs a Manager without a secretary
	 * @param n the employee's name
	 * @param s the salary
	 * @param y the hire year
	 * @param m the hire month
	 * @param d the hire day
	 * */
	public Manager(String n, double s, int y, int m, int d){
		super(n, s, y, m, d);
		secretaryEmployee = null;
	}

	@Override
	public String toString(){
		return super.toString() + "[secretary =" + secretaryEmployee + "]";
	}
	

	/**
	 * @return the secretaryEmployee
	 */
	public Employee getSecretaryEmployee() {
		return secretaryEmployee;
	}

	/**
	 * @param secretaryEmployee the secretaryEmployee to set
	 */
	public void setSecretaryEmployee(Employee secretaryEmployee) {
		this.secretaryEmployee = secretaryEmployee;
	}
	
	private Employee secretaryEmployee;
}
