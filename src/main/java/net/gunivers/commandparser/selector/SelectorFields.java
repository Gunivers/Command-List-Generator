package net.gunivers.commandparser.selector;

import net.gunivers.commandparser.selector.FieldType;
import net.gunivers.commandparser.selector.parser.SelectorParser;

public enum SelectorFields {
	ADVANCEMENTS(((String s) -> SelectorParser.ADVANCEMENTS.parse(s))),
	
	DISTANCE(((String s) -> s.matches(FieldType.DOUBLE_BOUNDED.getMatch()))),
	
	DX((String s) -> s.matches(FieldType.DOUBLE_BOUNDED.getMatch())),
	DY((String s) -> s.matches(FieldType.DOUBLE_BOUNDED.getMatch())),
	DZ((String s) -> s.matches(FieldType.DOUBLE_BOUNDED.getMatch())),
	
	//TODO Remove TEST field in SelectorFields
	TEST((String s) -> s.matches(".*")),
	
	GAMEMODE((String s) -> s.matches("spectator|adventure|survival|creative"), 1),
	LEVEL((String s) -> s.matches(FieldType.INT_BOUNDED.getMatch())),
	
	LIMIT((String s) -> s.matches(FieldType.INT.getMatch())),
	
	NAME((String s) -> s.matches(FieldType.STRING.getMatch()), 1),
	
	//TODO NBTS
	//NBTS((String s) -> SelectorParser.NBTS.parse(s);
	SCORES(((String s) -> SelectorParser.SCORES.parse(s))),
	
	SORT((String s) -> s.matches("nearest|furthest|random|arbitrary")),
	
	TAG((String s) -> s.matches(FieldType.STRING.getMatch()), 2),
	TEAM((String s) -> s.matches(FieldType.STRING.getMatch()), 1),
	//TYPE(Entity.class),
	
	X((String s) -> s.matches(FieldType.DOUBLE.getMatch())),
	X_ROTATION((String s) -> s.matches(FieldType.DOUBLE_BOUNDED.getMatch())),
	Y((String s) -> s.matches(FieldType.DOUBLE.getMatch())),
	Y_ROTATION((String s) -> s.matches(FieldType.DOUBLE_BOUNDED.getMatch())),
	Z((String s) -> s.matches(FieldType.DOUBLE.getMatch()));
	
	private SelectorMatcher match;
	private int canBeRepeated;
	
	private SelectorFields(SelectorMatcher match) {
		this.match = match;
		this.canBeRepeated = 0;
	}
	
	/**
	 * @param match a string
	 * @param canBeRepeated 0 to can't be repeated, 1 to can be only in '!' and 2 to always
	 */
	private SelectorFields(SelectorMatcher match, int canBeRepeated) {
		this.match = match;
		this.canBeRepeated = canBeRepeated;
	}
	
	public String toString() {
		return this.name().toLowerCase();
	}
	
	public boolean matches(String value) {
		return match.matches(value);
	}

	public int canBeRepeated() {
		return canBeRepeated;
	}
}
