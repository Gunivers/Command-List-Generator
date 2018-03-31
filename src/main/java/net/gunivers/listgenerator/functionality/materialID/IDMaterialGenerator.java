package net.gunivers.listgenerator.functionality.materialID;

import net.gunivers.cmdlg.util.Material;
import net.gunivers.listgenerator.functionality.Functionality;
import net.gunivers.listgenerator.util.Call;

import java.util.ArrayList;


public class IDMaterialGenerator extends Functionality
{

    /**
     * Generate Material ID
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
            commands.add(((Integer) material.getId()).toString());
        }

        return commands;
    }


	@Override
	public String toString() {
		return "Material ID";
	}
}