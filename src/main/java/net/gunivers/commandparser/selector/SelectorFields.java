package net.gunivers.commandparser.selector;

import net.gunivers.commandparser.selector.FieldType;

public enum SelectorFields {
	//ADVANCEMENTS(AdvancementParser.class, true),
	DISTANCE(FieldType.DOUBLE_BOUNDED.getMatch(), true),
	DX(FieldType.DOUBLE_BOUNDED.getMatch(), true),
	DY(FieldType.DOUBLE_BOUNDED.getMatch(), true),
	DZ(FieldType.DOUBLE_BOUNDED.getMatch(), true),
	GAMEMODE(""),
	LEVEL(FieldType.INT.getMatch()),
	LIMIT(FieldType.INT.getMatch(), true),
	NAME(FieldType.STRING.getMatch()),
	//NBT(NbtParser.class, true),
	//SCORES(ScoreParser.class, true),
	SORT(FieldType.STRING.getMatch(), true),
	TAG(FieldType.STRING.getMatch()),
	TEAM(FieldType.STRING.getMatch()),
	//TYPE(Entity.class),
	X(FieldType.DOUBLE.getMatch(), true),
	X_ROTATION(FieldType.DOUBLE.getMatch(), true),
	Y(FieldType.DOUBLE.getMatch(), true),
	Y_ROTATION(FieldType.DOUBLE.getMatch(), true),
	Z(FieldType.DOUBLE.getMatch(), true);
	
	
	String match;
	boolean singleton = false;
	
	private SelectorFields(String match) {
		this.match = match;
	}
	
	private SelectorFields(String match, boolean singleton) {
		this.match = match;
		this.singleton = singleton;
	}
	
	public String toString() {
		return this.name().toLowerCase();
	}
	
	public String getParameter() {
		return this.match;
	};
	
	//TODO
	public boolean matches(String value) {return true;}
}
