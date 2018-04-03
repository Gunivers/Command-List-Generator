package net.gunivers.minecraft;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * @author Oromis
 * Represent the different properties of a block
 */
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
	
	/**
	 * @param method a string corresponding at a name of a method
	 */
	MaterialType(String method) {
		this.method.append(method);
	}
	
	/**
	 * @param material a Material
	 * @param mt a MaterialType
	 * @return the result of the method having for name mt of material
	 */
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
