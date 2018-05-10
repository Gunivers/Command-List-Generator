package net.gunivers.commandparser.selector;

public enum SelectorFields
{
	//ADVANCEMENTS(AdvancementParser.class, true),
	DISTANCE(((String s) -> s.matches(FieldType.DOUBLE_BOUNDED.getMatch())), true),
	DX((String s) -> s.matches(FieldType.DOUBLE_BOUNDED.getMatch()), true),
	DY((String s) -> s.matches(FieldType.DOUBLE_BOUNDED.getMatch()), true),
	DZ((String s) -> s.matches(FieldType.DOUBLE_BOUNDED.getMatch()), true),
	GAMEMODE((String s) -> s.matches(FieldType.GAMEMODE.getMatch())),
	LEVEL((String s) -> s.matches(FieldType.INT.getMatch())),
	LIMIT((String s) -> s.matches(FieldType.INT.getMatch()), true),
	NAME((String s) -> s.matches(FieldType.STRING.getMatch())),
	//NBT(NbtParser.class, true),
	//SCORES(ScoreParser.class, true),
	SORT((String s) -> s.matches("nearest|furthest|random|arbitrary"), true),
	TAG((String s) -> s.matches(FieldType.STRING.getMatch())),
	TEAM((String s) -> s.matches(FieldType.STRING.getMatch())),
	//TYPE(Entity.class),
	X((String s) -> s.matches(FieldType.DOUBLE.getMatch()), true),
	X_ROTATION((String s) -> s.matches(FieldType.DOUBLE.getMatch()), true),
	Y((String s) -> s.matches(FieldType.DOUBLE.getMatch()), true),
	Y_ROTATION((String s) -> s.matches(FieldType.DOUBLE.getMatch()), true),
	Z((String s) -> s.matches(FieldType.DOUBLE.getMatch()), true);


	SelectorMatcher match;
	boolean singleton = false;

	private SelectorFields(SelectorMatcher match)
	{
		this.match = match;
	}

	private SelectorFields(SelectorMatcher match, boolean singleton)
	{
		this.match = match;
		this.singleton = singleton;
	}

	public String toString()
	{
		return this.name().toLowerCase();
	}

	//TODO
	public boolean matches(String value)
	{
		return true;
	}
}
