package net.gunivers.commandparser.commands;

import net.gunivers.commandparser.Command;
import net.gunivers.commandparser.node.CommandNode;
import net.gunivers.commandparser.node.EndNode;
import net.gunivers.commandparser.selector.SelectorNode;

public class CommandGamemode extends Command {

	public CommandGamemode() {
		super("gamemode");
		
		//TODO selector (toute fin)
		EndNode end = new EndNode();
		SelectorNode selector = new SelectorNode("selector", end);
		setChildren(
				new CommandNode("survival", selector, end), 
				new CommandNode("creative", selector, end), 
				new CommandNode("adventure", selector, end), 
				new CommandNode("spectator", selector, end), 
			end);
	}

}
