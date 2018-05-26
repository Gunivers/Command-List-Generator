package net.gunivers.core.minecraft;

import java.util.ArrayList;

public enum Entity
{
	area_effect_cloud(EntityType.HAS_LIMITED_LIFE, EntityType.TERRESTRIAL, EntityType.SUMMONED_BY_PLAYER, EntityType.STAY_ON_BLOCK),
	armor_stand(EntityType.HEIGHT_2_BLOCK, EntityType.HAS_GRAVITY, EntityType.SUMMONED_BY_PLAYER, EntityType.STAY_ON_BLOCK),
	arrow(EntityType.PROJECTILE, EntityType.INFLICT_DAMAGE),
	bat(EntityType.CAN_FLY, EntityType.ANIMAL),
	blaze(EntityType.HEIGHT_2_BLOCK, EntityType.CAN_FLY, EntityType.SPAWN_IN_NETHER, EntityType.MONSTER, EntityType.SUMMON_OTHER),
	boat(EntityType.CAN_SWIM, EntityType.RIDEABLE),
	cave_spider(EntityType.SPIDER),
	chest_minecart(EntityType.HAS_INVENTORY, EntityType.MINECART),
	chicken(EntityType.CAN_WALK, EntityType.LITTLE_ANIMAL),
	cod(EntityType.FISH),
	commandblock_minecart(EntityType.MINECART),
	cow(EntityType.PROCREATE, EntityType.BIG_ANIMAL),
	creeper(EntityType.CAN_WALK, EntityType.SPAWN_IN_OVERWORLD, EntityType.MONSTER),
	donkey(EntityType.HAS_INVENTORY, EntityType.CAN_WALK, EntityType.SPAWN_IN_OVERWORLD),
	dragon_fireball(EntityType.SUMMON_OTHER, EntityType.PROJECTILE, EntityType.EXPLODE),
	egg(EntityType.SUMMON_OTHER, EntityType.SUMMONED_BY_PLAYER, EntityType.PROJECTILE),
	elder_guardian(EntityType.REALLY_BIG_HEIGHT, EntityType.REALLY_BIG_WIDTH, EntityType.CAN_SWIM, EntityType.MONSTER),
	ender_crystal(EntityType.STAY_ON_BLOCK, EntityType.SPAWN_IN_ENDER, EntityType.SUMMONED_BY_PLAYER, EntityType.EXPLODE),
	ender_dragon(EntityType.REALLY_BIG_HEIGHT, EntityType.REALLY_BIG_WIDTH, EntityType.CAN_INTERACT_ON_BLOCK, EntityType.SPAWN_IN_ENDER, EntityType.BOSS),
	ender_pearl(EntityType.SUMMON_OTHER, EntityType.PROJECTILE),
	enderman(EntityType.HEIGHT_3_BLOCK, EntityType.CAN_WALK, EntityType.CAN_INTERACT_ON_BLOCK, EntityType.SPAWN_IN_ENDER, EntityType.SPAWN_IN_OVERWORLD, EntityType.MONSTER, EntityType.SUMMON_OTHER),
	endermite(EntityType.CAN_WALK, EntityType.MONSTER, EntityType.SUMMONED_BY_OTHER),
	evocation_fangs(EntityType.HAS_LIMITED_LIFE, EntityType.INFLICT_DAMAGE, EntityType.TERRESTRIAL, EntityType.SUMMONED_BY_OTHER, EntityType.STAY_ON_BLOCK),
	evocation_illager(EntityType.CAN_WALK, EntityType.MONSTER, EntityType.SPAWN_IN_OVERWORLD, EntityType.SUMMON_OTHER, EntityType.ILLAGER),
	eye_of_ender_signal(EntityType.SUMMONED_BY_PLAYER, EntityType.PROJECTILE),
	falling_block(EntityType.SPAWN_IN_OVERWORLD, EntityType.SUMMONED_BY_PLAYER, EntityType.STAY_ON_BLOCK),
	fireball(EntityType.PROJECTILE, EntityType.EXPLODE),
	fireworks_rocket(EntityType.SUMMONED_BY_PLAYER, EntityType.PROJECTILE, EntityType.EXPLODE),
	furnace_minecart(EntityType.MINECART),
	ghast(EntityType.REALLY_BIG_HEIGHT, EntityType.REALLY_BIG_WIDTH, EntityType.CAN_FLY, EntityType.SPAWN_IN_NETHER, EntityType.MONSTER, EntityType.SUMMON_OTHER),
	giant(EntityType.REALLY_BIG_HEIGHT, EntityType.REALLY_BIG_WIDTH, EntityType.CAN_WALK, EntityType.MONSTER),
	guardian(EntityType.CAN_SWIM, EntityType.MONSTER),
	hopper_minecart(EntityType.HAS_INVENTORY, EntityType.MINECART),
	horse(EntityType.FRIENDLY, EntityType.CAN_RUN, EntityType.SPAWN_IN_OVERWORLD, EntityType.BIG_ANIMAL, EntityType.RIDEABLE),
	husk(EntityType.CAN_RESIST_FIRE, EntityType.ZOMBIE),
	illusion_illager(EntityType.SPAWN_IN_OVERWORLD, EntityType.CAN_WALK, EntityType.MONSTER, EntityType.ILLAGER),
	item(EntityType.HAS_LIMITED_LIFE, EntityType.HAS_GRAVITY, EntityType.SPAWN_IN_OVERWORLD, EntityType.SPAWN_IN_NETHER, EntityType.SUMMONED_BY_PLAYER, EntityType.STAY_ON_BLOCK),
	item_frame(EntityType.HAS_INVENTORY, EntityType.SUMMONED_BY_PLAYER, EntityType.STAY_ON_BLOCK),
	leash_knot(EntityType.SUMMONED_BY_PLAYER, EntityType.STAY_ON_BLOCK),
	lightning_bolt(EntityType.SPAWN_IN_OVERWORLD, EntityType.INFLICT_DAMAGE),
	llama(EntityType.CAN_WALK, EntityType.BIG_ANIMAL, EntityType.SUMMON_OTHER),
	llama_spit(EntityType.PROJECTILE),
	magma_cube(EntityType.CAN_WALK, EntityType.SPAWN_IN_NETHER, EntityType.MONSTER, EntityType.SUMMON_OTHER, EntityType.SUMMONED_BY_OTHER),
	minecart(EntityType.RIDEABLE, EntityType.MINECART),
	mooshroom(EntityType.CAN_RUN, EntityType.BIG_ANIMAL),
	mule(EntityType.HAS_INVENTORY, EntityType.CAN_WALK, EntityType.BIG_ANIMAL, EntityType.RIDEABLE),
	ocelot(EntityType.CAN_WALK, EntityType.LITTLE_ANIMAL),
	painting(EntityType.SUMMONED_BY_PLAYER, EntityType.STAY_ON_BLOCK),
	parrot(EntityType.FRIENDLY, EntityType.CAN_FLY, EntityType.SPAWN_IN_OVERWORLD, EntityType.LITTLE_ANIMAL),
	phantom(EntityType.CAN_FLY, EntityType.SPAWN_IN_OVERWORLD, EntityType.MONSTER),
	pig(EntityType.CAN_WALK, EntityType.LITTLE_ANIMAL, EntityType.RIDEABLE),
	polar_bear(EntityType.WIDTH_2_BLOCK, EntityType.AGRESSIVE, EntityType.CAN_WALK, EntityType.BIG_ANIMAL),
	potion(EntityType.SUMMONED_BY_PLAYER, EntityType.SUMMON_OTHER, EntityType.PROJECTILE),
	puffer_fish(EntityType.AGRESSIVE, EntityType.FISH),
	rabbit(EntityType.CAN_RUN, EntityType.LITTLE_ANIMAL),
	salmon(EntityType.FISH),
	sheep(EntityType.CAN_RUN, EntityType.LITTLE_ANIMAL),
	shulker(EntityType.SPAWN_IN_ENDER, EntityType.MONSTER, EntityType.SUMMON_OTHER, EntityType.STAY_ON_BLOCK),
	shulker_bullet(EntityType.FOLLOW_TARGET, EntityType.PROJECTILE),
	silverfish(EntityType.SPAWN_IN_OVERWORLD, EntityType.MONSTER, EntityType.SUMMON_OTHER, EntityType.SUMMONED_BY_OTHER, EntityType.STAY_ON_BLOCK),
	skeleton(EntityType.SPAWN_IN_OVERWORLD, EntityType.SKELETON),
	skeleton_horse(EntityType.SPAWN_IN_OVERWORLD, EntityType.RIDEABLE, EntityType.BIG_ANIMAL),
	slime(EntityType.SPAWN_IN_OVERWORLD, EntityType.MONSTER, EntityType.SUMMON_OTHER, EntityType.SUMMONED_BY_OTHER),
	small_fireball(EntityType.PROJECTILE, EntityType.EXPLODE),
	snowball(EntityType.PROJECTILE),
	snowman(EntityType.CAN_INTERACT_ON_BLOCK, EntityType.GOLEM),
	spawner_minecart(EntityType.SUMMON_OTHER, EntityType.MINECART),
	spectral_arrow(EntityType.PROJECTILE, EntityType.SUMMONED_BY_PLAYER),
	spider(EntityType.WIDTH_2_BLOCK, EntityType.SPIDER),
	squid(EntityType.FISH),
	stray(EntityType.ZOMBIE, EntityType.SUMMON_OTHER),
	tnt(EntityType.HAS_LIMITED_LIFE, EntityType.SUMMONED_BY_PLAYER, EntityType.EXPLODE, EntityType.STAY_ON_BLOCK),
	tnt_minecart(EntityType.MINECART, EntityType.EXPLODE),
	trident(EntityType.SUMMONED_BY_PLAYER, EntityType.INFLICT_DAMAGE,  EntityType.PROJECTILE),
	tropical_fish(EntityType.FISH),
	turtle(EntityType.CAN_INTERACT_ON_BLOCK, EntityType.FISH),
	vex(EntityType.CAN_FLY, EntityType.MONSTER, EntityType.SUMMONED_BY_OTHER),
	villager(EntityType.FRIENDLY, EntityType.ILLAGER),
	villager_golem(EntityType.HEIGHT_3_BLOCK, EntityType.WIDTH_2_BLOCK, EntityType.GOLEM),
	vindication_illager(EntityType.MONSTER, EntityType.ILLAGER),
	witch(EntityType.MONSTER, EntityType.SUMMON_OTHER, EntityType.ILLAGER),
	wither(EntityType.HEIGHT_3_BLOCK, EntityType.WIDTH_3_BLOCK, EntityType.BOSS),
	wither_skeleton,
	wither_skull,
	wolf,
	xp_bottle,
	xp_orb,
	zombie,
	zombie_horse,
	zombie_pigman,
	zombie_villager;

