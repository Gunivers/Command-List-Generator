package net.gunivers.commandparser.commands;

import net.gunivers.commandparser.Command;
import net.gunivers.commandparser.node.CommandNode;
import net.gunivers.commandparser.node.EndNode;

public class CommandHelp extends Command {

	public CommandHelp() {
		super("help");
		
		EndNode end = new EndNode();
		setMatch("help|\\?");
		setChildren(new CommandNode("commande", "[a-z]+", end), new CommandNode("page", "[0-9]+", end), end);
	}

}
