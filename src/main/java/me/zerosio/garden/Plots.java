package me.zerosio.garden;

import java.util.Random;

public class Plots {
	private static final String[] PLOT_NAMES = {
		"plot1", "plot2", "plot3", "plot4"
	};
	
	private static final int[] ROTATION = {
		0, 90, 180, 270
	};

	public static String getRandomPlot() {
		return PLOT_NAMES[new Random().nextInt(PLOT_NAMES.length)];
	}
	
	public static int getRandomRotation() {
		return ROTATION[new Random().nextInt(ROTATION.length)];
	}
}