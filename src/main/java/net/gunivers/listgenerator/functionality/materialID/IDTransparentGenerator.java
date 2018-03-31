package net.gunivers.listgenerator.functionality.materialID;

import net.gunivers.cmdlg.util.GeneratorType;
import net.gunivers.cmdlg.util.Material;

import java.util.ArrayList;


public class IDTransparentGenerator extends IDMaterialGenerator
{

    private GeneratorType type = GeneratorType.TRANSPARENT_ID;

    public IDTransparentGenerator(String... commands)
    {
        super(commands);
    }

    /**
     * Generate Transparents ID
     *
     * @return ArrayList<String> commands
     */
    @Override
    public ArrayList<String> generate()
    {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values())
        {

            if (material.isTransparent())
            {
                StringBuilder temp = new StringBuilder();

                for (int e = 0; e < getCommand().length - 1; e++)
                {
                    temp.append(getCommand()[e]).append(material.getId());

                    if (e == getCommand().length - 2) temp.append(getCommand()[e + 1]);
                }

                commands.add(temp.toString());
            }

        }
        return commands;
    }

    /**
     * Get the Transparents ID to be generated
     *
     * @return ArrayList<Material> material ID List
     */
    public Iterable<? extends Integer> getIDList()
    {
        ArrayList<Integer> matos = new ArrayList<Integer>();

        for (Material material : Material.values()) if (material.isTransparent()) matos.add(material.getId());

        return matos;
    }

    @Override
    public GeneratorType getType()
    {
        return type;
    }
}