package fr.gunivers.cmdlg.generators.material;

import java.util.ArrayList;

import fr.gunivers.cmdlg.util.GeneratorType;
import fr.gunivers.cmdlg.util.Material;

public class MaterialGenerator extends fr.gunivers.cmdlg.api.MaterialGenerator{

	private GeneratorType type = GeneratorType.MATERIAL;
	
	/**Generate Simple Material
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

	/**Get the registered type
	 * 
	 * @return GeneratorType type
	 */
	@Override
	public GeneratorType getType() {
		return type;
	}

	/**Get the Material to be generated
	 * 
	 * @return ArrayList<Material> materialList
	 */
	@Override
	public Iterable<? extends Material> getMaterialList() {
		ArrayList<Material> matosList = new ArrayList<Material>();
		
		for (Material matos : Material.values()) matosList.add(matos);
		
		return matosList;
	}

}
