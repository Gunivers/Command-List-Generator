package net.gunivers.listgenerator.util.node;

public class CommandNode extends Node {

	private String match;
	
	public CommandNode(String tag, CommandNode... children) {
		super(tag, children);
		match = tag;
	}
	
	public CommandNode(String tag, String match, CommandNode... children) {
		super(tag, children);
		this.match = match;
	}
	
	@Override
	public boolean matches(String value) {
		return value.matches(match);
	}

}
