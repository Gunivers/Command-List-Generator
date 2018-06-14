package net.gunivers.commandlistgenerator.functionality.list;

import java.util.ArrayList;

import net.gunivers.commandlistgenerator.util.BooleanFunctionalInterface;
import net.gunivers.core.minecraft.Material;
import net.gunivers.core.utils.tuple.Tuple;

public class MaterialList extends List
{
	private static final long serialVersionUID = 4L;

	public MaterialList() {
		super("gui.list.item.material");
		addMaterialList((Material e) -> e instanceof Material, "gui.list.item.all");
		addMaterialList((Material e) -> e.isBlock(), "gui.list.item.material.block");
		addMaterialList((Material e) -> e.isItem(), "gui.list.item.material.item");
		addMaterialList((Material e) -> e.isBurnable(), "gui.list.item.material.burnableblock");
		addMaterialList((Material e) -> e.isEdible(), "gui.list.item.material.edibleitem");
		addMaterialList((Material e) -> e.isFlammable(), "gui.list.item.material.flammableblock");
		addMaterialList((Material e) -> e.isFuel(), "gui.list.item.material.fuelitem");
		addMaterialList((Material e) -> e.isOccluding(), "gui.list.item.material.occludingblock");
		addMaterialList((Material e) -> e.isRecord(), "gui.list.item.material.record");
		addMaterialList((Material e) -> e.isSolid(), "gui.list.item.material.solidblock");
		addMaterialList((Material e) -> e.isTransparent(), "gui.list.item.material.transparentblock");
		addMaterialList((Material e) -> e.hasGravity(), "gui.list.item.material.gravityblock");
	}
	
	public void addMaterialList(BooleanFunctionalInterface<Material> b, String name) {
		ArrayList<String> materials = new ArrayList<String>();
		for(Material material : Material.values())
			if(b.invoke(material))
				materials.add(material.toString().toLowerCase());
		addSubList(Tuple.newTuple(name, materials.toArray(new String[materials.size()])));
	}

}
