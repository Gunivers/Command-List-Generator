package net.gunivers.commandparser.selector;

public enum FieldType {

	DOUBLE("([0-9]+(\\.[0-9]+)?)"),
	DOUBLE_BOUNDED("(([0-9]+(\\.[0-9]+)?)\\.\\.)|(([0-9]+(\\.[0-9]+)?)?(\\.\\.([0-9]+(\\.[0-9]+)?)))|([0-9]+(\\.[0-9]+)?)"),
	STRING("\\w+"),
	INT("[0-9]+"),
	INT_BOUNDED("[0-9]+(..[0-9]*)?"),
	GAMEMODE("survival|creative|spectator|adventure");
	
	private String regex;
	
	FieldType(String regex) {
		this.regex = regex;
	}
	
	public String getMatch() {
		return regex;
	}
	
}
