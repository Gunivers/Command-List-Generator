package net.gunivers.commandparser.selector;

public enum SelectorFields {
	//ADVANCEMENTS(AdvancementParser.class, true),
	DISTANCE("[0-9]+(\\.[0-9]+)?", true),
	DX("[0-9]+(\\.[0-9]+)?", true),
	DY("[0-9]+(\\.[0-9]+)?", true),
	DZ("[0-9]+(\\.[0-9]+)?", true),
	GAMEMODE(""),
	LEVEL("[0-9]+"),
	LIMIT("[0-9]+", true),
	NAME("\\w"),
	//NBT(NbtParser.class, true),
	//SCORES(ScoreParser.class, true),
	SORT("\\w", true),
	TAG("\\w"),
	TEAM("\\w"),
	//TYPE(Entity.class),
	X("[0-9]+(\\.[0-9]+)?", true),
	X_ROTATION("[0-9]+(\\.[0-9]+)?", true),
	Y("[0-9]+(\\.[0-9]+)?", true),
	Y_ROTATION("[0-9]+(\\.[0-9]+)?", true),
	Z("[0-9]+(\\.[0-9]+)?", true);
	
	
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
}
