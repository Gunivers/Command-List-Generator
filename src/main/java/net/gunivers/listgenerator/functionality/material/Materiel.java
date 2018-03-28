package net.gunivers.listgenerator.functionality.material;

import java.util.ArrayList;

import net.gunivers.cmdlg.util.Material;
import net.gunivers.listgenerator.functionality.Functionality;

public class Materiel extends Functionality
{
    /**
     * Generate Simple Material
     *
     * @return ArrayList<String> commands
     */
    public ArrayList<String> generate()
    {
        ArrayList<String> save = new ArrayList<String>();

        for (Material material : Material.values())
        {
            save.add(material.name());
        }

        return save;
    }

    public String toString() {
    	return "Material";
    }
}
