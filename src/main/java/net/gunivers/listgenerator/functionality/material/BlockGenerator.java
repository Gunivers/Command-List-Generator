package net.gunivers.listgenerator.functionality.material;

import net.gunivers.listgenerator.util.Call;
import net.gunivers.listgenerator.util.Functionality;
import net.gunivers.minecraft.Material;

import java.util.ArrayList;

public class BlockGenerator extends Functionality
{

    /**
     * Generate Block Material
     *
     * @return ArrayList<String> commands
     */
	@Call
    public ArrayList<String> generate()
    {
        ArrayList<String> save = new ArrayList<>();

        for (Material material : Material.values())
        {
            if (material.isBlock())
            {
                save.add(material.name());
            }
        }
        return save;
    }

    /**
     * Get the Materials to be generated
     *
     * @return ArrayList<Material> materialList
     */
    public Iterable<? extends Material> getMaterialList()
    {
        ArrayList<Material> values = new ArrayList<Material>();
        for (Material matos : Material.values()) if (matos.isBlock()) values.add(matos);

        return values;
    }

	@Override
	public String toString() {
		return "Block";
	}
}