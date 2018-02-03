package net.gunivers.cmdlg.generators.materialID;

import java.util.ArrayList;

import net.gunivers.cmdlg.util.GeneratorType;
import net.gunivers.cmdlg.util.Material;


public class IDOccludingGenerator extends IDMaterialGenerator {

    private GeneratorType type = GeneratorType.MATERIAL_ID;

    public IDOccludingGenerator(String... commands) {
        super(commands);
    }

    /**
     * Generate Occludings ID
     *
     * @return ArrayList<String> commands
     */
    @Override
    public ArrayList<String> generate() {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values()) {
        	
            if (material.isOccluding()) {
            	StringBuilder temp = new StringBuilder();
            	
            	for (int e = 0; e < getCommand().length - 1; e++) {
            		temp.append(getCommand()[e]).append(material.getId());
            		
            		if (e == getCommand().length - 2) temp.append(getCommand()[e + 1]);
            	}
            	
            	commands.add(temp.toString());
            }
            
        }
        return commands;
    }

    /**
     * Get the Occludings ID to be generated
     *
     * @return ArrayList<Material> material ID List
     */
    public Iterable<? extends Integer> getIDList() {
    	ArrayList<Integer> matos = new ArrayList<Integer>();
    	
    	for (Material material : Material.values()) if (material.isOccluding()) matos.add(material.getId());
    	
        return matos;
    }
}