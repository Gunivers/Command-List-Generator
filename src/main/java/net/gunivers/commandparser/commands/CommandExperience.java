package net.gunivers.commandparser.commands;

import net.gunivers.commandparser.Command;
import net.gunivers.commandparser.node.CommandNode;
import net.gunivers.commandparser.node.EndNode;

public class CommandExperience extends Command {

	public CommandExperience() {
		super("experience");
		EndNode end = new EndNode();
		CommandNode level = new CommandNode("level", end);
		CommandNode point = new CommandNode("point", end);
		CommandNode amount = new CommandNode("amount", "(-)?[0-9]+", level, point, end);
		CommandNode target = new CommandNode("selector", amount);
		CommandNode add = new CommandNode("add", target);
		CommandNode set = new CommandNode("set", target);
		CommandNode target2 = new CommandNode("selector", level, point);
		CommandNode query = new CommandNode("query", target2);
		setChildren(set, add, query);
	}

}
