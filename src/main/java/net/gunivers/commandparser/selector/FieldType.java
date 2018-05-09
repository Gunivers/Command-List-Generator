package net.gunivers.commandparser.selector;

public enum FieldType {

	DOUBLE("([0-9]+(\\.[0-9]+)?)"),
	DOUBLE_BOUNDED("(([0-9]+(\\.[0-9]+)?)\\.\\.)|(([0-9]+(\\.[0-9]+)?)?(\\.\\.([0-9]+(\\.[0-9]+)?)))|([0-9]+(\\.[0-9]+)?)"),
	STRING("\\w+"),
	INT("[0-9]+");
	
	private String match;
	
	FieldType(String match) {
		this.match = match;
	}
	
	public String getMatch() {
		return match;
	}
	
}
