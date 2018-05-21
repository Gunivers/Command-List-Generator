package net.gunivers.commandparser.selector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.gunivers.commandparser.node.CommandNode;
import net.gunivers.core.language.tuple.Tuple;
import net.gunivers.core.language.tuple.Tuple2;

public class SelectorNode extends CommandNode {

	public SelectorNode(String name, CommandNode... children) {
		super(name, children);
	}

	@Override
	public Tuple2<Integer, String> matches(String value) {
		if (value.length() < 2)
			return Tuple.newTuple(0, "Sélecteur invalide");
		
		if (!value.substring(0, 2).matches("@a|@e|@p|@r|@s"))
			return Tuple.newTuple(0, "Sélecteur invalide");
		
		if (value.matches("@a|@e|@p|@r|@s$"))
			return Tuple.newTuple(1, null);
		
		if (value.matches("[0-9a-zA-Z_-]+"))
			return Tuple.newTuple(1, null);
		
		else if (!(value.length() > 3 || value.substring(0, 3).matches("(@a|@e|@p|@r|@s\\[)")))
			return Tuple.newTuple(0, "Sélecteur invalide.");
		
		else {
			Pattern p = Pattern.compile(
					"[^\\[\\],{}]+(?:[^\\[\\],{}]+|(?=\\{)(?:(?=.*?\\{(?!.*?\\1)(.*\\}(?!.*\\2).*))(?=.*?\\}(?!.*?\\2)(.*)).)+?.*?(?=\\1)[^{]*(?=\\2$))");
			Matcher m = p.matcher(value.substring(2));
			ArrayList<String> values = new ArrayList<String>();
			while (m.find())
				values.add(m.group());
			return check(values.toArray(new String[values.size()]));
		}
	}

	private Tuple2<Integer, String> check(String[] value) {
		HashMap<String, Integer> reference = new HashMap<String, Integer>();
		int index = 3;
		for (int i = 0; i < value.length; i++) {
			String[] map = value[i].split("=");
			try {
				SelectorFields arg = SelectorFields.valueOf(map[0].toUpperCase());
				if (arg.matches(map[1])) {
					if (map[1].startsWith("!") && reference.containsKey('!' + map[0]) && arg.canBeRepeated() == 0)
						return Tuple.newTuple(index, "La négation de ce paramètre n'est autorisée qu'une seule fois.");
					if(!map[1].startsWith("!") && reference.containsKey(map[0]) && (arg.canBeRepeated() >= 0 && arg.canBeRepeated() <= 1))
						return Tuple.newTuple(index, "Ce paramètre n'est autorisé qu'une seule fois.");
					index += map[0].length() + 1;
					if(map[1].startsWith("!"))
						reference.put('!' + map[0], reference.containsKey('!' + map[0]) ? reference.get('!' + map[0]) + 1 : 1);
					else
						reference.put(map[0], reference.containsKey(map[0]) ? reference.get(map[0]) + 1 : 1);
					index += map[1].length() + 1;
				} else
					return Tuple.newTuple(index, "La valeur ne correspond pas au format.");
			} catch (IllegalArgumentException e) {
				return Tuple.newTuple(index, "Le paramètre n'existe pas.");
			}
		}
		return Tuple.newTuple(-1, "Aucune erreur n'a été détectée.");
	}
}
