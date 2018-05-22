package net.gunivers.commandparser.node;

import net.gunivers.core.utils.tuple.Tuple;
import net.gunivers.core.utils.tuple.Tuple2;

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

	public Tuple2<Integer, String> matches(String value)
	{
		return value.matches(match) ? Tuple.newTuple(1, null) : Tuple.newTuple(0, "Argument invalide.");
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
