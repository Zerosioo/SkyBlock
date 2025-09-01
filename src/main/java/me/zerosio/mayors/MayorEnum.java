package me.zerosio.mayors;

import me.zerosio.npcs.mayors.*;

public enum MayorEnum {
	AATROX(Aatrox.class),
	DIANA(Diana.class),
	PAUL(Paul.class),
	COLE(Cole.class),
	SMARTYXD(SmartyXD.class),
	MARINA(Marina.class);

	private final Class<? extends Mayor> clazz;

	MayorEnum(Class<? extends Mayor> clazz) {
		this.clazz = clazz;
	}

	public static MayorEnum fromMayor(Mayor mayor) {
		for (MayorEnum val : values()) {
			if (val.clazz.equals(mayor.getClass())) {
				return val;
			}
		}
		return null;
	}

	public static MayorEnum fromString(String name) {
		try {
			return MayorEnum.valueOf(name.toUpperCase());
		} catch (Exception e) {
			return null;
		}
	}

	public Mayor getInstance() {
		try {
			return clazz.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
