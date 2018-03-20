package net.gunivers.listgenerator.functionality;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Incrementation {
	
	public static Method getIncremente() {
		try {
			return Incrementation.class.getMethod("incremente", Integer.class, Integer.class, Integer.class);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<String> incremente(Integer initValue, Integer incr, Integer nbLoop) {
		ArrayList<String> save = new ArrayList<String>();
		save.add(Integer.toString(initValue));
		for(int i = 0; i < nbLoop; i++)
			save.add(Integer.toString(initValue += incr));
		return save;
	}
	
}
