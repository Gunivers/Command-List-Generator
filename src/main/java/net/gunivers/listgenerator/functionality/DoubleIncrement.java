package net.gunivers.listgenerator.functionality;

import java.util.ArrayList;

public class DoubleIncrement {
	
	
	public static ArrayList<String> incremente(Double init, Double increment, Integer nbLoop) {
		ArrayList<String> save = new ArrayList<String>();
		save.add(Double.toString(init));
		
		for (int i = 0; i <=nbLoop; i++) save.add(Double.toString(init += increment));
		
		return save;
	}
}
