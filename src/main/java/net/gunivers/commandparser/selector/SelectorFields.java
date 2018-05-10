package net.gunivers.commandparser.selector;

public enum SelectorFields {
	//ADVANCEMENTS(AdvancementParser.class, true),
	DISTANCE(((String s) -> true), true),
	DX(((String s) -> true), true),
	DY(((String s) -> true), true),
	DZ(((String s) -> true), true),
	GAMEMODE(((String s) -> true)),
	LEVEL(((String s) -> true)),
	LIMIT(((String s) -> true), true),
	NAME(((String s) -> true)),
	//NBT(NbtParser.class, true),
	//SCORES(ScoreParser.class, true),
	SORT(((String s) -> true), true),
	TAG(((String s) -> true)),
	TEAM(((String s) -> true)),
	//TYPE(Entity.class),
	X(((String s) -> true), true),
	X_ROTATION(((String s) -> true), true),
	Y(((String s) -> true), true),
	Y_ROTATION(((String s) -> true), true),
	Z(((String s) -> true), true);
	
	
	SelectorMatcher match;
	boolean singleton = false;
	
	private SelectorFields(SelectorMatcher match) {
		this.match = match;
	}
	
	private SelectorFields(SelectorMatcher match, boolean singleton) {
		this.match = match;
		this.singleton = singleton;
	}
	
	public String toString() {
		return this.name().toLowerCase();
	}
	
	public boolean matches(String value) {return this.match.matches(value);}
}