	private ArrayList<EntityType> cate = new ArrayList<EntityType>();
	
	/** <strong>Constuctor<strong>
	 * Create an Entity
	 * @param categories
	 *                  the categories of the entity
	 */
	private Entity(EntityType... categories)
	{
		for (EntityType cate : categories)
		{
			this.cate.addAll(cate.getCategories());
		}
		
		singleton();
	}
	
	/** <strong>Entity.<italic>filter<italic><strong>
	 * Filter the entities then return what is left
	 * @param categories
	 *                  an Array of the categories 
	 * @return an ArrayList of the entity that matches the categories
	 */
	public static ArrayList<Entity> filter(EntityType... categories)
	{
		ArrayList<Entity> filter = new ArrayList<>();
		
		for (Entity e : Entity.values())
		{
			for (EntityType cate : categories)
			{
				if (!e.cate.contains(cate))
					continue;
				
				filter.add(e);
			}
		}
		
		return filter;
	}
	
	/**
	 * This method will sort the categories in order to have only one of each
	 */
	private void singleton()
	{
		ArrayList<EntityType> temp = new ArrayList<>();
		
		for (EntityType category : cate)
		{
			if(!temp.contains(category )) temp.add(category );
		}
		
		cate = temp;
		
		if (!temp.contains(EntityType.HEIGHT_1_BLOCK) && !temp.contains(EntityType.HEIGHT_2_BLOCK) &&
				!temp.contains(EntityType.HEIGHT_3_BLOCK) && !temp.contains(EntityType.REALLY_BIG_HEIGHT))
			cate.add(EntityType.HEIGHT_1_BLOCK);
		
		if (!temp.contains(EntityType.WIDTH_1_BLOCK) && !temp.contains(EntityType.WIDTH_2_BLOCK) &&
				!temp.contains(EntityType.WIDTH_3_BLOCK) && !temp.contains(EntityType.REALLY_BIG_WIDTH))
			cate.add(EntityType.WIDTH_1_BLOCK);
		
		if (!temp.contains(EntityType.PASSIVE) && !temp.contains(EntityType.AGRESSIVE) && !temp.contains(EntityType.FRIENDLY))
			cate.add(EntityType.PASSIVE);
	}
}
