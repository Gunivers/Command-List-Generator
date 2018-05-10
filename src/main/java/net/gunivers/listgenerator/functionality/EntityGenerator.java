package net.gunivers.listgenerator.functionality;

import net.gunivers.core.minecraft.Entity;
import net.gunivers.core.minecraft.EntityType;
import net.gunivers.listgenerator.util.Call;
import net.gunivers.listgenerator.util.Functionality;

import java.util.ArrayList;

/**
 * @author A~Z
 * Generate Entity values
 */
public class EntityGenerator extends Functionality
{
	EntityType et;

	/**
	 * <strong>Constructor</strong>
	 *
	 * @param et a EntityType
	 */
	public EntityGenerator(EntityType et)
	{
		this.et = et;
	}

	/**
	 * Generate Simple Entity
	 *
	 * @param et a EntityType
	 * @return ArrayList<String> generated Entity names
	 */
	@Call
	public static ArrayList<String> generate(EntityType et)
	{
		ArrayList<String> commands = new ArrayList<>();

		for (Entity entity : Entity.values())
		{
			if (EntityType.checkEntity(entity, et)) commands.add(entity.name());
		}

		return commands;
	}

	/**
	 * @param et a EntityType
	 * @return String name of et
	 */
	public static String toString(EntityType et)
	{
		return et.toString();
	}

	/**
	 * Generate simple Entity
	 *
	 * @return ArrayList<String> generated Entity names
	 */
	public ArrayList<String> generate()
	{
		ArrayList<String> commands = new ArrayList<>();

		for (Entity entity : Entity.values())
		{
			if (EntityType.checkEntity(entity, et)) commands.add(entity.name());
		}

		return commands;
	}

	@Override
	public String toString()
	{
		return "Entity";
	}

	@Override
	public ArrayList<Object> callParameterOverlay()
	{
		// TODO
		return null;
	}
}
