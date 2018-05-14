package net.gunivers.commandparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import net.gunivers.commandparser.node.CommandNode;
import net.gunivers.commandparser.node.Node;

public class Command extends CommandNode {
	
	private static Command commandTree;
	private static boolean treeIsDefined = false;

	public static Command getCommand(String label) {
		return (Command) commandTree.getChild(label);
	}

	public static CommandNode getTree() {
		return commandTree;
	}

	public Command(String tag, CommandNode... children) {
		super(tag, children);
		if(!treeIsDefined) {
			treeIsDefined = true;
			commandTree = new Command("command");
			commandTree.setSilent(true);
		}
		if(!tag.equals("command"))
			commandTree.addChild(this);
	}
	
	public static String validSyntax(String command) {
		int result = ((Command)commandTree).hasCorrectSyntax(command);
		StringBuilder msg = new StringBuilder();
		if(result == -1)
			msg.append("Aucune erreur n'a été détectée.\n");
		else {
			msg.append("Une erreur a été détectée à la position " + result);
			msg.append('\n' + command + '\n');
			Stream.generate(() -> ' ').limit(result).forEach(msg::append);
			msg.append("^");
		}
		return msg.toString();
	}

	public int hasCorrectSyntax(String command) {
		String[] cmd = (command + " $").split(" ");
		int value = browseAndCompare(cmd, this);
		return value;
	}

	private int browseAndCompare(String[] cmd, CommandNode node) {
		int range = node.isSilent() ? 0 : 1;
		int match = node.matches(cmd[0]);
		if (cmd.length == 1 && match == 1)
			return -1;
		else if (cmd.length == 1)
			return 0;
		else if (range > 0 && match == 0)
			return 0;
		else if (range > 0 && match > 1)
			return match;
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (Node child : node.getChildren()) {
			int i = browseAndCompare(Arrays.copyOfRange(cmd, range, cmd.length), (CommandNode) child);
			list.add((i == -1) ? -1 : (range > 0 ? i + cmd[0].length() + 1 : i));
		}
		return (list.contains(-1)) ? -1 : Collections.max(list);
	}
}
