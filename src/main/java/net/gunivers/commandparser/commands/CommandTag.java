package net.gunivers.commandparser.commands;

import net.gunivers.commandparser.Command;
import net.gunivers.commandparser.node.CommandNode;
import net.gunivers.commandparser.node.EndNode;

public class CommandTag extends Command {

	public CommandTag() {
		super("tag");
		
		EndNode end = new EndNode();
		CommandNode name = new CommandNode("Name", "\\w+", end);
		CommandNode add = new CommandNode("add", name);
		CommandNode remove = new CommandNode("remove", name);
		CommandNode target = new CommandNode("selector", add, remove, new CommandNode("list", end));
		setChildren(target);
		
	}

}
