package net.gunivers.listgenerator.util.node.commands;

import net.gunivers.listgenerator.util.node.Command;
import net.gunivers.listgenerator.util.node.CommandNode;
import net.gunivers.listgenerator.util.node.EndNode;

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
