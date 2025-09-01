package me.zerosio.profile;

import java.util.Random;

public class ProfileTypes {
	private static final String[] PROFILE_NAMES = {
		"Apple", "Banana", "Blueberry", "Cucumber", "Coconut",
		"Grapes", "Kiwi", "Lemon", "Lime", "Mango", "Orange",
		"Papaya", "Pineapple", "Peach", "Pear", "Pomegranate",
		"Raspberry", "Strawberry", "Tomato", "Watermelon", "Zucchini"
	};

	public static String getRandomProfileName() {
		return PROFILE_NAMES[new Random().nextInt(PROFILE_NAMES.length)];
	}
}
