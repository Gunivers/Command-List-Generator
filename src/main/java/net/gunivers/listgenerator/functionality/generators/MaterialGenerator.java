package net.gunivers.listgenerator.functionality.generators;

import net.gunivers.listgenerator.util.Call;
import net.gunivers.listgenerator.util.Functionality;
import net.gunivers.minecraft.Material;
import net.gunivers.minecraft.MaterialType;

import java.util.ArrayList;

/**
 * 
 * @author A~Z
 * Generate Material values
 */
public class MaterialGenerator extends Functionality
{
	MaterialType mt;
	
	/** <strong>Constructor</strong>
	 * 
	 * @param mt a MaterialType
	 */
	public MaterialGenerator(MaterialType mt) {
		this.mt = mt;
	}
	
	/**
	 * Generate simple Material
	 * @return ArrayList<String> generated Material names
	 */
	public ArrayList<String> generate() {
		ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values())
        {
            if (MaterialType.checkMaterial(material, mt)) commands.add(material.name());
        }

        return commands;
	}
	
    /**
     * Generate Simple Material
     *
     * @param mt a MaterialType
     * @return ArrayList<String> generated Material names
     */
    @Call
    public static ArrayList<String> generate(MaterialType mt)
    {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values())
        {
            if (MaterialType.checkMaterial(material, mt)) commands.add(material.name());
        }

        return commands;
    }

	@Override
	public String toString() {
		return "Material";
	}
	
	/**
	 * 
	 * @param mt a MaterialType
	 * @return String name of mt
	 */
	public static String toString(MaterialType mt) {
		return mt.toString();
	}
}
