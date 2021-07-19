package main;

public class Time {
	private static long startTime = 0;
	private static String purpose = "";
	
	public static void startTimer(String s) {
		startTime = System.nanoTime();
		purpose = s;
		
		System.out.println("Starting task: " + purpose + " --------");
	}
	
	public static int endTimer() {
		long timePassed = (System.nanoTime() - startTime) / 1000000;
		System.out.println("It took " + timePassed + " milliseconds to complete task: " + purpose);
		
		return (int) timePassed;
	}
}