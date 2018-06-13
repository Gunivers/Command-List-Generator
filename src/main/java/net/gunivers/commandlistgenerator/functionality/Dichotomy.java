package net.gunivers.commandlistgenerator.functionality;

import java.util.ArrayList;

import net.gunivers.commandlistgenerator.util.HelpFunctionality;


@Deprecated
public class Dichotomy extends HelpFunctionality  implements java.io.Serializable
{
	private static final long serialVersionUID = 5L;

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

	//TODO OROMIS Remplis on sait pas comment ça fonctionne sinon
	@Override
	public String getHelp() {
		return "Je sais pas comment ça fonctionne :/";
	}
}