package net.gunivers.listgenerator.functionality.material;


import net.gunivers.listgenerator.util.Call;
import net.gunivers.listgenerator.util.Functionality;
import net.gunivers.minecraft.Material;

import java.util.ArrayList;

public class EdibleGenerator extends Functionality
{

    /**
     * Generate Edible Material
     *
     * @return ArrayList<String> commands
     */
    @Call
    public ArrayList<String> generate()
    {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values())
        {
            if (material.isEdible())
            {
                commands.add(material.name());
            }
        }
        return commands;
    }

	@Override
	public String toString() {
		return "EdibleGenerator";
	}
}