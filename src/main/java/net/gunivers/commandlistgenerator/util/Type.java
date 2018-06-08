package net.gunivers.commandlistgenerator.util;

import net.gunivers.commandlistgenerator.CommandListGenerator;

public enum Type {
	
	DEFAULT, FLOAT, DOUBLE, BYTE, SHORT, LONG;
	
	public String getName() {
		return CommandListGenerator.LANGUAGE.get("gui.sequence.type." + toString().toLowerCase());
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
