package net.gunivers.commandparser.selector.parser;

import net.gunivers.commandparser.selector.FieldType;

public class SelectorParser
{

	public static boolean parseScore(String value)
	{
		String[] scores = value.substring(1, value.length()).split(",");

		for (int i = 0; i < scores.length; i++)
		{
			String[] score = scores[i].split("=");

			if (score.length != 2) return false;
			if (!score[0].matches("\\w+")) return false;
			if (!score[1].matches(FieldType.INT_BOUNDED.getMatch())) return false;
		}

		return true;
	}

	public static boolean parseAdvancement(String value)
	{
		String[] advancements = value.substring(1, value.length() - 2).split(",");

		for (int i = 0; i < advancements.length; i++)
		{
			String[] advancement = advancements[i].split("=");

			if (advancement.length != 2) return false;
			if (!advancement[0].matches("(minecraft:)?(\\w|/\\w)+")) return false;
			if (!advancement[1].matches("true|false")) return false;
		}

		return true;
	}
}