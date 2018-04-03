package net.gunivers.listgenerator.functionality.increment;

import java.util.ArrayList;

import net.gunivers.listgenerator.functionality.Functionality;
import net.gunivers.listgenerator.util.Call;

/**
 * @author Oromis
 * A function to increment at each loop
 */
public class Increment extends Functionality {

	/**
	 * @param initValue first value of the loop
	 * @param incr number to add at each loop
	 * @param nbLoop number of loop
	 * @return an ArrayList<String> with all the value replacing the tag
	 */
	@Call
	public ArrayList<String> incremente(Double initValue, Double incr, Integer nbLoop) {
		ArrayList<String> save = new ArrayList<String>();
		save.add(Double.toString(initValue));
		
		for(int i = 0; i < nbLoop; i++)
			save.add(Double.toString(initValue += incr));
		
		return save;
	}

	@Override
	public String toString() {
		return "Increment";
	}
}
