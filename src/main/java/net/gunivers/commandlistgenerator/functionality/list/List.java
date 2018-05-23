package net.gunivers.commandlistgenerator.functionality.list;

import java.util.ArrayList;

import net.gunivers.commandlistgenerator.CommandListGenerator;
import net.gunivers.core.language.Language;
import net.gunivers.core.utils.tuple.Tuple2;

public abstract class List {

	public static ArrayList<List> lists = new ArrayList<List>();
	private ArrayList<String> content = new ArrayList<String>();
	private ArrayList<Tuple2<String, String[]>> subLists = new ArrayList<Tuple2<String, String[]>>();
	private String tag;
	protected Language l = CommandListGenerator.language;
	
	public static List getList(String s) {
		for(List l : lists)
			if(l.getTag().equals(s))
				return l;
		return null;
	}
	
	public List(String name) {
		tag = name;
		lists.add(this);
	}
	
	public String getTag() {
		return l.get(tag);
	}
	
	public ArrayList<Tuple2<String, String[]>> getSubLists() {
		return (subLists.size() > 0) ? subLists : null;
	}
	
	public Tuple2<String, String[]> getSubList(String s) {
		for(Tuple2<String, String[]> t : subLists)
			if(l.get(t._1).equals(s))
				return t;
		return null;
	}
	
	public void addSubList(Tuple2<String, String[]> sublist) {
		subLists.add(sublist);
	}
	
	public void addContent(String string) {
		content.add(string);
	}

	public void setContent(ArrayList<String> content) {
		this.content = content;
	}
		
	public ArrayList<String> getContent() {
		return content;
	}
	
}
