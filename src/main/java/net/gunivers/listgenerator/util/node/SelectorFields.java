package net.gunivers.listgenerator.util.node;

import net.gunivers.core.minecraft.Entity;

@SuppressWarnings("rawtypes")
public enum SelectorFields {
	ADVANCEMENTS(AdvancementParser.class, true),
	DISTANCE(Double.class, true),
	DX(Double.class, true),
	DY(Double.class, true),
	DZ(Double.class, true),
	GAMEMODE(Integer.class),
	LEVEL(Integer.class),
	LIMIT(Integer.class, true),
	NAME(String.class),
	NBT(NbtParser.class, true),
	SCORES(ScoreParser.class, true),
	SORT(String.class, true),
	TAG(String.class),
	TEAM(String.class),
	TYPE(Entity.class),
	X(Double.class),
	X_ROTATION(Double.class),
	Y(Double.class),
	Y_ROTATION(Double.class),
	Z(Double.class);
	
	
	Class clazz;
	boolean singleton = false;
	
	private SelectorFields(Class parameter) {
		this.clazz = parameter;
	}
	
	private SelectorFields(Class parameter, boolean singleton) {
		this.clazz = parameter;
		this.singleton = singleton;
	}
	
	public String toString() {
		return this.name().toLowerCase();
	}
	
	public Class getParameter() {
		return this.clazz;
	};
}
