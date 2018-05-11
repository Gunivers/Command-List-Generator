package net.gunivers.commandparser.commands;

import net.gunivers.commandparser.Command;
import net.gunivers.commandparser.node.CommandNode;
import net.gunivers.commandparser.node.EndNode;

public class CommandAdvancement extends Command {

	public CommandAdvancement() {
		super("advancement");
		//TODO selector -> Seulement joueur, après grant et revoke
		EndNode end = new EndNode();
		CommandNode advancement1 = new CommandNode("advancement1", "([/\\.-_a-z0-9]+:)[/\\.-_a-z0-9]+", end);
		CommandNode through = new CommandNode("through", advancement1);
		CommandNode from = new CommandNode("from", advancement1);
		CommandNode until = new CommandNode("until", advancement1);
		CommandNode advancement2 = new CommandNode("advancement2", "([/\\.-_a-z0-9]+:)[/\\.-_a-z0-9]+", new CommandNode("criteria", "[a-z-_0-9]+", end), end); //Critère à vérifier
		CommandNode only = new CommandNode("only", advancement2);
		CommandNode selector = new CommandNode("selector", new CommandNode("everything", end), through, from, until, only);
		CommandNode grant = new CommandNode("grant", selector);
		CommandNode revoke = new CommandNode("revoke", selector);
		setChildren(grant, revoke);
	}

}
