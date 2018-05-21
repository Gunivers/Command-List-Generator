package net.gunivers.commandlistgenerator.functionality;

import java.util.ArrayList;

import net.gunivers.core.minecraft.Material;
import net.gunivers.core.minecraft.MaterialType;

/**
 * @author A~Z Generate Material values
 */
@Deprecated
public class MaterialGenerator {
	MaterialType mt;

	/**
	 * <strong>Constructor</strong>
	 *
	 * @param mt
	 *            a MaterialType
	 */
	public MaterialGenerator(MaterialType mt) {
		this.mt = mt;
	}

	/**
	 * Generate Simple Material
	 *
	 * @param mt
	 *            a MaterialType
	 * @return ArrayList<String> generated Material names
	 */
	public static ArrayList<String> generate(MaterialType mt) {
		ArrayList<String> commands = new ArrayList<>();

		for (Material material : Material.values()) {
			if (MaterialType.checkMaterial(material, mt))
				commands.add(material.name());
		}

		return commands;
	}

	/**
	 * @param mt
	 *            a MaterialType
	 * @return String name of mt
	 */
	public static String toString(MaterialType mt) {
		return mt.toString();
	}

	/**
	 * Generate simple Material
	 *
	 * @return ArrayList<String> generated Material names
	 */
	public ArrayList<String> generate() {
		ArrayList<String> commands = new ArrayList<>();

		for (Material material : Material.values()) {
			if (MaterialType.checkMaterial(material, mt))
				commands.add(material.name());
		}

		return commands;
	}

	@Override
	public String toString() {
		return "Material";
	}
}
