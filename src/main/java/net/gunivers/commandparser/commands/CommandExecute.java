package net.gunivers.commandparser.commands;

import net.gunivers.commandparser.Command;
import net.gunivers.commandparser.node.CommandNode;

public class CommandExecute extends Command {

	public CommandExecute() {
		super("execute");
		
		CommandNode ifExec = new CommandNode("if", this);
		setChildren(ifExec);
	}

}
