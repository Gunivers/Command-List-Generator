package net.gunivers.listgenerator.functionality.materialID;


import net.gunivers.listgenerator.functionality.material.Material;
import net.gunivers.listgenerator.util.Call;
import net.gunivers.listgenerator.util.Functionality;

import java.util.ArrayList;


public class IDFlammableGenerator extends Functionality
{

    /**
     * Generate Flammables ID
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

            if (material.isFlammable())
            {
                commands.add(((Integer) material.getId()).toString());
            }

        }
        return commands;
    }

	@Override
	public String toString() {
		return "Flammable ID";
	}
}