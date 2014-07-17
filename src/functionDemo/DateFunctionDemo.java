package functionDemo;

/**
 * @author zfh1005
 *
 */

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateFunctionDemo {
	
	@SuppressWarnings("deprecation")
	/*
	 * print <code>Data</code>
	 * */
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
	
	/*
	 * print <code>Calendar</code>
	 * */
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
	
	/*
	 * print <code>GregorianCalendar</code>
	 * */
	public void printGregarionCalendar(){	
		GregorianCalendar gregarionCalendar = new GregorianCalendar();
		//end
		System.out.println("time is:" + gregarionCalendar.toString());
		System.out.println(gregarionCalendar.get(Calendar.YEAR));
		System.out.println(gregarionCalendar.get(Calendar.MONTH) + 1);
		System.out.println(gregarionCalendar.get(Calendar.DAY_OF_MONTH));
		System.out.println(gregarionCalendar.get(Calendar.HOUR));
		System.out.println(gregarionCalendar.get(Calendar.MINUTE));
	}
	
	/*
	 * <code>Date</code> conversion to <code>Calendar</code> format
	 * */
	public Calendar DateConversionToCalendar(Date date){
		/*
		 * class <code>GregorianCalendar</code> is also reference <code>Calendar</code>
		 * */
		/*
		GregorianCalendar gregarionCalendar = (GregorianCalendar) GregorianCalendar.getInstance();
		gregarionCalendar.setTime(date);
		return gregarionCalendar;
		**/
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	/*
	 * <code>Calendar</code> conversion to <code>Date</code> format
	 * */
	public Date CalendarConversionToDate(Calendar calendar){
		Date date = calendar.getTime();		
		return date;
	}
	
	
	/*
	 * <code>Calendar</code> demo
	 * print Calendar
	 * @param	year	print year
	 * */
	public void CalendarDemo(int year){
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(Calendar.YEAR, year);
		System.out.println("#######################");
		System.out.println("Year:" + year);	
		System.out.println("#######################");
		
		for(int iMonthRty = 0; iMonthRty <= 11; iMonthRty++){
			gc.set(Calendar.MONTH, iMonthRty);
			int iMonth = gc.get(Calendar.MONTH);
			
			gc.set(Calendar.DAY_OF_MONTH, 1);
			
			System.out.println("");
			System.out.println("*******************************");
			System.out.println("Month:" +(iMonth+1));	
			System.out.println("**********************");
			System.out.println("Sun	Mon	Tue	Wed	Thu	Fri	Sta");
			
			int iWeekday = gc.get(Calendar.DAY_OF_WEEK);
			for(int iWeekRty = Calendar.SUNDAY; iWeekRty < iWeekday; iWeekRty++){
				System.out.print("	");
			}
			
			do{
				int Day = gc.get(Calendar.DAY_OF_MONTH);
				System.out.printf("%3d	", Day);
				
				if(iWeekday == Calendar.SATURDAY){
					System.out.println();
				}
				gc.add(Calendar.DAY_OF_MONTH, 1);
				iWeekday = gc.get(Calendar.DAY_OF_WEEK);
			}while(gc.get(Calendar.MONTH) == iMonth);
			
			System.out.println("");
			System.out.println("*******************************");
			gc.add(Calendar.MONTH, iMonthRty);
		}		
	}

	public static void main(String[] args) {
		DateFunctionDemo dateFD = new DateFunctionDemo();
		dateFD.CalendarDemo(2013);
	
	}

}
