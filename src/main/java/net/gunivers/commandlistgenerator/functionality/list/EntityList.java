package net.gunivers.commandlistgenerator.functionality.list;

import java.util.ArrayList;

import net.gunivers.commandlistgenerator.util.BooleanFunctionalInterface;
import net.gunivers.core.minecraft.Entity;
import net.gunivers.core.utils.tuple.Tuple;

public class EntityList extends List {
	
	public EntityList() {
		super("gui.list.item.entity");
		addEntityList((Object e) -> ((Entity) e) instanceof Entity, "gui.list.item.all");
		addEntityList((Object e) -> ((Entity) e).isAnimal(), "gui.list.item.entity.animal");
		addEntityList((Object e) -> ((Entity) e).isMonster(), "gui.list.item.entity.monster");
		addEntityList((Object e) -> ((Entity) e).isEnderEntity(), "gui.list.item.entity.enderentity");
		addEntityList((Object e) -> ((Entity) e).isNetherEntity(), "gui.list.item.entity.netherentity");
		addEntityList((Object e) -> ((Entity) e).isOverworldEntity(), "gui.list.item.entity.overworldentity");
		addEntityList((Object e) -> ((Entity) e).canFly(), "gui.list.item.entity.flyingentity");
		addEntityList((Object e) -> ((Entity) e).hasLife(), "gui.list.item.entity.livingentity");
		addEntityList((Object e) -> ((Entity) e).hasNoLife(), "gui.list.item.entity.inanimateentity");
		addEntityList((Object e) -> ((Entity) e).hasNaturalSpawn(), "gui.list.item.entity.naturalspawnentity");
		addEntityList((Object e) -> ((Entity) e).isMinecart(), "gui.list.item.entity.minecart");
		addEntityList((Object e) -> ((Entity) e).isVillager(), "gui.list.item.entity.villager");
	}
	
	public void addEntityList(BooleanFunctionalInterface b, String name) {
		ArrayList<String> entities = new ArrayList<String>();
		for(Entity entity : Entity.values())
			if(b.invoke(entity))
				entities.add(entity.toString());
		addSubList(Tuple.newTuple(name, entities.toArray(new String[entities.size()])));
	}

}
