package net.gunivers.listgenerator.util.node;

import java.util.ArrayList;

public abstract class CommandNode extends Node {

	public static ArrayList<CommandNode> instances = new ArrayList<CommandNode>();
	
	public CommandNode(String tag, Node ... children) {
		super(tag, children);
		CommandNode.instances.add(this);
	}

	public static CommandNode getCommand(String label) {
		for (CommandNode cmd : instances) {
			if (cmd.getTag().equals(label)) return cmd;
		}
		
		return null;
	}

	public int hasCorrectSyntax(String command) {
		
		String[] cmd = command.split(" ");
		
		CommandNode current = CommandNode.getCommand(cmd[0]);
		
		if (current == null) return 0;
		
		for (int i = 1; i < cmd.length; i++) {
			if (current.hasChild(cmd[i])) {
				current = (CommandNode) current.getChild(cmd[i]);
			}
			
			else return i;
		}
		
		return -1;
	}
}
