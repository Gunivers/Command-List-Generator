package net.gunivers.listgenerator.functionality.increment;

import java.util.ArrayList;

import net.gunivers.listgenerator.util.Functionality;

public class LongIncrement extends Functionality {

	public ArrayList<String> generate(Long init, Long increment, Integer nbLoop) {
		ArrayList<String> save = new ArrayList<String>();
		save.add(init.toString());
		
		for (int i = 0; i < nbLoop; i++) {
			save.add((init += increment).toString());
		}
		
		return save;	
	}
	
	@Override
	public String toString() {
		return "Long Increment";
	}

}
