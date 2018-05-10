package net.gunivers.core.minecraft;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author A~Z
 * Represent the different properties of a block
 */
public enum EntityType
{

	FLY("canFly"),

	LIVE("hasLife"),
	HAS_NATURAL_SPAWN("hasNaturalSpawn"),
	SPAWN_ENDER("hasSpawnEnder"),
	SPAWN_NETHER("hasSpawnNether"),
	SPAWN_OVERWORLD("hasSpawnOerworld"),

	ANIMAL("isAnimal"),
	MINECART("isMinecart"),
	MONSTER("isMonster"),
	VILLAGER("isVillager");

	StringBuilder method = new StringBuilder();

	/**
	 * @param method a string corresponding at a name of a method
	 */
	EntityType(String method)
	{
		this.method.append(method);
	}

	/**
	 * @param entity a Entity
	 * @param et     a EntityType
	 * @return the result of the method having for name et of entity
	 */
	public static boolean checkEntity(Entity entity, EntityType et)
	{
		try
		{
			Method m = Entity.class.getMethod(et.method.toString());
			return (boolean) m.invoke(entity, new Object[]{});

		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
