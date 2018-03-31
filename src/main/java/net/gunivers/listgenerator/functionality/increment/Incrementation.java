package net.gunivers.listgenerator.functionality.increment;

import java.util.ArrayList;

import net.gunivers.listgenerator.functionality.Functionality;
import net.gunivers.listgenerator.util.Call;

public class Incrementation extends Functionality {

	@Call
	public ArrayList<String> incremente(Integer initValue, Integer incr, Integer nbLoop) {
		ArrayList<String> save = new ArrayList<String>();
		save.add(Integer.toString(initValue));
		
		for(int i = 0; i < nbLoop; i++)
			save.add(Integer.toString(initValue += incr));
		
		return save;
	}

	@Override
	public String toString() {
		return "Incrémentation";
	}
}
