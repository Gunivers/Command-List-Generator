package net.gunivers.listgenerator.util.value;

import net.gunivers.listgenerator.util.Tag;

import java.util.HashMap;

public class ValueManager
{
    private static HashMap<Tag, IValue[]> tagValues = new HashMap<>();

    public static void register(Tag tag, Object... objects)
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
                System.out.println("Cannot recognised value: " + objects[i]);
            }
        }

        if (tagValues.get(tag) != null)
            tagValues.remove(tag);

        tagValues.put(tag, value);
    }

    public static IValue[] getValues(String key)
    {
        return getValues(Tag.tags.get(key));
    }

    public static IValue[] getValues(Tag key)
    {
        if (tagValues.get(key) != null)
        {
            return tagValues.get(key);
        }

        return null;
    }
}
