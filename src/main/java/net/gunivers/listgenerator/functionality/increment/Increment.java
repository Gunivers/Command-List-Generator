package net.gunivers.listgenerator.functionality.increment;

import java.util.ArrayList;

import net.gunivers.listgenerator.util.Call;
import net.gunivers.listgenerator.util.Functionality;

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
	public ArrayList<String> generate(Number initValue, Number increment, Integer nbLoop) {
		String end = "";
		
		if (initValue instanceof Long) {
			end = "L";
			Long init = (Long) initValue;
			Long incr = (Long) increment;
		}
		
		else if (initValue instanceof Float) {
			end = "F";
			Float init = (Float) initValue;
			Float incr = (Float) increment;
		}
		
		else if (initValue instanceof Double) {
			end = "D";
			Double init = (Double) initValue;
			Double incr = (Double) increment;
		}
		
		else if (initValue instanceof Short) {
			end = "s";
			Short init = (Short) initValue;
			Short incr = (Short) increment;
		}
		
		else if (initValue instanceof Byte) {
			end = "b";
			Byte init = (Byte) initValue;
			Byte incr = (Byte) increment;
		}
		
		else {
			Integer init = (Integer) initValue;
			Integer incr = (Integer) increment;
		}
		
		ArrayList<String> save = new ArrayList<String>();
		save.add(init.toString());
		
		for(int i = 0; i < nbLoop; i++)
			save.add((init += incr).toString() + end);
		
		return save;
	}

	@Override
	public String toString() {
		return "Increment";
	}
}
