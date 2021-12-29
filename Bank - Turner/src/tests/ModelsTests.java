package tests;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ModelsTests {
	
	public static void main(String[] args) {
		Calendar calendar = GregorianCalendar.getInstance();
		Calendar calendar1 = GregorianCalendar.getInstance();
		
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 2);
		calendar.set(Calendar.SECOND, 9);
		
		calendar1.set(Calendar.HOUR_OF_DAY, 0);
		calendar1.set(Calendar.MINUTE, 11);
		calendar1.set(Calendar.SECOND, 59);
		
		
		System.out.println(timeToString(divideElapsedTime(calendar, 3)));
	}
	
	public static Calendar divideElapsedTime(Calendar hour1, int hoursNumber) {
		Calendar calendar = GregorianCalendar.getInstance();
		double hours = 0, minutes = 0, seconds = 0;
		hours = hour1.get(Calendar.HOUR_OF_DAY)/hoursNumber;
		minutes = hour1.get(Calendar.MINUTE)/hoursNumber + (afterPointNumber(String.valueOf(hours))*6);
		seconds = hour1.get(Calendar.SECOND)/hoursNumber + (afterPointNumber(String.valueOf(minutes))*6);
		calendar.set(Calendar.HOUR_OF_DAY, (int)hours);
		calendar.set(Calendar.MINUTE, (int)minutes);
		calendar.set(Calendar.SECOND, (int)seconds);
		return calendar;
	}
	
	public static Calendar addTime(Calendar hour, Calendar hour1) {
		Calendar calendar = GregorianCalendar.getInstance();
		int hours = 0, minutes = 0, seconds = 0;
		hours = hour.get(Calendar.HOUR_OF_DAY) + hour1.get(Calendar.HOUR_OF_DAY);
		minutes = hour.get(Calendar.MINUTE) + hour1.get(Calendar.MINUTE);
		seconds = hour.get(Calendar.SECOND) + hour1.get(Calendar.SECOND);
		if(seconds >= 60) {
			minutes++;
			seconds -= 60;
		}
		if(minutes >= 60) {
			hours++;
			minutes -= 60;
		}
		calendar.set(Calendar.HOUR_OF_DAY, hours);
		calendar.set(Calendar.MINUTE, minutes);
		calendar.set(Calendar.SECOND, seconds);
		
		return calendar;
	}
	
	public static String timeToString(Calendar hour) {
		return String.valueOf(hour.get(Calendar.HOUR_OF_DAY)) + ":" 
				+ String.valueOf(hour.get(Calendar.MINUTE)) + ":"
				+ String.valueOf(hour.get(Calendar.SECOND));
	}
	
	public static Calendar substractHours(Calendar firstHour, Calendar finalHour) {
		int hours, minutes, seconds;
		Calendar calendar = GregorianCalendar.getInstance();
		seconds = finalHour.get(Calendar.SECOND) - firstHour.get(Calendar.SECOND);
		minutes = finalHour.get(Calendar.MINUTE) - firstHour.get(Calendar.MINUTE);
		hours = finalHour.get(Calendar.HOUR_OF_DAY) - firstHour.get(Calendar.HOUR_OF_DAY);
		if(seconds < 0) {
			minutes--;
			seconds += 60;
		}
		if(minutes < 0) {
			hours--;
			minutes += 60;
		}
		calendar.set(Calendar.HOUR_OF_DAY, hours);
		calendar.set(Calendar.MINUTE, minutes);
		calendar.set(Calendar.SECOND, seconds);	
		return calendar;
	}
	
	public static int afterPointNumber(String string) {
		int pointPosition = 0;
		int number = 0;
		while(string.charAt(pointPosition) != 46) {
			pointPosition++;
		}
		number = string.charAt(pointPosition+1) - 48;
		return number;
	}

}
