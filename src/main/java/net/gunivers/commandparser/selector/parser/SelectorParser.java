package net.gunivers.commandparser.selector.parser;

import net.gunivers.commandparser.selector.FieldType;

public enum SelectorParser
{
	SCORE("\\w+", FieldType.DOUBLE_BOUNDED.getMatch()),
	ADVANCEMENT("(minecraft:)?\\w(/\\w)*", "(minecraft:)?\\w(/\\w)*");
	
	private String key;
	private String value;
	
	private SelectorParser(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public boolean parse(String compound) {

		String[] parts = compound.substring(1, value.length() - 2).split(",");

		for (int i = 0; i < parts.length; i++)
		{
			String[] part = parts[i].split("=");

			if (part.length != 2) return false;
			if (!part[0].matches(this.key)) return false;
			if (!part[1].matches(this.value)) return false;
		}
		
		return true;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}
}