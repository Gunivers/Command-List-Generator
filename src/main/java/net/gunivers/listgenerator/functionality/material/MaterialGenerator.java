package net.gunivers.listgenerator.functionality.material;

import net.gunivers.cmdlg.util.Material;
import net.gunivers.listgenerator.functionality.Functionality;
import net.gunivers.listgenerator.util.Call;

import java.util.ArrayList;

public class MaterialGenerator extends Functionality
{

    /**
     * Generate Simple Material
     *
     * @return ArrayList<String> commands
     */
    @Call
    public ArrayList<String> generate()
    {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values())
        {
            commands.add(material.name());
        }

        return commands;
    }

	@Override
	public String toString() {
		return "Material";
	}
}
