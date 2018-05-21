package net.gunivers.commandlistgenerator.functionality;

import java.util.ArrayList;
import java.util.HashMap;

import net.gunivers.commandlistgenerator.util.HelpFunctionality;
import net.gunivers.core.language.tuple.Tuple;


/**
 * @author Oromis Abstract method representing a functionality of the Command
 *         List Generator
 */
public abstract class Functionality extends HelpFunctionality {

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
	public static Functionality getFunctionalities(String name) {
		return functionalities.get(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public abstract String toString();

	/**
	 * Save each instance of a functionality
	 */
	 {
		functionalities.put(this.toString(), this);
	}

	public abstract ArrayList<String> generate(Tuple tuple, Integer nbLoop);
	
	public abstract String getHelp();
}
