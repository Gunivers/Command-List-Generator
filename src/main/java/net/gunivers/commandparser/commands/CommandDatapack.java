package net.gunivers.commandparser.commands;

import net.gunivers.commandparser.Command;
import net.gunivers.commandparser.node.CommandNode;
import net.gunivers.commandparser.node.EndNode;

public class CommandDatapack extends Command {

	public CommandDatapack() {
		super("datapack");
		
		EndNode end = new EndNode();
		CommandNode existing = new CommandNode("existing", "[/-_a-z0-9]+", end);
		CommandNode name1 = new CommandNode("name", "[/-_a-z0-9]+", end);
		CommandNode first = new CommandNode("first", end);
		CommandNode last = new CommandNode("last", end);
		CommandNode after = new CommandNode("after", existing);
		CommandNode before = new CommandNode("before", existing);
		CommandNode available = new CommandNode("available", end);
		CommandNode enabled = new CommandNode("enabled", end);
		CommandNode name2 = new CommandNode("name", "[/-_a-z0-9]+", after, before, first, last, end);
		CommandNode disable = new CommandNode("disable", name1);
		CommandNode list = new CommandNode("list", available, enabled, end); 
		CommandNode enable = new CommandNode("enable", name2);
		setChildren(list, enable, disable);
	}

}
