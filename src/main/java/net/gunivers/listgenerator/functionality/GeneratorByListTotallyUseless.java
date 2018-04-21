package net.gunivers.listgenerator.functionality;

import java.util.ArrayList;

import net.gunivers.listgenerator.util.Functionality;

/**
 * @author A~Z
 * I'm choosed to do do such an useless task. Sure it is my fate :/
 */
public class GeneratorByListTotallyUseless extends Functionality {

	ArrayList<String> theListThatIsAlreadyCreated;
	
	public GeneratorByListTotallyUseless(ArrayList<String> theListThatIsAlreadyCreated) {
		this.theListThatIsAlreadyCreated = theListThatIsAlreadyCreated;
	}
	
	/**generate
	 * Useless code !!
	 * 
	 * @param theListThatIsAlreadyCreated this name is obvious, don't ya think ?
	 * @return THAT. It does not even modify it. It. Just. Return. It!
	 */
	public ArrayList<String> generate() {
		return this.theListThatIsAlreadyCreated;
	}
	
	/**toString
	 * This name means this name
	 * 
	 * @return List - it's my name and I'm proud of it
	 */
	@Override
	public String toString() {
		return "List";
	}

	@Override
	public ArrayList<Object> callParameterOverlay() {
		// TODO: The method callParameterOverlay
		return null;
	}
	
	/**
	 * 
	 * @param newList to set instead of current
	 * @return new current list
	 */
	public ArrayList<String> setList(ArrayList<String> newList) {
		return this.theListThatIsAlreadyCreated = newList;
	}
	
	/**
	 * 
	 * @return current list
	 */
	public ArrayList<String> getList() {
		return this.theListThatIsAlreadyCreated;
	}
}
