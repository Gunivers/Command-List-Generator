package net.gunivers.commandlistgenerator.functionality.list;

import java.util.ArrayList;

import net.gunivers.commandlistgenerator.util.BooleanFunctionalInterface;
import net.gunivers.core.minecraft.Material;
import net.gunivers.core.utils.tuple.Tuple;

public class MaterialList extends List {
	
	public MaterialList() {
		super("gui.list.item.material");
		addMaterialList((Object e) -> ((Material) e) instanceof Material, "gui.list.item.all");
		addMaterialList((Object e) -> ((Material) e).isBlock(), "gui.list.item.material.block");
		addMaterialList((Object e) -> ((Material) e).isItem(), "gui.list.item.material.item");
		addMaterialList((Object e) -> ((Material) e).isBurnable(), "gui.list.item.material.burnableblock");
		addMaterialList((Object e) -> ((Material) e).isEdible(), "gui.list.item.material.edibleitem");
		addMaterialList((Object e) -> ((Material) e).isFlammable(), "gui.list.item.material.flammableblock");
		addMaterialList((Object e) -> ((Material) e).isFuel(), "gui.list.item.material.fuelitem");
		addMaterialList((Object e) -> ((Material) e).isOccluding(), "gui.list.item.material.occludingblock");
		addMaterialList((Object e) -> ((Material) e).isRecord(), "gui.list.item.material.record");
		addMaterialList((Object e) -> ((Material) e).isSolid(), "gui.list.item.material.solidblock");
		addMaterialList((Object e) -> ((Material) e).isTransparent(), "gui.list.item.material.transparentblock");
		addMaterialList((Object e) -> ((Material) e).hasGravity(), "gravityblock");
	}
	
	public void addMaterialList(BooleanFunctionalInterface b, String name) {
		ArrayList<String> materials = new ArrayList<String>();
		for(Material material : Material.values())
			if(b.invoke(material))
				materials.add(material.toString().toLowerCase());
		addSubList(Tuple.newTuple(name, materials.toArray(new String[materials.size()])));
	}

}
