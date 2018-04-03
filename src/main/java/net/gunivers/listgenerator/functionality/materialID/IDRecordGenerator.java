package net.gunivers.listgenerator.functionality.materialID;


import net.gunivers.listgenerator.util.Call;
import net.gunivers.listgenerator.util.Functionality;
import net.gunivers.minecraft.Material;

import java.util.ArrayList;


public class IDRecordGenerator extends Functionality
{


    /**
     * Generate Records ID
     *
     * @return ArrayList<String> commands
     */
    @Call
    public ArrayList<String> generate()
    {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values())
        {

            if (material.isRecord())
            {
                commands.add(((Integer) material.getId()).toString());
            }

        }
        return commands;
    }

	@Override
	public String toString() {
		return "Record ID";
	}
}