package net.gunivers.core.minecraft;

public enum Entity {
	area_effect_cloud,
	armor_stand,
	arrow,
	bat,
	blaze,
	boat,
	cave_spider,
	chest_minecart,
	chicken,
	cod_mob,
	commandblock_minecart,
	cow,
	creeper,
	donkey,
	dragon_fireball,
	egg,
	elder_guardian,
	ender_crystal,
	ender_dragon,
	ender_pearl,
	enderman,
	endermite,
	evocation_fangs,
	evocation_illager,
	eye_of_ender_signal,
	falling_block,
	fireball,
	fireworks_rocket,
	furnace_minecart,
	ghast,
	giant,
	guardian,
	hopper_minecart,
	horse,
	husk,
	illusion_illager,
	item,
	item_frame,
	leash_knot,
	lightning_bolt,
	llama,
	llama_spit,
	magma_cube,
	minecart,
	mooshroom,
	mule,
	ocelot,
	painting,
	parrot,
	phantom,
	pig,
	polar_bear,
	potion,
	puffer_fish,
	rabbit,
	salmon_mob,
	sheep,
	shulker,
	shulker_bullet,
	silverfish,
	skeleton,
	skeleton_horse,
	slime,
	small_fireball,
	snowball,
	snowman,
	spawner_minecart,
	spectral_arrow,
	spider,
	squid,
	stray,
	tnt,
	tnt_minecart,
	trident,
	tropical_fish,
	turtle,
	vex,
	villager,
	villager_golem,
	vindiction_illager,
	witch,
	wither,
	wither_skeleton,
	wither_skull,
	wolf,
	xp_bottle,
	xp_orb,
	zombie,
	zombie_horse,
	zombie_pigman,
	zombie_villager;
	
	public boolean isMonster() {
		switch(this) {
			case blaze:
			case cave_spider:
			case creeper:
			case elder_guardian:
			case ender_dragon:
			case enderman:
			case endermite:
			case evocation_illager:
			case ghast:
			case giant:
			case guardian:
			case husk:
			case illusion_illager:
			case magma_cube:
			case phantom:
			case shulker:
			case silverfish:
			case skeleton:
			case slime:
			case snowman:
			case spider:
			case stray:
			case witch:
			case wither:
			case wither_skeleton:
			case zombie:
			case zombie_pigman:
			case zombie_villager: return true;
			default: return false;
		}
	}
}
