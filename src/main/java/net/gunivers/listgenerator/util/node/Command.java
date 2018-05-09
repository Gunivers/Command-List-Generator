package net.gunivers.listgenerator.util.node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public abstract class Command extends CommandNode {

	public static ArrayList<Command> instances = new ArrayList<Command>();
	
	public static Command getCommand(String label) {
		for (Command cmd : instances) {
			if (cmd.getTag().equals(label)) return cmd;
		}
		
		return null;
	}
	
	
	public Command(String tag, CommandNode... children) {
		super(tag, children);
		Command.instances.add(this);
	}


	public int hasCorrectSyntax(String command) {
		String[] cmd = (command + " $").split(" ");
		int value = browseAndCompare(cmd, this);
		return value;
	}
	
	private int browseAndCompare(String[] cmd, CommandNode node) {
		if(cmd.length == 1 && node.matches(cmd[0])) return -1;
		else if(cmd.length == 1) return 0;
		else if(!node.matches(cmd[0])) return 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(Node child : node.getChildren()) {
			int i = browseAndCompare(Arrays.copyOfRange(cmd, 1, cmd.length), (CommandNode)child);
			list.add((i == -1) ? -1 : i + 1);
		}
		return (list.contains(-1)) ? -1 : Collections.max(list);
	}
}
