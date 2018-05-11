package net.gunivers;

import net.gunivers.commandparser.Command;
import net.gunivers.commandparser.commands.CommandDatapack;
import net.gunivers.commandparser.commands.CommandGamemode;
import net.gunivers.commandparser.commands.CommandTag;

public class ClassTest
{

	public static void main(String[] args)
	{

		new CommandGamemode();
		new CommandTag();
		new CommandDatapack();
		
		String commandTest = "datapack list available";
		
		int result = Command.validSyntax(commandTest);

		System.out.println(result);
		
	}

}
