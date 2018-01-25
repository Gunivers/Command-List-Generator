package fr.gunivers.cmdlg.generators;

import java.util.ArrayList;
import java.util.Arrays;

import fr.gunivers.cmdlg.api.BasicGenerator;
import fr.gunivers.cmdlg.util.GeneratorType;
import fr.gunivers.cmdlg.util.Material;

public class MaterialGenerator extends BasicGenerator {

    private GeneratorType type = GeneratorType.MATERIAL;

    public MaterialGenerator(String... commands) {
        super(commands);
    }

    /**
     * Generate Simple Material
     *
     * @return ArrayList<String> commands
     */
    @Override
    public Iterable<? extends String> generate() {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values()) {
            StringBuilder temp = new StringBuilder();

            for (int e = 0; e < getCommand().length - 1; e++) {
                temp.append(getCommand()[e]).append(material.name());

                if (e == getCommand().length - 2) temp.append(getCommand()[e + 1]);
            }

            commands.add(temp.toString());
        }

        return commands;
    }

    /**
     * Get the registered type
     *
     * @return GeneratorType type
     */
    @Override
    public GeneratorType getType() {
        return type;
    }

    /**
     * Get the Material to be generated
     *
     * @return ArrayList<Material> materialList
     */
    public Iterable<? extends Material> getMaterialList() {
        return Arrays.asList(Material.values());
    }

}
