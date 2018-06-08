package net.gunivers.commandlistgenerator.functionality.list;

import net.gunivers.core.minecraft.Entity;
import net.gunivers.core.minecraft.EntityType;
import net.gunivers.core.utils.tuple.Tuple;

public class EntityList extends List
{
	
	public EntityList()
	{
		super("gui.list.item.entity");

		for (EntityType et : EntityType.values())
		{
			addSubList(Tuple.newTuple(et.toString().toLowerCase(), Entity.filter(et).toArray(new String[Entity.filter(et).size()])));
		}
	}
}
