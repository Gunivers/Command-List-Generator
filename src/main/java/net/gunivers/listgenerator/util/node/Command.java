package net.gunivers.listgenerator.util.node;

import java.util.ArrayList;

public abstract class Command extends Node {

	public static ArrayList<Command> instances = new ArrayList<Command>();
	
	public static Command getCommand(String label) {
		for (Command cmd : instances) {
			if (cmd.getTag().equals(label)) return cmd;
		}
		
		return null;
	}
	
	
	public Command(String tag, Node... children) {
		super(tag, children);
		Command.instances.add(this);
	}


	/*public int hasCorrectSyntax(String command) {
		
		String[] cmd = command.split(" ");
		
		Command current = Command.getCommand(cmd[0]);
		
		if (current == null) return 0;
		
		for (int i = 1; i < cmd.length; i++) {
			if (current.hasChild(cmd[i])) {
				current = (Command) current.getChild(cmd[i]);
			}
			
			else return i;
		}
		
		return -1;
	}*/
}
