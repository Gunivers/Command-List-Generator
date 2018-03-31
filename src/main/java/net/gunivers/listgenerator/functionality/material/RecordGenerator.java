package net.gunivers.listgenerator.functionality.material;


import net.gunivers.cmdlg.util.Material;
import net.gunivers.listgenerator.Call;
import net.gunivers.listgenerator.functionality.Functionality;

import java.util.ArrayList;

public class RecordGenerator extends Functionality
{
    /**
     * Generate record Material
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
                commands.add(material.name());
            }
        }
        return commands;
    }
	@Override
	public String toString() {
		return "Record";
	}
}