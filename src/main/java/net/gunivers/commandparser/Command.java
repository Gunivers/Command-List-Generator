package net.gunivers.commandparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Stream;

import net.gunivers.commandparser.node.CommandNode;
import net.gunivers.commandparser.node.Node;
import net.gunivers.core.language.tuple.Tuple;
import net.gunivers.core.language.tuple.Tuple2;

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
		Tuple2<Integer, String> result = ((Command)commandTree).hasCorrectSyntax(command);
		StringBuilder msg = new StringBuilder();
		if(result._1 == -1)
			msg.append(result._2);
		else {
			msg.append("Une erreur a été détectée à la position " + result._1);
			msg.append('\n' + command + '\n');
			Stream.generate(() -> ' ').limit(result._1).forEach(msg::append);
			msg.append("^  ");
			msg.append(result._2);
		}
		return msg.toString();
	}

	public Tuple2<Integer, String> hasCorrectSyntax(String command) {
		String[] cmd = (command + " $").split(" ");
		Tuple2<Integer, String> value = browseAndCompare(cmd, this);
		return value;
	}

	private Tuple2<Integer, String> browseAndCompare(String[] cmd, CommandNode node) {
		int range = node.isSilent() ? 0 : 1;
		Tuple2<Integer, String> match = node.matches(cmd[0]);
		if (cmd.length == 1 && match._1 == 1)
			return Tuple.newTuple(-1, "Aucune erreur n'a été détectée.");
		else if (cmd.length == 1)
			return Tuple.newTuple(0, "Argument incorrecte.");
		else if (range > 0 && match._1 == 0)
			return match;
		else if (range > 0 && match._1 > 1)
			return match;
		ArrayList<Tuple2<Integer, String>> list = new ArrayList<Tuple2<Integer, String>>();
		for (Node child : node.getChildren()) {
			Tuple2<Integer, String> i = browseAndCompare(Arrays.copyOfRange(cmd, range, cmd.length), (CommandNode) child);
			list.add((i._1 == -1) ? i : (range > 0 ? Tuple.newTuple(i._1 + cmd[0].length() + 1, i._2) : i));
		}
		for(Tuple2<Integer, String> t : list)
			if(t._1 == -1)
				return t;
		
		return Collections.max(list, new Comparator<Tuple2<Integer, String>>() {
			@Override
			public int compare(Tuple2<Integer, String> a, Tuple2<Integer, String> b) {
				return Integer.compare(a._1, b._1);
			}
		});
	}
}
