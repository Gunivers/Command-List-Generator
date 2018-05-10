package net.gunivers.commandparser.selector;

import net.gunivers.commandparser.selector.FieldType;
import net.gunivers.commandparser.selector.parser.SelectorParser;

public enum SelectorFields {
	ADVANCEMENTS(((String s) -> SelectorParser.parseAdvancement(s))),
	DISTANCE(((String s) -> s.matches(FieldType.DOUBLE_BOUNDED.getMatch()))),
	
	DX((String s) -> s.matches(FieldType.DOUBLE_BOUNDED.getMatch())),
	DY((String s) -> s.matches(FieldType.DOUBLE_BOUNDED.getMatch())),
	DZ((String s) -> s.matches(FieldType.DOUBLE_BOUNDED.getMatch())),
	
	GAMEMODE((String s) -> s.matches(FieldType.GAMEMODE.getMatch())),
	LEVEL((String s) -> s.matches(FieldType.INT_BOUNDED.getMatch())),
	LIMIT((String s) -> s.matches(FieldType.INT.getMatch())),
	NAME((String s) -> s.matches(FieldType.STRING.getMatch())),
	//NBT(NbtParser.class),
	SCORES(((String s) -> SelectorParser.parseScore(s))),
	SORT((String s) -> s.matches("nearest|furthest|random|arbitrary")),
	
	TAG((String s) -> s.matches(FieldType.STRING.getMatch())),
	TEAM((String s) -> s.matches(FieldType.STRING.getMatch())),
	//TYPE(Entity.class),
	
	X((String s) -> s.matches(FieldType.DOUBLE.getMatch())),
	X_ROTATION((String s) -> s.matches(FieldType.DOUBLE_BOUNDED.getMatch())),
	Y((String s) -> s.matches(FieldType.DOUBLE.getMatch())),
	Y_ROTATION((String s) -> s.matches(FieldType.DOUBLE_BOUNDED.getMatch())),
	Z((String s) -> s.matches(FieldType.DOUBLE.getMatch()));
	
	SelectorMatcher match;
	
	private SelectorFields(SelectorMatcher match) {
		this.match = match;
	}
	
	public String toString() {
		return this.name().toLowerCase();
	}
	
	//TODO
	public boolean matches(String value) {return true;}
}
