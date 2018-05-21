package net.gunivers.core.minecraft;

import net.gunivers.commandlistgenerator.util.BooleanFunctionalInterface;

/**
 * @author A~Z
 * Represent the different properties of a block
 */
public enum EntityType
{

	FLY((Object e) -> ((Entity) e).canFly()),
	LIVE((Object e) -> ((Entity) e).hasLife()),
	HAS_NATURAL_SPAWN((Object e) -> ((Entity) e).hasNaturalSpawn()),
	
	SPAWN_ENDER((Object e) -> ((Entity) e).hasSpawnEnder()),
	SPAWN_NETHER((Object e) -> ((Entity) e).hasSpawnNether()),
	SPAWN_OVERWORLD((Object e) -> ((Entity) e).hasSpawnOverworld()),

	ANIMAL((Object e) -> ((Entity) e).isAnimal()),
	MINECART((Object e) -> ((Entity) e).isMinecart()),
	MONSTER((Object e) -> ((Entity) e).isMonster()),
	VILLAGER((Object e) -> ((Entity) e).isVillager());

	BooleanFunctionalInterface method;

	/**
	 * @param method a string corresponding at a name of a method
	 */
	EntityType(BooleanFunctionalInterface bool)
	{
		this.method = bool;
	}
	
	public BooleanFunctionalInterface getMethod()
	{
		return method;
	}
}
