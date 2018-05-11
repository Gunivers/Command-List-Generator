package net.gunivers.commandparser.commands;

import net.gunivers.commandparser.Command;
import net.gunivers.commandparser.node.CommandNode;
import net.gunivers.commandparser.node.EndNode;

public class CommandTrigger extends Command {

	public CommandTrigger() {
		super("trigger");
		EndNode end = new EndNode();
		CommandNode value = new CommandNode("(-)?[0-9]+", end);
		CommandNode add = new CommandNode("add", value);
		CommandNode set = new CommandNode("set", value);
		CommandNode objective = new CommandNode("objective", "\\S", add, set, end);
		setChildren(objective);
	}

}
