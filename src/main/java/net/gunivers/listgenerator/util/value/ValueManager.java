package net.gunivers.listgenerator.util.value;

import net.gunivers.listgenerator.util.Tag;

import java.util.HashMap;

@SuppressWarnings("rawtypes")
public class ValueManager
{
	private static HashMap<Tag, IValue[]> tagValues = new HashMap<Tag, IValue[]>();

	/**
	 * For registering a value for a tag
	 *
	 * @param tag
	 * @param objects
	 */
	public static void register(Tag tag, Object... objects)
	{
		if (objects != null)
		{
			IValue[] value = new IValue[objects.length];

			for (int i = 0; i < objects.length; i++)
			{
				if (objects[i] instanceof String)
				{
					value[i] = new StringValue((String) objects[i]);
				} else if (objects[i] instanceof Double)
				{
					value[i] = new DoubleValue((Double) objects[i]);
				} else if (objects[i] instanceof Integer)
				{
					value[i] = new IntValue((Integer) objects[i]);
				} else if (objects[i] instanceof Boolean)
				{
					value[i] = new BooleanValue((Boolean) objects[i]);
				} else
				{
					System.out.println("Unregisterable value type at range "+ i +": "+ objects[i].getClass().getName() +" # "+ objects[i]);
				}
			}

			if (tagValues.get(tag) != null)
				tagValues.remove(tag);

			tagValues.put(tag, value);
		} else
		{
			System.out.println("Trying to register null values table");
		}
		
	}

	/**
	 * get all parameters of a tag
	 *
	 * @param key
	 * @return
	 */
	public static IValue[] getValues(String key) {
		return getValues(Tag.tags.get(key));
	}

	/**
	 * get all parameters of a tag
	 *
	 * @param key
	 * @return
	 */
	public static IValue[] getValues(Tag key) {
		if (tagValues.get(key) != null) {
			return tagValues.get(key);
		}

		return null;
	}

	/**
	 * remove all data for a tag
	 *
	 * @param tag
	 */
	public static void removeValues(String tag) {
		removeValues(Tag.tags.get(tag));
	}

	/**
	 * remove all data for a tag
	 *
	 * @param tag
	 */
	public static void removeValues(Tag tag) {
		if (tagValues.get(tag) != null)
			tagValues.remove(tag);
	}
}
