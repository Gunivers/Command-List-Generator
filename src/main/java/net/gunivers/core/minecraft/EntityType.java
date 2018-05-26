package net.gunivers.core.minecraft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum EntityType {
	HEIGHT_1_BLOCK,
	HEIGHT_2_BLOCK,
	HEIGHT_3_BLOCK,
	
	WIDTH_1_BLOCK,
	WIDTH_2_BLOCK,
	WIDTH_3_BLOCK,

	HAS_INVENTORY,
	HAS_SPAWNER,
	HAS_GRAVITY,
	HAS_AI,
	
	TERRESTRIAL,
	AERIAL,
	MARINE,

	PASSIVE,
	AGRESSIVE(HAS_AI),
	FRIENDLY(HAS_AI),

	CAN_MOVE(HAS_AI),
	
	CAN_FLY(CAN_MOVE),
	CAN_WALK(HAS_GRAVITY, TERRESTRIAL, CAN_MOVE),
	CAN_SWIM(HAS_GRAVITY, MARINE, CAN_MOVE),
	CAN_RUN(CAN_WALK),
	
	CAN_INTERACT_ON_BLOCK(CAN_MOVE),
	CAN_RESIST_FIRE(CAN_MOVE),
	
	EXPLODE,
	FOLLOW_TARGET(CAN_MOVE),
	RUN_AWAY(CAN_MOVE),
	PROCREATE(CAN_RUN, RUN_AWAY),
	
	SPAWN_NATURALLY,
	SPAWN_IN_OVERWORLD(SPAWN_NATURALLY),
	SPAWN_IN_NETHER(SPAWN_NATURALLY, CAN_RESIST_FIRE),
	SPAWN_IN_ENDER(SPAWN_NATURALLY),

	OBJECT(PASSIVE),
	MONSTER(AGRESSIVE, FOLLOW_TARGET),
	HUMAN(HEIGHT_2_BLOCK, WIDTH_1_BLOCK, HAS_GRAVITY, SPAWN_IN_OVERWORLD, CAN_RUN),
	ANIMAL(HEIGHT_1_BLOCK, HAS_GRAVITY, PASSIVE, SPAWN_IN_OVERWORLD, RUN_AWAY),
	
	SUMMON_OTHER,
	SUMMONED_BY_OTHER,
	SUMMONED_BY_PLAYER(OBJECT, SUMMONED_BY_OTHER),
	
	RIDEABLE(HAS_GRAVITY, CAN_MOVE),
	
	MINECART(HEIGHT_1_BLOCK, WIDTH_1_BLOCK, RIDEABLE, TERRESTRIAL),
	
	PROJECTILE(OBJECT, CAN_FLY),
	STAY_ON_BLOCK(OBJECT),

	BOSS(CAN_FLY, CAN_RESIST_FIRE, MONSTER),
	ILLAGER(HUMAN),
	UNDEAD(HEIGHT_2_BLOCK, WIDTH_1_BLOCK, CAN_WALK, MONSTER),
	
	SKELETON(UNDEAD),
	ZOMBIE(UNDEAD, SPAWN_IN_OVERWORLD),
	SPIDER(HEIGHT_1_BLOCK, WIDTH_2_BLOCK, SPAWN_IN_OVERWORLD, MONSTER);
	
	private ArrayList<EntityType> implications = new ArrayList<EntityType>();
	
	private EntityType(EntityType... implications)
	{
		constructor(Arrays.asList(implications));
		singleton();
	}
	
	public ArrayList<EntityType> getImplications()
	{
		return implications;
	}
	
	/**
	 * This method will add the whole genealogic tree of implication in implications
	 * @param implications
	 *                    a List of the first implications
	 */
	private void constructor(List<EntityType> implications)
	{
		this.implications.addAll(implications);
		
		for (EntityType implication : implications)
		{
			constructor(implication.getImplications());
		}
	}
	
	/**
	 * This method will sort the implications in order to have only one of each
	 */
	private void singleton()
	{
		ArrayList<EntityType> temp = new ArrayList<>();
		
		for (EntityType implication : implications)
		{
			if(!temp.contains(implication)) temp.add(implication);
		}
		
		implications = temp;
	}
}
