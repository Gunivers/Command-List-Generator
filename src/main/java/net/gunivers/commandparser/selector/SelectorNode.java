package net.gunivers.commandparser.selector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.gunivers.commandparser.node.CommandNode;

public class SelectorNode extends CommandNode {


	public SelectorNode(String selector) {
		super(selector);
	}

	@Override
	public int matches(String value) {
		if(value.length() == 2 && value.matches("@a|@e|@p|@r|@s"))
			return 1;
		else if(value.matches("[0-9a-zA-Z_-]+"))
			return 1;
		else if(!(value.length() > 3 && value.substring(0, 2).matches("(@a|@e|@p|@r|@s)\\[")))
			return 0;
		else {
			Pattern p = Pattern.compile("[^\\[\\],{}]+=(?:[^\\[\\],{}]+|(?=\\{)(?:(?=.*?\\{(?!.*?\\1)(.*\\}(?!.*\\2).*))(?=.*?\\}(?!.*?\\2)(.*)).)+?.*?(?=\\1)[^{]*(?=\\2$))");
			Matcher m = p.matcher(value);
			ArrayList<String> values = new ArrayList<String>();
			while(m.find())
				values.add(m.group());
			return check(values.toArray(new String[values.size()]));
		}
	}
	
	private int check(String[] value) {
		HashMap<String, Integer> reference = new HashMap<String, Integer>();
		int index = 3;
		for(int i = 0; i < value.length; i++) {
			String[] map = value[i].split("=");
			try {
				SelectorFields arg = SelectorFields.valueOf(map[0]);
				index += map[i].length() + 1;
				arg.matches(value[i]);
			} catch(IllegalArgumentException e) {
				return index;
			}
		}
	}
}
