package freakyWecker;

public class varContainer {
	private static saveSettings savVar;
	private static boolean firstTime;
	private static boolean minuteChecked;
	private static boolean minute;
	private static int i;
	public static saveSettings getSavVar() {
		return savVar;
	}
	public static void setSavVar(saveSettings savVar) {
		varContainer.savVar = savVar;
	}
	public static boolean isFirstTime() {
		return firstTime;
	}
	public static void setFirstTime(boolean firstTime) {
		varContainer.firstTime = firstTime;
	}
	public static boolean isMinuteChecked() {
		return minuteChecked;
	}
	public static void setMinuteChecked(boolean minuteChecked) {
		varContainer.minuteChecked = minuteChecked;
	}
	public static boolean isMinute() {
		return minute;
	}
	public static void setMinute(boolean minute) {
		varContainer.minute = minute;
	}
	public static int getI() {
		return i;
	}
	public static int setI(int i) {
		varContainer.i = i;
		return i;
	}

}
