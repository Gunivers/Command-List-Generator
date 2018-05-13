package net.gunivers.commandparser.node;

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

	@Override
	public int matches(String value)
	{
		return value.matches(match) ? 1 : 0;
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
