package net.gunivers.commandparser.node;

import net.gunivers.core.language.Tuple;

public class CommandNode extends Node
{

	private String match;
	private boolean silent;

	public CommandNode(String tag, CommandNode... children)
	{
		super(tag, children);
		match = tag;
		silent = false;
	}

	public CommandNode(String tag, String match, CommandNode... children)
	{
		super(tag, children);
		this.match = match;
		silent = false;
	}

	public CommandNode(String tag, String match, boolean silent, CommandNode... children)
	{
		super(tag, children);
		this.match = match;
		this.silent = silent;
	}

	public Tuple<Integer, String> matches(String value)
	{
		return value.matches(match) ? new Tuple<Integer, String>(1, null) : new Tuple<Integer, String>(0, "Invalid argument");
	}
	
	public void setMatch(String value) {
		match = value;
	}

	public boolean isSilent()
	{
		return silent;
	}

	public void setSilent(boolean silent)
	{
		this.silent = silent;
	}

	
}
