package net.gunivers.commandparser.selector;

import net.gunivers.commandparser.Node;

public class Selector extends Node {

	public Selector(String tag, Node ... children) {
		super(tag, children);
	}
	
	/*@Override
	public boolean matches(String selector) {
		selector = selector.substring(2, selector.length() - 2);
		
		String[] fields = selector.split(",");
		
		for (String field : fields) {
			SelectorFields value = SelectorFields.valueOf(field.split("=")[0]);
			if (value == null) return false;
			
			Class clazz = value.getParameter();
		}
		
		return false;
	}*/
}
