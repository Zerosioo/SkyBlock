package me.zerosio.utility;

public class Timer {
	private long startTime;
	private long endTime;
	private long elapsedTime;

	public void start() {
		startTime = System.currentTimeMillis();
	}

	public void stop() {
		endTime = System.currentTimeMillis();
		elapsedTime = endTime - startTime;
		// ("Execution time: " + elapsedTime + " milliseconds.");
	}

	public String endTime() {
		return String.valueOf(elapsedTime);
	}
}
