package net.gunivers.commandparser.selector;

public class Selector
{

	private String selector;

	public Selector(String selector)
	{
		this.selector = selector;
	}

	public boolean matches()
	{
		selector = selector.substring(3, selector.length() - 2);

		StringBuilder key = new StringBuilder();
		StringBuilder value = new StringBuilder();

		boolean getKey = true;
		int levelOfCompound = 0;

		for (char letter : selector.toCharArray())
		{
			if (getKey)
			{
				if (letter == '=')
				{
					getKey = false;
					continue;
				}

				key.append(letter);
			} else
			{
				if (letter == ',' && levelOfCompound == 0)
				{
					getKey = true;
					if (!SelectorFields.valueOf(key.toString()).matches(value.toString())) return false;
					continue;
				}

				value.append(letter);

				switch (letter)
				{
					case '[':
					case '{':
					case '(':
						levelOfCompound++;
						break;

					case ')':
					case '}':
					case ']':
						levelOfCompound--;
				}

				if (levelOfCompound < 0) return false;
			}
		}

		return false;
	}
}
