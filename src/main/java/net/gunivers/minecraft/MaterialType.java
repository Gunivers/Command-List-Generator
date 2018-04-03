package net.gunivers.minecraft;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public enum MaterialType {

	OCCLUDING("isOccluding"),
	EDIBLE("isEdible"),
	RECORD("isRecord"),
	SOLID("isSolid"),
	BLOCK("isBlock"),
	TRANSPARENT("isTransparent"),
	FLAMMABLE("isFlammable"),
	BURNABLE("isBurnable"),
	FUEL("isFuel"),
	GRAVITY("hasGravity"),
	ITEM("isItem");
	
	StringBuilder method = new StringBuilder();
	
	MaterialType(String method) {
		this.method.append(method);
	}
	
	public static boolean checkMaterial(Material material, MaterialType mt) {
		try {
			Method m = Material.class.getMethod(mt.method.toString());
			return (boolean) m.invoke(material, new Object[] {});
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return false;
		}
	}
}
