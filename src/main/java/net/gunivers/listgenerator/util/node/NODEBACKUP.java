package net.gunivers.listgenerator.util.node;

import java.util.ArrayList;

public class NODEBACKUP {
	
	protected ArrayList<NODEBACKUP> children = new ArrayList<NODEBACKUP>();
	protected String tag = "";
	
	protected NODEBACKUP father;
	
	public NODEBACKUP(String tag, NODEBACKUP ... children) {
		if (tag != null) this.tag = tag;
		this.father = null;
		
		for (NODEBACKUP child : children) {
			this.children.add(child);
			child.setFather(this);
		}
	}
	
	public void setChildren(NODEBACKUP ... children) {
		for (NODEBACKUP child : this.children) {
			child.setFather(null);
		}
		
		this.children.clear();
		
		for (NODEBACKUP child : children) {
			if (child != null) {
				child.setFather(this);
				this.children.add(child);
			}
		}
	}
	
	public ArrayList<NODEBACKUP> addChild(NODEBACKUP child) {
		if (child.hasFather()) {
			child.getFather().removeChild(child);
		}
		
		child.setFather(this);
		
		if (!this.children.contains(child)) this.children.add(child);
		
		return this.children;
	}
	
	public ArrayList<NODEBACKUP> removeChild(NODEBACKUP child) {
		
		if (this.children.contains(child)) {
			this.children.remove(this.children.indexOf(child));
			child.setFather(null);
		}
		
		return this.children;
	}
	
	public ArrayList<NODEBACKUP> getChildren() {
		return this.children;
	}
	
	public String getTag() {
		return this.tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
		if (tag == null) this.tag = "";
	}
	
	protected void setFather(NODEBACKUP father) {
		this.father = father;
	}
	
	public NODEBACKUP getFather() {
		return this.father;
	}
	
	public boolean hasFather() {
		return (this.father != null);
	}
	
	public boolean hasChildren() {
		return (!this.children.isEmpty());
	}
	
	public boolean hasChild(String tag) {
		for (NODEBACKUP child : children) {
			if (child.matches(tag)) return true;
		}
		
		return false;
	}
	
	public NODEBACKUP getChild(String tag) {
		for (NODEBACKUP child : children) {
			if (child.getTag().equals(tag)) return child;
		}

		return null;
	}
	
	public ArrayList<String> getTree() {
		ArrayList<String> tree = new ArrayList<String>();
		tree.add(this.tag);
		
		for (NODEBACKUP child : this.children) {
			for (String path : child.getTree()) {
				tree.add("|    " + path);
			}
		}
		
		return tree;
	}
	
	public boolean matches(String tag) {return this.tag == tag;}
}
