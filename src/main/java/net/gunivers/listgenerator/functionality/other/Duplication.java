package net.gunivers.listgenerator.functionality.other;

import java.util.ArrayList;

import net.gunivers.listgenerator.functionality.Functionality;
import net.gunivers.listgenerator.util.Call;

public class Duplication extends Functionality {

	@Override
	public String toString() {
		return "Duplication";
	}
	
	@Call
	public ArrayList<String> duplication(String txt, double multiplier, double add, int nbreCommands) {
		
        ArrayList<String> commands = new ArrayList<>();
		
		for(int i = 0; i < nbreCommands; i++)
			commands.add(i, duplicate(txt, (int) Math.round((i+1)*multiplier+add)));
        
        return commands;
	}

	private String duplicate(String txt, int times) {
		
		StringBuilder ret = new StringBuilder();
		
		for(int i = 0; i < times; i++)
			ret.append(txt);
		
		return ret.toString();
	}
	
}