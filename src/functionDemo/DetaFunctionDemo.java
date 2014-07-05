package functionDemo;

/**
 * @author ZFH
 *
 */

import java.util.Date;
import java.util.Calendar;

public class DetaFunctionDemo {
	
	@SuppressWarnings("deprecation")
	public void printDate(){
		Date start = new Date();
		//start
		System.out.println("start time is:" + start.toString());
		System.out.println((start.getYear() + 1900));
		System.out.println((start.getMonth()+ 1));
		System.out.println(start.getDate());
		System.out.println(start.getHours());
		System.out.println(start.getMinutes());
	}
	public void printCalendar(){	
		Calendar stop = Calendar.getInstance();
		//end
		System.out.println("stop time is:" + stop.toString());
		System.out.println(stop.get(Calendar.YEAR));
		System.out.println(stop.get(Calendar.MONTH) + 1);
		System.out.println(stop.get(Calendar.DAY_OF_MONTH));
		System.out.println(stop.get(Calendar.HOUR));
		System.out.println(stop.get(Calendar.MINUTE));
	}

	public static void main(String[] args) {
		DetaFunctionDemo dateFD = new DetaFunctionDemo();
		dateFD.printDate();
		dateFD.printCalendar();
	}

}
