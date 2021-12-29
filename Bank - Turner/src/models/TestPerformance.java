package models;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TestPerformance {

	
	public static void main(String[] args) {
//		Calendar calendar = GregorianCalendar.getInstance();
//		Calendar calendar1 = GregorianCalendar.getInstance();
//		Calendar calendar2 = GregorianCalendar.getInstance();
//		Calendar calendar3 = GregorianCalendar.getInstance();
//		Calendar calendar4 = GregorianCalendar.getInstance();
//		calendar.set(Calendar.HOUR_OF_DAY, 2);
//		calendar.set(Calendar.MINUTE, 50);
//		calendar.set(Calendar.SECOND, 0);
//		calendar1.set(Calendar.HOUR_OF_DAY, 7);
//		calendar1.set(Calendar.MINUTE, 35);
//		calendar1.set(Calendar.SECOND, 0);
//		calendar2.set(Calendar.HOUR, 2);
//		calendar2.set(Calendar.MINUTE, 30);
//		calendar2.set(Calendar.SECOND, 0);
//		calendar3.set(Calendar.HOUR, 2);
//		calendar3.set(Calendar.MINUTE, 30);
//		calendar3.set(Calendar.SECOND, 0);
//		calendar4.set(Calendar.HOUR, 2);
//		calendar4.set(Calendar.MINUTE, 30);
//		calendar4.set(Calendar.SECOND, 0);
//		List<Calendar> timesList = new List<Calendar>();
//		Node<Calendar> timeNode = new Node<Calendar>(calendar);
//		Node<Calendar> timeNode1 = new Node<Calendar>(calendar1);
//		Node<Calendar> timeNode2 = new Node<Calendar>();
//		Node<Calendar> timeNode3 = new Node<Calendar>();
//		Node<Calendar> timeNode4 = new Node<Calendar>();
//		timesList.addNodeToTail(timeNode);
//		timesList.addNodeToTail(timeNode1);		
//		calendar2 = avgTimes(timesList);
//
//		System.out.println(calendar2.get(Calendar.HOUR_OF_DAY) + ":" + calendar2.get(Calendar.MINUTE)
//		 + ":" + calendar2.get(Calendar.SECOND));
	}
	
	
	
	public static Calendar avgTimes(List<Calendar> list) {
		Calendar calendar = GregorianCalendar.getInstance();
		double hours = 0, minutes = 0, seconds = 0;
		Node<Calendar> calendarNode = list.getHeadNode();
		while(calendarNode != null) {
			System.out.println(calendarNode.getData().get(Calendar.HOUR_OF_DAY) + ":"
					+ calendarNode.getData().get(Calendar.MINUTE) + ":"
					+ calendarNode.getData().get((Calendar.SECOND)));
			hours += calendarNode.getData().get(Calendar.HOUR_OF_DAY);
			minutes += calendarNode.getData().get(Calendar.MINUTE);
			seconds += calendarNode.getData().get(Calendar.SECOND);
			calendarNode = calendarNode.getNext();
		}
		
		hours = hours/list.size();
		minutes = minutes/list.size() + (afterPointNumber(String.valueOf(hours))*6);
		seconds = seconds/list.size() + (afterPointNumber(String.valueOf(minutes))*6);
		calendar.set(Calendar.HOUR_OF_DAY, (int)hours);
		calendar.set(Calendar.MINUTE, (int)minutes);
		calendar.set(Calendar.SECOND, (int)seconds);
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
