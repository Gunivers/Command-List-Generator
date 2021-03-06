package net.gunivers.core.minecraft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum EntityType {
	ALL,
	
	HEIGHT_1_BLOCK(ALL),
	HEIGHT_2_BLOCK(ALL),
	HEIGHT_3_BLOCK(ALL),
	REALLY_BIG_HEIGHT(ALL),
	
	WIDTH_1_BLOCK(ALL),
	WIDTH_2_BLOCK(ALL),
	WIDTH_3_BLOCK(ALL),
	REALLY_BIG_WIDTH(ALL),

	HAS_INVENTORY(ALL),
	HAS_SPAWNER(ALL),
	HAS_GRAVITY(ALL),
	HAS_AI(ALL),
	HAS_LIMITED_LIFE(ALL),

	INFLICT_DAMAGE(HAS_AI),
	
	PASSIVE(ALL),
	AGRESSIVE(INFLICT_DAMAGE),
	FRIENDLY(HAS_AI),
	
	TERRESTRIAL(HAS_GRAVITY),
	AERIAL(ALL),
	MARINE(HAS_GRAVITY),

	CAN_MOVE(HAS_AI),
	
	CAN_FLY(CAN_MOVE, AERIAL),
	CAN_WALK(HAS_GRAVITY, TERRESTRIAL, CAN_MOVE),
	CAN_SWIM(HAS_GRAVITY, MARINE, CAN_MOVE),
	CAN_RUN(CAN_WALK),
	
	CAN_INTERACT_ON_BLOCK(CAN_MOVE),
	CAN_RESIST_FIRE(CAN_MOVE),
	
	EXPLODE(INFLICT_DAMAGE, CAN_INTERACT_ON_BLOCK),
	FOLLOW_TARGET(CAN_MOVE),
	RUN_AWAY(CAN_MOVE),
	PROCREATE(CAN_RUN, RUN_AWAY),
	
	SPAWN_NATURALLY,
	SPAWN_IN_OVERWORLD(SPAWN_NATURALLY),
	SPAWN_IN_NETHER(SPAWN_NATURALLY, CAN_RESIST_FIRE),
	SPAWN_IN_ENDER(SPAWN_NATURALLY),

	OBJECT(PASSIVE),
	
	SUMMON_OTHER,
	SUMMONED_BY_OTHER,
	SUMMONED_BY_PLAYER(OBJECT, SUMMONED_BY_OTHER),
	
	MONSTER(HAS_SPAWNER, AGRESSIVE, FOLLOW_TARGET),
	HUMAN(HEIGHT_2_BLOCK, HAS_GRAVITY, SPAWN_IN_OVERWORLD, CAN_RUN),
	ANIMAL(HAS_SPAWNER, HAS_GRAVITY, SPAWN_IN_OVERWORLD, RUN_AWAY),
	BOSS(AGRESSIVE, FOLLOW_TARGET, CAN_FLY, CAN_INTERACT_ON_BLOCK, CAN_RESIST_FIRE, SUMMON_OTHER, SUMMONED_BY_OTHER),
	
	RIDEABLE(HAS_GRAVITY, CAN_MOVE),
	GOLEM(FRIENDLY, INFLICT_DAMAGE,  SUMMONED_BY_PLAYER),
	MINECART(CAN_WALK),
	
	PROJECTILE(HAS_LIMITED_LIFE, AERIAL, CAN_MOVE, OBJECT, SUMMONED_BY_OTHER),
	STAY_ON_BLOCK(OBJECT),
	
	LITTLE_ANIMAL(ANIMAL),
	BIG_ANIMAL(WIDTH_2_BLOCK, ANIMAL),
	FISH(CAN_SWIM, LITTLE_ANIMAL),

	ILLAGER(HUMAN),
	UNDEAD(HEIGHT_2_BLOCK, CAN_WALK, MONSTER),
	
	SKELETON(UNDEAD),
	ZOMBIE(UNDEAD),
	SPIDER(CAN_INTERACT_ON_BLOCK, CAN_WALK, SPAWN_IN_OVERWORLD, MONSTER);
	
	private ArrayList<EntityType> categories = new ArrayList<EntityType>();
	
	private EntityType(EntityType... categories)
	{
		constructor(Arrays.asList(categories));
		singleton();
	}
	
	public ArrayList<EntityType> getCategories()
	{
		return categories;
	}
	
	/**
	 * This method will add the whole genealogic tree of category in categories
	 * @param categories
	 *                    a List of the first categories
	 */
	private void constructor(List<EntityType> categories)
	{
		for (EntityType category : categories)
		{
			if (!this.categories.contains(category)) this.categories.add(category);
		}
		
		for (EntityType category : categories)
		{
			constructor(category.getCategories());	
		}
	}
	
	/**
	 * This method will sort the categories in order to have only one of each
	 */
	private void singleton()
	{
		ArrayList<EntityType> temp = new ArrayList<>();
		
		for (EntityType category : categories)
		{
			if(!temp.contains(category )) temp.add(category );
		}
		
		categories = temp;
	}
}