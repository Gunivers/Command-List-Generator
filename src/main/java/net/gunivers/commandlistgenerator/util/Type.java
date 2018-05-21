package net.gunivers.commandlistgenerator.util;

import com.sun.xml.internal.ws.util.StringUtils;

public enum Type {
	
	FLOAT, DOUBLE, BYTE, SHORT, LONG, NULL;
	
	@Override
	public String toString() {
		return StringUtils.capitalize(name().toLowerCase());
	}

}
