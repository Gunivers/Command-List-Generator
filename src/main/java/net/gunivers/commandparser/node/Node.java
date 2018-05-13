package net.gunivers.commandparser.node;

import java.util.ArrayList;
import java.util.Arrays;

public class Node
{

	protected ArrayList<Node> children = new ArrayList<Node>();
	protected String tag = "";

	public Node(String tag, Node... children)
	{
		this.tag = tag;
		setChildren(children);
	}

	public void setChildren(Node... children)
	{
		this.children.addAll(Arrays.asList(children));
	}

	public boolean addChild(Node child)
	{
		return children.add(child);
	}

	public Node getChild(String tag)
	{
		for (Node child : children)
		{
			if (child.getTag().equals(tag)) return child;
		}
		return null;
	}

	public boolean removeChild(Node child)
	{
		return children.remove(child);
	}

	public ArrayList<Node> getChildren()
	{
		return children;
	}

	public String getTag()
	{
		return tag;
	}
	
	public int matches(String tag)
	{
		return this.tag == tag ? 1 : 0;
	}

}
