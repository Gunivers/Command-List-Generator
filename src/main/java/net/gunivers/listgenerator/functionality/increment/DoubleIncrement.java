package net.gunivers.listgenerator.functionality.increment;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * 
 * @author AZ
 *
 */
public class DoubleIncrement {
	
	public static Method getIncrement() {
			try { return DoubleIncrement.class.getMethod("increment", Double.class, Double.class, Integer.class);} 
			
			catch (NoSuchMethodException | SecurityException e) {e.printStackTrace();}
			
			return null;
	}
	
	public static ArrayList<String> generate(Double init, Double increment, Integer nbLoop) {
		ArrayList<String> save = new ArrayList<String>();
		save.add(Double.toString(init));
		
		for (int i = 0; i <=nbLoop; i++) save.add(Double.toString(init += increment) +"D");
		
		return save;
	}
}
