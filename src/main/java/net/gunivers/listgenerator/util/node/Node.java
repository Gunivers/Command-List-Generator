package net.gunivers.listgenerator.util.node;

import java.util.ArrayList;

public class Node {
	
	protected ArrayList<Node> children = new ArrayList<Node>();
	protected String tag = "";
	
	protected Node father;
	
	public Node(String tag, Node ... children) {
		if (tag != null) this.tag = tag;
		this.father = null;
		
		for (Node child : children) {
			this.children.add(child);
			child.setFather(this);
		}
	}
	
	public void setChildren(Node ... children) {
		for (Node child : this.children) {
			child.setFather(null);
		}
		
		this.children.clear();
		
		for (Node child : children) {
			if (child != null) {
				child.setFather(this);
				this.children.add(child);
			}
		}
	}
	
	public ArrayList<Node> addChild(Node child) {
		if (child.hasFather()) {
			child.getFather().removeChild(child);
		}
		
		child.setFather(this);
		
		if (!this.children.contains(child)) this.children.add(child);
		
		return this.children;
	}
	
	public ArrayList<Node> removeChild(Node child) {
		
		if (this.children.contains(child)) {
			this.children.remove(this.children.indexOf(child));
			child.setFather(null);
		}
		
		return this.children;
	}
	
	public ArrayList<Node> getChildren() {
		return this.children;
	}
	
	public String getTag() {
		return this.tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
		if (tag == null) this.tag = "";
	}
	
	protected void setFather(Node father) {
		this.father = father;
	}
	
	public Node getFather() {
		return this.father;
	}
	
	public boolean hasFather() {
		return (this.father != null);
	}
	
	public boolean hasChildren() {
		return (!this.children.isEmpty());
	}
	
	public boolean hasChild(String tag) {
		for (Node child : children) {
			if (child.matches(tag)) return true;
		}
		
		return false;
	}
	
	public Node getChild(String tag) {
		for (Node child : children) {
			if (child.getTag().equals(tag)) return child;
		}

		return null;
	}
	
	public ArrayList<String> getTree() {
		ArrayList<String> tree = new ArrayList<String>();
		tree.add(this.tag);
		
		for (Node child : this.children) {
			for (String path : child.getTree()) {
				tree.add("|    " + path);
			}
		}
		
		return tree;
	}
	
	public boolean matches(String tag) {return this.tag == tag;}
}
