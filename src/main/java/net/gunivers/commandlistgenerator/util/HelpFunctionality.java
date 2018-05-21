package net.gunivers.commandlistgenerator.util;

import java.util.HashMap;

public abstract class HelpFunctionality {
	
	private static HashMap<String, String> help = new HashMap<String, String>();
	
	{
		help.putIfAbsent(this.getClass().getName(), this.getHelp());
	}
	
	public abstract String getHelp();
	
	public static String getHelp(String name) {
		return help.get(name);
	}
	
	public static HashMap<String, String> getHelpList() {
		return help;
	}
}
