package net.gunivers;

import net.gunivers.commandparser.Command;
import net.gunivers.commandparser.commands.CommandExecute;
import net.gunivers.commandparser.commands.CommandGameMode;
import net.gunivers.commandparser.commands.CommandTag;

public class ClassTest
{

	public static void main(String[] args)
	{

		new CommandGameMode();
		new CommandTag();
		new CommandExecute();
		
		String commandTest = "tag selector add test";
		
		int result = Command.validSyntax(commandTest);

		System.out.println(result);
		
	}

}
