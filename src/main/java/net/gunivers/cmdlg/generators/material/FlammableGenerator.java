package net.gunivers.cmdlg.generators.material;

<<<<<<< Updated upstream:src/main/java/fr/gunivers/cmdlg/generators/material/FlammableGenerator.java
package fr.gunivers.cmdlg.generators.material;

import java.util.ArrayList;
import java.util.Iterator;

import net.gunivers.cmdlg.util.GeneratorType;
import net.gunivers.cmdlg.util.Material;

public class FlammableGenerator extends MaterialGenerator {
	
	private GeneratorType type = GeneratorType.FLAMMABLE;
	
	/**
     * Generate Blocks Material
     *
     * @return ArrayList<String> commands
     */
    @Override
    public Iterator<String> generate() {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values()) {
        	if (material.isFlammable()) {
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
    	for (Material matos : Material.values()) if (matos.isFlammable()) values.add(matos);
    	
        return values;
    }
}
=======
package net.gunivers.cmdlg.generators.material;

import java.util.ArrayList;
import java.util.Iterator;

import net.gunivers.cmdlg.util.GeneratorType;
import net.gunivers.cmdlg.util.Material;

public class FlammableGenerator extends MaterialGenerator {
	
	private GeneratorType type = GeneratorType.FLAMMABLE;
	
	/**
     * Generate Blocks Material
     *
     * @return ArrayList<String> commands
     */
    @Override
    public ArrayList<String> generate() {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values()) {
        	if (material.isFlammable()) {
        		StringBuilder temp = new StringBuilder();
        		
        		for (int e = 0; e < getCommand().length - 1; e++) {
        			temp.append(getCommand()[e]).append(material.name());
        			
        			if (e == getCommand().length - 2) temp.append(getCommand()[e + 1]);
	           }
        		
        		commands.add(temp.toString());
        	}
        }
        return commands;
    }

    /**
     * Get the Materials to be generated
     *
     * @return ArrayList<Material> materialList
     */
    public Iterable<? extends Material> getMaterialList() {
    	ArrayList<Material> values = new ArrayList<Material>();
    	for (Material matos : Material.values()) if (matos.isFlammable()) values.add(matos);
    	
        return values;
    }
}
>>>>>>> Stashed changes:src/main/java/net/gunivers/cmdlg/generators/material/FlammableGenerator.java
