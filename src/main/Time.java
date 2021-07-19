package main;

public class Time {
	private static long startTime = 0;
	private static String purpose = "";
	
	public static void startTimer(String s) {
		startTime = System.nanoTime();
		purpose = s;
	}
	
	public static void endTimer() {
		long timePassed = (System.nanoTime() - startTime);
		System.out.println("It took " + timePassed + " nanoseconds to " + purpose);
	}
}