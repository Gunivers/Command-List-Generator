package net.gunivers.commandlistgenerator.functionality;

import java.util.ArrayList;

import net.gunivers.commandlistgenerator.util.Call;

public class Dichotomy extends Functionality {

	@Override
	public String toString() {
		return "Dichotomy";
	}

	@Call
	public ArrayList<String> dichotomy(int min, int max) {

		ArrayList<String> commands = new ArrayList<>();

		long l = 1;

		while (l < max) {
			commands.add(String.valueOf(l + min));
			l = 2 * l;
		}

		return commands;
	}

	@Override
	public ArrayList<Object> callParameterOverlay() {
		// TODO
		return null;
	}
}