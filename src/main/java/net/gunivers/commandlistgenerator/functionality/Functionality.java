package net.gunivers.commandlistgenerator.functionality;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import net.gunivers.commandlistgenerator.util.HelpFunctionality;
import net.gunivers.core.utils.tuple.Tuple;


/**
 * @author Oromis Abstract method representing a functionality of the Command
 *         List Generator
 */
public abstract class Functionality extends HelpFunctionality implements Serializable {

	private static final long serialVersionUID = 11L;
	
	/**
	 * This Map stocks all functionalities
	 */
	private static HashMap<String, Functionality> functionalities = new HashMap<String, Functionality>();
	
	/**
	 * @return a HashMap of all available functionnalities
	 */
	public static HashMap<String, Functionality> getFunctionalities() {
		return functionalities;
	}

	/**
	 * @return the functionality
	 */
	public static Functionality getFunctionalityByTag(String name) {
		for(Entry<String, Functionality> f : getFunctionalities().entrySet())
			if(f.getValue().toString().equals(name))
				return f.getValue();
		return null;
	}
	
	public static Functionality getFunctionalityByDefaultName(String name) {
		return functionalities.get(name);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public abstract String toString();
	
	public abstract String getDefaultName();

	/**
	 * Save each instance of a functionality
	 */
	 {
		functionalities.put(this.getDefaultName(), this);
	 }
	 
	public abstract ArrayList<String> generate(Tuple tuple, Integer nbLoop);
	
	public abstract String getHelp();
}
