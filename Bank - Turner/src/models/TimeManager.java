package models;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeManager {
	
	private Calendar elapsedTime;
	
	public TimeManager() {
		initTimeManager();
	}
	
	public void initTimeManager() {
		elapsedTime = GregorianCalendar.getInstance();
		elapsedTime.set(Calendar.HOUR_OF_DAY, 0);
		elapsedTime.set(Calendar.MINUTE, 0);
		elapsedTime.set(Calendar.SECOND, 0);
	}
	
	public void addTime(Calendar hour) {
		int hours = 0, minutes = 0, seconds = 0;
		hours = hour.get(Calendar.HOUR_OF_DAY) + elapsedTime.get(Calendar.HOUR_OF_DAY);
		minutes = hour.get(Calendar.MINUTE) + elapsedTime.get(Calendar.MINUTE);
		seconds = hour.get(Calendar.SECOND) + elapsedTime.get(Calendar.SECOND);
		if(seconds >= 60) {
			minutes++;
			seconds -= 60;
		}
		if(minutes >= 60) {
			hours++;
			minutes -= 60;
		}
		elapsedTime.set(Calendar.HOUR_OF_DAY, hours);
		elapsedTime.set(Calendar.MINUTE, minutes);
		elapsedTime.set(Calendar.SECOND, seconds);
	}
	
	public Calendar multiplyTime(Calendar time, int multiplier) {
		Calendar calendar = GregorianCalendar.getInstance();
		int hours = 0, minutes = 0, seconds = 0;
		hours = time.get(Calendar.HOUR_OF_DAY)*multiplier;
		minutes = time.get(Calendar.MINUTE)*multiplier;
		seconds = time.get(Calendar.SECOND)*multiplier;
		if(seconds >= 60) {
			seconds = afterPointNumber(String.valueOf(seconds/60));
			minutes += (int)seconds/60;
		}
		if(minutes >= 60) {
			minutes = afterPointNumber(String.valueOf(minutes/60));
			hours += (int)minutes/60; 
		}
		calendar.set(Calendar.SECOND, seconds);
		calendar.set(Calendar.MINUTE, minutes);
		calendar.set(Calendar.HOUR_OF_DAY, hours);
		return calendar;
	}
	
	public Calendar substractHours(Calendar firstHour, Calendar finalHour) {
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
	
	public Calendar divideElapsedTime(int divider) {
		Calendar calendar = GregorianCalendar.getInstance();
		double totalSeconds = 0, seconds = 0, minutes = 0, hours = 0;
		totalSeconds += elapsedTime.get(Calendar.HOUR_OF_DAY)*3600 +
				elapsedTime.get(Calendar.MINUTE)*60 + 
				elapsedTime.get(Calendar.SECOND);
		
		totalSeconds /= divider;
		
		hours = totalSeconds/3600;
		totalSeconds %= 3600;
		minutes = totalSeconds/60;
		totalSeconds %=60;
		seconds = totalSeconds;
		
		calendar.set(Calendar.HOUR_OF_DAY, (int)hours);
		calendar.set(Calendar.MINUTE, (int)minutes);
		calendar.set(Calendar.SECOND, (int)seconds);
		
		return calendar;
	}
	
	public int afterPointNumber(String string) {
		int pointPosition = 0;
		int number = 0;
		while(string.charAt(pointPosition) != 46) {
			pointPosition++;
		}
		number = string.charAt(pointPosition+1) - 48;
		return number;
	}
	
	public String timeToString(Calendar hour) {
		return String.valueOf(hour.get(Calendar.HOUR_OF_DAY)) + ":" 
				+ String.valueOf(hour.get(Calendar.MINUTE)) + ":"
				+ String.valueOf(hour.get(Calendar.SECOND));
	}
	
	public Calendar getElapsedTime() {
		return elapsedTime;
	}
	
	public void resetElapsedTime() {
		elapsedTime = substractHours(elapsedTime, elapsedTime);
		System.out.println("reset" + timeToString(elapsedTime));
	}
}
