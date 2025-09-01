package me.zerosio.mayors;

import me.zerosio.npcs.build.AbstractNPC;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public interface Mayor {

	List<Perk> getPerks();

	default List<String> getFormattedPerks() {
		List<String> lines = new ArrayList<>();
		lines.add("§8§m--------------------------");
		for (Perk perk : getPerks()) {
			lines.add("§6✯ " + perk.getTitle());
			for (String desc : perk.getDescription()) {
				lines.add(desc);
			}
			lines.add("");
		}
		lines.add("§8§m--------------------------");
		lines.add("");
		return lines;
	}


	default String getIdOfMayor() {
		return this.getClass().getSimpleName().toLowerCase();
	}

	AbstractNPC createNPC();

	String getDisplayName();

	default boolean isSpecialMayor() {
		return false;
	}

	default int getSpecialInterval() {
		return -1; // e.g every 8 years so put 8
	}

	default void onActive() {
		for (Perk perk : getPerks()) {
			perk.activate(this);
		}
	}

	static Map<Mayor, MayorRole> ROLE_MAP = new WeakHashMap<>();

	default MayorRole getRole() {
		return ROLE_MAP.getOrDefault(this, MayorRole.NONE);
	}

	default void setRole(MayorRole role) {
		ROLE_MAP.put(this, role);
	}

	default boolean isMayor() {
		return getRole() == MayorRole.MAYOR;
	}

	default boolean isMinister() {
		return getRole() == MayorRole.MINISTER;
	}

	default boolean isCandidate() {
		return getRole() == MayorRole.NONE;
	}

	default String getColorCode() {
		String name = getDisplayName();
		if (name != null && name.length() >= 2 && name.charAt(0) == '§') {
			return name.substring(0, 2);
		}
		return "§f"; 
	}

}
