package net.gunivers.commandlistgenerator.functionality.list;

import java.util.ArrayList;

import net.gunivers.commandlistgenerator.CommandListGenerator;
import net.gunivers.core.language.Language;
import net.gunivers.core.utils.tuple.Tuple2;

public abstract class List implements java.io.Serializable
{
	private static final long serialVersionUID = 3L;
	
	public static ArrayList<List> lists = new ArrayList<List>();
	private ArrayList<String> content = new ArrayList<String>();
	private ArrayList<Tuple2<String, String[]>> subLists = new ArrayList<Tuple2<String, String[]>>();
	private String tag;
	protected static Language language = CommandListGenerator.LANGUAGE;
	
	public static List getList(String tag)
	{
		for(List list : lists)
			if(list.getTag().equals(tag))
				return list;
		
		return null;
	}
	
	public List(String tag)
	{
		this.tag = tag;
		lists.add(this);
	}
	
	public String getTag()
	{
		return language.get(tag);
	}
	
	public ArrayList<Tuple2<String, String[]>> getSubLists()
	{
		return (subLists.isEmpty()) ? null : subLists;
	}
	
	public Tuple2<String, String[]> getSubList(String name)
	{
		for(Tuple2<String, String[]> t : subLists)
			if(language.get(t._1).equals(name))
				return t;
		
		return null;
	}
	
	public void addSubList(Tuple2<String, String[]> sublist)
	{
		subLists.add(sublist);
	}
	
	public void addContent(String content)
	{
		this.content.add(content);
	}

	public void setContent(ArrayList<String> content)
	{
		this.content = content;
	}
		
	public ArrayList<String> getContent()
	{
		return content;
	}
	
	public static void refreshText() {
		language = CommandListGenerator.LANGUAGE;
	}
	
}
