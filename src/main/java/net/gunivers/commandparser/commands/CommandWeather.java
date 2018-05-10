package net.gunivers.commandparser.commands;

import net.gunivers.commandparser.Command;
import net.gunivers.commandparser.node.CommandNode;
import net.gunivers.commandparser.node.EndNode;

public class CommandWeather extends Command
{

	public CommandWeather()
	{
		super("weather");

		EndNode end = new EndNode();
		CommandNode duration = new CommandNode("duration", end)
		{
			@Override
			public boolean matches(String value)
			{
				try
				{
					int val = Integer.parseInt(value);
					if (val > 0 && val < 1000000) return true;
				} catch (NumberFormatException e)
				{
				}
				return false;
			}
		};

		CommandNode clear = new CommandNode("clear", duration, end);
		CommandNode rain = new CommandNode("rain", duration, end);
		CommandNode thunder = new CommandNode("thunder", duration, end);

		setChildren(clear, rain, thunder);
	}

}
