package fr.gunivers.cmdlg.generators.material;

import java.util.ArrayList;
import java.util.Iterator;

import fr.gunivers.cmdlg.util.GeneratorType;
import fr.gunivers.cmdlg.util.Material;

public class BurnableGenerator extends MaterialGenerator {
	
	private GeneratorType type = GeneratorType.BURNABLE;
	
	/**
     * Generate Burnable Material
     *
     * @return ArrayList<String> commands
     */
    @Override
    public Iterator<String> generate() {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values()) {
        	if (material.isBurnable()) {
        		StringBuilder temp = new StringBuilder();
        		
        		for (int e = 0; e < getCommand().length - 1; e++) {
        			temp.append(getCommand()[e]).append(material.name());
        			
        			if (e == getCommand().length - 2) temp.append(getCommand()[e + 1]);
	           }
        		
        		commands.add(temp.toString());
        	}
        }
        return commands.iterator();
    }

    /**
     * Get the Materials to be generated
     *
     * @return ArrayList<Material> materialList
     */
    public Iterable<? extends Material> getMaterialList() {
    	ArrayList<Material> values = new ArrayList<Material>();
    	for (Material matos : Material.values()) if (matos.isBurnable()) values.add(matos);
    	
        return values;
    }
}
