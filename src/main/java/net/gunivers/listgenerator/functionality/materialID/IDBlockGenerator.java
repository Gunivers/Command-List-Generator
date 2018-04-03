package net.gunivers.listgenerator.functionality.materialID;


import net.gunivers.listgenerator.functionality.Functionality;
import net.gunivers.listgenerator.functionality.material.Material;
import net.gunivers.listgenerator.util.Call;

import java.util.ArrayList;


public class IDBlockGenerator extends Functionality
{

    /**
     * Generate Blocks ID
     *
     * @return ArrayList<String> commands
     */
	@SuppressWarnings("deprecation")
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