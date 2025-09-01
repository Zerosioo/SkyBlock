package me.zerosio.user;

import org.bukkit.entity.Player;

import me.zerosio.localization.LocalizationLanguages;

public class LanguageSelected {
	public static LocalizationLanguages getSelectedLanguage(Player player) {
		User user = User.getUser(player);
		return LocalizationLanguages.valueOf(user.getData("selected_language"));
	}
}