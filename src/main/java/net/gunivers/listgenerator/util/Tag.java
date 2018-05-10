package net.gunivers.listgenerator.util;

import net.gunivers.listgenerator.util.value.IValue;
import net.gunivers.listgenerator.util.value.ValueManager;

import java.util.HashMap;

/**
 * @author Oromis
 * This object represent a tag in the mold
 */
public class Tag
{

	public static HashMap<String, Tag> tags = new HashMap<>();

	private Functionality type;
	private String id;

	/**
	 * Constructor of Tag
	 *
	 * @param id the id of the tag
	 */
	public Tag(String id)
	{
		this.id = id;
	}

	/**
	 * @return the id of the tag
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @return the type of the tag
	 */
	public Functionality getType()
	{
		return type;
	}

	/**
	 * @param type the type of the tag
	 */
	public void setType(Functionality type)
	{
		this.type = type;
	}

	/**
	 * @return the parameters of the tag
	 */
	public IValue[] getParameters()
	{
		return ValueManager.getValues(this);
	}
}
