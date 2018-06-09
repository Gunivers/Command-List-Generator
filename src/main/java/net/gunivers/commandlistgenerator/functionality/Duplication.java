package net.gunivers.commandlistgenerator.functionality;

import java.util.ArrayList;

import net.gunivers.core.utils.tuple.Tuple;
import net.gunivers.core.utils.tuple.Tuple3;

public class Duplication extends Functionality {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5144240444727305718L;

	@Override
	public String toString() {
		return l.get("gui.duplication.title");
	}

	@Override
	public ArrayList<String> generate(Tuple t, Integer nbLoop) {
		// <string, multiplier, add>
		Tuple3<String, Double, Double> tuple = Tuple.castTo(t,
				Tuple.newTuple(String.class, Double.class, Double.class));
		ArrayList<String> commands = new ArrayList<>();

		for (int i = 0; i < nbLoop; i++)
			commands.add(duplicate(tuple._1, (int) Math.round((i + 1) * tuple._2 + tuple._3)));

		return commands;
	}

	private String duplicate(String txt, int times) {

		StringBuilder ret = new StringBuilder();

		for (int i = 0; i < times; i++)
			ret.append(txt);

		return ret.toString();
	}

	@Override
	public String getHelp() {
		String text =  l.get("gui.duplication.parameter.text");
		String multiplier =  l.get("gui.duplication.parameter.multiplier");
		String adder =  l.get("gui.duplication.parameter.adder");
		
		return l.get("gui.functionalities.description.parameters")
				+ "\n - " + text + ": "  + l.get("gui.duplication.description.text")
				+ "\n - " + multiplier + ": " + l.get("gui.duplication.description.multiplier")
				+ "\n - " + adder + ": " + l.get("gui.duplication.description.adder")
				+ "\n"
				+ "\n" + l.get("gui.functionalities.example") + ":"
				+ "\n " + text + ":❤; " + multiplier + ":1.0; " + adder + ":0.0"
				+ "\n " + l.get("gui.commandlistgenerator.command") + ": cmd #Duplication:example#"
				+ "\n cmd ❤"
				+ "\n cmd ❤❤"
				+ "\n cmd ❤❤❤ "
				+ "\n [...]"
				+ "\n " + text + ":❤; " + multiplier + ":5.0; " + adder + ":2.0"
				+ "\n cmd ❤❤❤❤❤❤❤"
				+ "\n cmd ❤❤❤❤❤❤❤❤❤❤❤❤"
				+ "\n cmd ❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤ "
				+ "\n [...]";
	}

	@Override
	public String getDefaultName() {
		return "Duplication";
	}

}