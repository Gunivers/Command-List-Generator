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
	vindication_illager,
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
	
	public boolean hasSpawnEnder() {
		switch(this) {
			case area_effect_cloud:
			case dragon_fireball:
			case ender_crystal:
			case ender_dragon:
			case enderman:
			case endermite:
			case shulker:
			case shulker_bullet: return true;
			
			default: return false;
		}
 	}
	
	public boolean hasSpawnOverworld() {
		switch(this) {
			case arrow:
			case bat:
			case cave_spider:
			case chicken:
			case cod_mob:
			case cow:
			case creeper:
			case donkey:
			case elder_guardian:
			case enderman:
			case endermite:
			case evocation_fangs:
			case evocation_illager:
			case guardian:
			case horse:
			case illusion_illager:
			case lightning_bolt:
			case llama:
			case llama_spit:
			case mooshroom:
			case mule:
			case ocelot:
			case parrot:
			case phantom:
			case pig:
			case polar_bear:
			case puffer_fish:
			case rabbit:
			case salmon_mob:
			case sheep:
			case silverfish:
			case skeleton:
			case skeleton_horse:
			case slime:
			case spider:
			case squid:
			case stray:
			case tropical_fish:
			case turtle:
			case vex:
			case villager:
			case villager_golem:
			case vindication_illager:
			case witch:
			case wolf:
			case xp_orb:
			case zombie:
			case zombie_villager: return true;
			
			default: return false;
		}
	}
	
	public boolean hasSpawnNether() {
		switch(this) {
			case blaze:
			case fireball:
			case ghast:
			case magma_cube:
			case wither:
			case wither_skeleton:
			case wither_skull:
			case xp_orb:
			case zombie_pigman: return true;
			
			default: return false;
		}
	}
	
	public boolean isMinecart() {
		switch(this) {
			case chest_minecart:
			case commandblock_minecart:
			case furnace_minecart:
			case hopper_minecart:
			case minecart:
			case spawner_minecart:
			case tnt_minecart: return true;
			
			default: return false;
		}
	}
	
 	public boolean hasNaturalSpawn() {
		switch(this) {
			case bat:
			case blaze:
			case cave_spider:
			case chicken:
			case cod_mob:
			case cow:
			case creeper:
			case donkey:
			case elder_guardian:
			case ender_dragon:
			case enderman:
			case endermite:
			case evocation_illager:
			case ghast:
			case guardian:
			case horse:
			case husk:
			case illusion_illager:
			case lightning_bolt:
			case llama:
			case magma_cube:
			case mooshroom:
			case mule:
			case ocelot:
			case parrot:
			case phantom:
			case pig:
			case polar_bear:
			case rabbit:
			case salmon_mob:
			case sheep:
			case shulker:
			case skeleton:
			case slime:
			case snowman:
			case spider:
			case squid:
			case stray:
			case tropical_fish:
			case turtle:
			case villager:
			case villager_golem:
			case vindication_illager:
			case witch:
			case wither_skeleton:
			case wolf:
			case zombie:
			case zombie_pigman: return true;
					
			default: return false;
		}
	}
	
	public boolean hasNoLife() {
		switch(this) {
			case area_effect_cloud:
			case armor_stand:
			case arrow:
			case boat:
			case dragon_fireball:
			case egg:
			case ender_crystal:
			case ender_pearl:
			case evocation_fangs:
			case falling_block:
			case fireball:
			case fireworks_rocket:
			case item:
			case item_frame:
			case leash_knot:
			case lightning_bolt:
			case llama_spit:
			case painting:
			case potion:
			case shulker_bullet:
			case snowball:
			case spectral_arrow:
			case trident:
			case wither_skull:
			case xp_bottle:
			case xp_orb: return true;
			
			default: return false;
		}
	}
	
	public boolean canFly() {
		switch(this) {
			case bat:
			case blaze:
			case eye_of_ender_signal:
			case fireworks_rocket:
			case ghast:
			case shulker_bullet:
			case wither:
			case xp_orb: return true;
			
			default: return false;
		}
	}
	
	public boolean isVillager() {
		switch(this) {
			case evocation_illager:
			case illusion_illager:
			case villager:
			case vindication_illager:
			case witch:
			case zombie_villager: return true;
			
			default: return false;
		}
	}
	
	public boolean isAnimal() {
		switch(this) {
			case bat:
			case chicken:
			case cod_mob:
			case cow:
			case donkey:
			case horse:
			case llama:
			case mooshroom:
			case mule:
			case ocelot:
			case parrot:
			case pig:
			case polar_bear:
			case rabbit:
			case salmon_mob:
			case sheep:
			case squid:
			case tropical_fish:
			case turtle:
			case villager_golem: return true;
					
			default: return false;
		}
	}
	
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
			case vex:
			case vindication_illager:
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
