package net.gunivers.commandlistgenerator.functionality;

import java.util.ArrayList;

import net.gunivers.core.language.tuple.Tuple;
import net.gunivers.core.language.tuple.Tuple3;

public class Duplication extends Functionality {
	public Duplication() {
		super();
	}

	@Override
	public String toString() {
		return "Duplication";
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
		return "Parameters:"
				+ "\n - Text: It will be duplicated then placed in your command"
				+ "\n - Multiplier: It will multiply your text"
				+ "\n - Adder: After the multiplication, the original text will be added"
				+ "\n"
				+ "\nExample:"
				+ "\n Text:❤; Multiplier:1.0; Adder:0.0"
				+ "\n Command: cmd #Duplication:example#"
				+ "\n cmd ❤"
				+ "\n cmd ❤❤"
				+ "\n cmd ❤❤❤ "
				+ "\n [...]"
				+ "\n Text:❤; Multiplier:5.0; Adder:2.0"
				+ "\n cmd ❤❤❤❤❤❤❤"
				+ "\n cmd ❤❤❤❤❤❤❤❤❤❤❤❤"
				+ "\n cmd ❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤ "
				+ "\n [...]";
	}

}