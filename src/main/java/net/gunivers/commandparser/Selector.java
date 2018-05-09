package net.gunivers.commandparser;

public class Selector extends Node {

	public Selector(String tag, Node ... children) {
		super(tag, children);
	}
	
	@Override
	public boolean matches(String selector) {
		selector = selector.substring(2, selector.length() - 2);
		
		String[] fields = selector.split(",");
		
		for (String field : fields) {
			SelectorFields value = Enum.valueOf(SelectorFields.class, field.split("=")[0]);
			if (value == null) return false;
			
			Class clazz = value.getParameter();
		}
		
		return false;
	}
}
