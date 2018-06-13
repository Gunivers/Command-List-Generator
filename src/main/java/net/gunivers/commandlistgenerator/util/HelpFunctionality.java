package net.gunivers.commandlistgenerator.util;

import java.util.HashMap;

import net.gunivers.commandlistgenerator.CommandListGenerator;
import net.gunivers.core.language.Language;

public abstract class HelpFunctionality {
	
	private static HashMap<String, String> help = new HashMap<String, String>();
	
	protected static Language l = CommandListGenerator.LANGUAGE;
	
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
	
	public static void refreshText() {
		l = CommandListGenerator.LANGUAGE;
	}
}
