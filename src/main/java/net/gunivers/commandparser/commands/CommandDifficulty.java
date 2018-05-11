package net.gunivers.commandparser.commands;

import net.gunivers.commandparser.Command;
import net.gunivers.commandparser.node.CommandNode;
import net.gunivers.commandparser.node.EndNode;

public class CommandDifficulty extends Command {

	public CommandDifficulty() {
		super("difficulty");
		EndNode end = new EndNode();
		setChildren(new CommandNode("easy", end), new CommandNode("hard", end), new CommandNode("normal", end), new CommandNode("peaceful", end), end);
	}

}
