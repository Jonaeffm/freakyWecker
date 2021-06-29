package freakyWecker;

import java.util.ArrayList;
import java.util.List;

public class saveSettings {
	//private static long startTime;
	private static List<Long> endTimes;
	private static List<String> nameOfWecker;
	
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
		endTimes.remove(i);
		nameOfWecker.remove(i);
	}
}
