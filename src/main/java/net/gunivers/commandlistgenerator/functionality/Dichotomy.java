package net.gunivers.commandlistgenerator.functionality;

import java.util.ArrayList;


@Deprecated
public class Dichotomy {

	public String toString() {
		return "Dichotomy";
	}

	public ArrayList<String> dichotomy(int min, int max) {

		ArrayList<String> commands = new ArrayList<>();

		long l = 1;

		while (l < max) {
			commands.add(String.valueOf(l + min));
			l = 2 * l;
		}

		return commands;
	}
}