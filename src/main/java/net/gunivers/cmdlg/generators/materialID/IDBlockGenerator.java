package net.gunivers.cmdlg.generators.materialID;

import net.gunivers.cmdlg.util.GeneratorType;
import net.gunivers.minecraft.Material;

import java.util.ArrayList;


public class IDBlockGenerator extends IDMaterialGenerator
{

    private GeneratorType type = GeneratorType.BLOCK_ID;

    public IDBlockGenerator(String... commands)
    {
        super(commands);
    }

    /**
     * Generate Blocks ID
     *
     * @return ArrayList<String> commands
     */
    @Override
    public ArrayList<String> generate()
    {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values())
        {

            if (material.isBlock())
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
     * Get the Blocks ID to be generated
     *
     * @return ArrayList<Material> material ID List
     */
    public Iterable<? extends Integer> getIDList()
    {
        ArrayList<Integer> matos = new ArrayList<Integer>();

        for (Material material : Material.values()) if (material.isBlock()) matos.add(material.getId());

        return matos;
    }

    @Override
    public GeneratorType getType()
    {
        return type;
    }
}