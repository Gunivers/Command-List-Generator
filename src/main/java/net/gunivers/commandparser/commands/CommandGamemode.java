package net.gunivers.commandparser.commands;

import net.gunivers.commandparser.Command;
import net.gunivers.commandparser.node.CommandNode;
import net.gunivers.commandparser.node.EndNode;

public class CommandGamemode extends Command {

	public CommandGamemode() {
		super("gamemode");
		
		//TODO selector (toute fin)
		EndNode end = new EndNode();
		setChildren(new CommandNode("survival", end), new CommandNode("creative", end), new CommandNode("adventure", end), new CommandNode("spectator", end), end);
	}

}
