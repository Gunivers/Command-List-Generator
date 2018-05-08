package net.gunivers.listgenerator.util.node;

import java.util.ArrayList;
import java.util.Arrays;

public class Node {

	protected ArrayList<Node> children = new ArrayList<Node>();
	protected String tag = "";

	public Node(String tag, Node... children) {
		this.tag = tag;
		setChildren(children);
	}

	public void setChildren(Node... children) {
		this.children.addAll(Arrays.asList(children));
	}

	public boolean addChild(Node child) {
		return children.add(child);
	}

	public boolean removeChild(Node child) {
		return children.remove(child);
	}

	public ArrayList<Node> getChildren() {
		return children;
	}

	public String getTag() {
		return tag;
	}

	public boolean matches(String tag) {
		return this.tag == tag;
	}

}
