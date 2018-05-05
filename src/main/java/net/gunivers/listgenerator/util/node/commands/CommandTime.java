package net.gunivers.listgenerator.util.node.commands;

import net.gunivers.listgenerator.util.node.CommandNode;
import net.gunivers.listgenerator.util.node.Node;

class CommandTime extends CommandNode {

	public CommandTime() {
		super("time");
		
		Node add = new Node("add",		new Node("[0-9]"));
		Node query = new Node("query",	new Node("daytime"), new Node("gametime"));
		Node set = new Node("set",		new Node("day"),	 new Node("night"),		new Node("[0-9]"));
		
		this.setChildren(add, query, set);
	}
}