package net.gunivers.commandlistgenerator.util;

import com.sun.xml.internal.ws.util.StringUtils;

public enum Type {
	
	DEFAULT, FLOAT, DOUBLE, BYTE, SHORT, LONG;
	
	public String getName() {
		return StringUtils.capitalize(name().toLowerCase());
	}

	public String getDefaultName() {
		return this.toString();
	}
	
	public static Type getByName(String name) {
		for(Type t : Type.values())
			if(t.getName().equals(name))
				return t;
		return null;
	}
}
