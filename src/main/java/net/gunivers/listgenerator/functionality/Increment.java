package net.gunivers.listgenerator.functionality;

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
	public ArrayList<String> generate(Double initValue, Double increment, Integer nbLoop, boolean round, Class<? extends Number> type) {
        char end = '\u0000';
        
        if (type.equals(Long.class)) 
            end = 'L';
        
        else if (type.equals(Float.class))
            end = 'F';
        
        else if (type.equals(Double.class)) 
            end = 'D';
            	
        else if (type.equals(Short.class)) 
            end = 's'; 
        
        else if (type.equals(Byte.class))
            end = 'b';
        
        ArrayList<String> save = new ArrayList<String>();
        save.add(initValue.toString());
        
        for(int i = 0; i < nbLoop; i++)
            if (round) save.add(((Long) Math.round(initValue += increment)).toString() + end);
            else save.add((initValue += increment).toString() + end);
        
        return save;
    }

	@Override
	public String toString() {
		return "Increment";
	}
}
