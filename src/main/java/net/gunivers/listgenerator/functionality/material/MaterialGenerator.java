package net.gunivers.listgenerator.functionality.material;

import net.gunivers.listgenerator.util.Call;
import net.gunivers.listgenerator.util.Functionality;
import net.gunivers.minecraft.Material;
import net.gunivers.minecraft.MaterialType;

import java.util.ArrayList;

public class MaterialGenerator extends Functionality
{

    /**
     * Generate Simple Material
     *
     * @return ArrayList<String> generated material names
     */
    @Call
    public ArrayList<String> generate(MaterialType mt)
    {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values())
        {
            if (MaterialType.checkMaterial(material, mt)) commands.add(material.name());
        }

        return commands;
    }

    /**
     * @return Material
     */
	@Override
	public String toString() {
		return "Material";
	}
}
