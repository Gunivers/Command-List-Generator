package net.gunivers.listgenerator.functionality.materialID;


import net.gunivers.listgenerator.util.Call;
import net.gunivers.listgenerator.util.Functionality;
import net.gunivers.minecraft.Material;

import java.util.ArrayList;


public class IDBlockGenerator extends Functionality
{

    /**
     * Generate Blocks ID
     *
     * @return ArrayList<String> commands
     */
	@Call
    public ArrayList<String> generate()
    {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values())
        {

            if (material.isBlock())
            {
                commands.add(((Integer) material.getId()).toString());
            }

        }
        return commands;
    }
    
	@Override
	public String toString() {
		return "Block ID";
	}
}