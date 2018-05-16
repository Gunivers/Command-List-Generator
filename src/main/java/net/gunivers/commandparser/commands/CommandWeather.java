package net.gunivers.commandparser.commands;

import net.gunivers.commandparser.Command;
import net.gunivers.commandparser.node.CommandNode;
import net.gunivers.commandparser.node.EndNode;
import net.gunivers.core.language.Tuple;

public class CommandWeather extends Command
{

	public CommandWeather()
	{
		super("weather");

		EndNode end = new EndNode();
		CommandNode duration = new CommandNode("duration", end)
		{
			@Override
			public Tuple<Integer, String> matches(String value)
			{
				try
				{
					int val = Integer.parseInt(value);
					if (val > 0 && val < 1000000) return new Tuple<Integer, String>(1, null);
				} catch (NumberFormatException e)
				{
				}
				return new Tuple<Integer, String>(0, "La valeur ne correspond pas au format.");
			}
		};

		CommandNode clear = new CommandNode("clear", duration, end);
		CommandNode rain = new CommandNode("rain", duration, end);
		CommandNode thunder = new CommandNode("thunder", duration, end);

		setChildren(clear, rain, thunder);
	}

}
