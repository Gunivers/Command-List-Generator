package net.gunivers.commandparser.selector.parser;

import net.gunivers.commandparser.selector.FieldType;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public enum SelectorParser {
	NBTS,
	SCORE("\\w+", FieldType.INT_BOUNDED.getMatch()),
	ADVANCEMENT("(minecraft:)?\\w(/\\w)*", "(minecraft:)?\\w(/\\w)*");

	private String key = null;
	private String value = null;

	private static JSONParser jparse = new JSONParser();

	private SelectorParser(String key, String value) {
		this.key = key;
		this.value = value;
	}

	private SelectorParser() {
	}

	public boolean parse(String compound) {

		if (key == null || value == null) {
			try {
				jparse.parse(compound);
				return true;
			} catch (ParseException e) {
				return false;
			}
		}

		String[] parts = compound.substring(1, value.length() - 2).split(",");

		for (int i = 0; i < parts.length; i++) {
			String[] part = parts[i].split("=");

			if (part.length != 2)
				return false;
			if (!part[0].matches(this.key))
				return false;
			if (!part[1].matches(this.value))
				return false;
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