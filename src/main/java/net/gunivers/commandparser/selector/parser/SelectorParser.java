package net.gunivers.commandparser.selector.parser;

import net.gunivers.commandparser.selector.FieldType;

public class SelectorParser {
	
	public static boolean parseScore(String value) {
		String[] scores = value.substring(1, value.length() -2).split(",");
		
		for(int i = 0; i < scores.length; i++) {
			String[] score = scores[i].split("=");
			
			if (score.length != 2) return false;
			if (!score[0].matches("\\w")) return false;
			if (!score[1].matches(FieldType.DOUBLE_BOUNDED.getMatch())) return false;
		}
		
		return true;
	}
	
	public static boolean parseAchievement(String value) {
		
		return true;
	}
}