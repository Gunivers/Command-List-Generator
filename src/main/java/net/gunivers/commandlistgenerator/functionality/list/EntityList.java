package net.gunivers.commandlistgenerator.functionality.list;

import java.util.ArrayList;

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
			ArrayList<String> array = new ArrayList<String>();
			for(Entity e : Entity.filter(et))
			{
				array.add(e.toString().toLowerCase());
			}
			
			addSubList(Tuple.newTuple(et.toString().toLowerCase(), array.toArray(new String[array.size()])));
		}
	}
}
