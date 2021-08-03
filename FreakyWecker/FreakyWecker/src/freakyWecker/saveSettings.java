package freakyWecker;

import java.util.ArrayList;
import java.util.List;

public class saveSettings {
	//private static long startTime;
	//times of the alarm clocks
	private static List<Long> endTimes;
	//names of the alerm clocks
	private static List<String> nameOfWecker;
	
	private static String pfad;
	private static String dName;
	private static String zeitZoneGespeichert;
	
	private dBOptions DBO = new dBOptions();
	private String  language="e";
	
	//constructor
	public saveSettings()
	{
	
		//long startTime = (long)0;
		endTimes=new ArrayList<Long>();
		 nameOfWecker = new ArrayList<String>();
		}
	
	
	
	public long getEndTime(int value) {
		return endTimes.get(value);
	}
	
	public void setEndTime(long endTime) {
		endTimes.add(endTime);
		//System.out.println("long "+endTime+" wurde hinzugefügt.");
	}
	
	/*public static long getStartTime() {
		return startTime;
	}
	
	public static void setStartTime(long startTime) {
		saveSettings.startTime = startTime;
	}*/

	public String getNameOfWecker(int i) {
		return nameOfWecker.get(i);
	}

	public void setNameOfWecker(String nameOfWecker) {
		saveSettings.nameOfWecker.add(nameOfWecker);
	}
	
	public int sizeOfEndTimes()
	{
		return endTimes.size();
	}
	
	public static void deleteEndTime(int i)
	{
		//System.out.println("deleted");
		endTimes.remove(i);
		nameOfWecker.remove(i);
	}



	public static String getPfad() {
		return pfad;
	}



	public static void setPfad(String pfad) {
		saveSettings.pfad = pfad;
	}



	public static String getdName() {
		return dName;
	}



	public static void setdName(String dName) {
		saveSettings.dName = dName;
	}



	public static String getZeitZoneGespeichert() {
		return zeitZoneGespeichert;
	}



	public static void setZeitZoneGespeichert(String zeitZoneGespeichert) {
		saveSettings.zeitZoneGespeichert = zeitZoneGespeichert;
	}



	public dBOptions getDBO() {
		return DBO;
	}



	public void setDBO(dBOptions dBO) {
		DBO = dBO;
	}



	public String getLanguage() {
		return language;
	}



	public void setLanguage(String language) {
		this.language = language;
	}



	
}
