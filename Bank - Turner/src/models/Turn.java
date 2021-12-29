package models;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Turn {

	String name, document;
	Calendar initTime, finalTime;
	int turnNumber;
	
	public Turn (int turnNumber,String name, String document) {
		this.name = name;
		this.document = document;
		this.turnNumber = turnNumber;
	}
	
	public String labelingTurn() {
		return "<html><p style=\"text-align: center; font-size: 25px;\">NUMERO - TURNO: " + turnNumber +"<br>"
				+ "NOMBRE: " + name + "</br><br>DOCUMENTO: " + document + "</br></p></html>";
	}
	
	public void setInitTime() {
		initTime = GregorianCalendar.getInstance();
	}
	
	public void setFinalTime() {
		finalTime = GregorianCalendar.getInstance();
	}

	private String timeToString(Calendar calendar) {
		return calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);
	}
	
	public String[] dataToStringVector() {
		String turn = "", auxName = "", initT= "", finalT= "";
		if(finalTime == null) {
			turn = String.valueOf(turnNumber);
			auxName = name;
			initT = timeToString(initTime);
		}
		else {
			turn = String.valueOf(turnNumber);
			auxName = name;
			initT = timeToString(initTime);
			finalT = timeToString(finalTime);
		}
		String[] dataVector =  {turn, auxName, initT, finalT};
		return dataVector;
	}
	
	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public int getTurnNumber() {
		return turnNumber;
	}

	public Calendar getInitTime() {
		return initTime;
	}

	public Calendar getFinalTime() {
		return finalTime;
	}
	
}