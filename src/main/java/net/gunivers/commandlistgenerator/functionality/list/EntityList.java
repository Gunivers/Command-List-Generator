package net.gunivers.commandlistgenerator.functionality.list;

import java.util.ArrayList;

import net.gunivers.commandlistgenerator.util.BooleanFunctionalInterface;
import net.gunivers.core.minecraft.Entity;
import net.gunivers.core.minecraft.EntityType;
import net.gunivers.core.utils.tuple.Tuple;
import net.gunivers.core.utils.tuple.Tuple2;

public class EntityList extends List
{
	
	public EntityList()
	{
		super("gui.list.item.entity");

		for (EntityType et : EntityType.values())
		{
			addSubList(Tuple.newTuple(et.toString().toLowerCase(), (String[]) Entity.filter(et).toArray()));
		}
	}
}
