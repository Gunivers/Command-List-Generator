package net.gunivers.commandparser.selector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.gunivers.commandparser.node.CommandNode;
import net.gunivers.core.language.Tuple;

public class SelectorNode extends CommandNode
{

	public SelectorNode(String name, CommandNode... children)
	{
		super(name, children);
	}

	@Override
	public Tuple<Integer, String> matches(String name)
	{
		if (getTag().length() == 2 && getTag().matches("@a|@e|@p|@r|@s"))
			return new Tuple<Integer, String>(1, null);

		if (getTag().matches("[0-9a-zA-Z_-]+"))
			return new Tuple<Integer, String>(1, null);

		if (!(getTag().length() > 3 || getTag().substring(0, 3).matches("(@a|@e|@p|@r|@s\\[)")))
			return new Tuple<Integer, String>(0, "Sélecteur invalide.");

		Pattern p = Pattern.compile(
				"[^\\[\\],{}]+(?:[^\\[\\],{}]+|(?=\\{)(?:(?=.*?\\{(?!.*?\\1)(.*\\}(?!.*\\2).*))(?=.*?\\}(?!.*?\\2)(.*)).)+?.*?(?=\\1)[^{]*(?=\\2$))");

		Matcher m = p.matcher(getTag().substring(2));
		ArrayList<String> values = new ArrayList<String>();

		while (m.find())
			values.add(m.group());

		return check(values.toArray(new String[values.size()]));
	}

	
	private Tuple<Integer, String> check(String[] name)
	{
		HashMap<String, Integer> reference = new HashMap<String, Integer>();
		int index = 3;

		for (int i = 0; i < name.length; i++)
		{
			String[] map = name[i].split("=");

			try
			{
				SelectorFields arg = SelectorFields.valueOf(map[0].toUpperCase());
				
				if (arg.matches(map[1]))
				{
					if (map[1].startsWith("!") && reference.containsKey('!' + map[0]) && arg.canBeRepeated() == 0)
						return new Tuple<Integer, String>(index, "La négation de ce paramètre n'est autorisée qu'une seule fois.");

					if (!map[1].startsWith("!") && reference.containsKey(map[0]) && (arg.canBeRepeated() >= 0 && arg.canBeRepeated() <= 1))
						return new Tuple<Integer, String>(index, "Ce paramètre n'est autorisé qu'une seule fois.");

					index += map[0].length() + 1;

					if (map[1].startsWith("!"))
						reference.put('!' + map[0], reference.containsKey('!' + map[0]) ? reference.get('!' + map[0]) + 1 : 1);

					else
						reference.put(map[0], reference.containsKey(map[0]) ? reference.get(map[0]) + 1 : 1);

					index += map[1].length() + 1;

				} else
					return new Tuple<Integer, String>(index, "La valeur ne correspond pas au format.");

			} catch (IllegalArgumentException e)
			{
				return new Tuple<Integer, String>(index, "Le paramètre n'existe pas.");
			}
		}

		return new Tuple<Integer, String>(-1, "ucune erreur n'a été détectée.");
	}
}
