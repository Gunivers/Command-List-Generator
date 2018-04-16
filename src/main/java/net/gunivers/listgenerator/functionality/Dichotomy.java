package net.gunivers.listgenerator.functionality;

import net.gunivers.listgenerator.util.Call;
import net.gunivers.listgenerator.util.Functionality;

import java.util.ArrayList;

public class Dichotomy extends Functionality
{

    @Override
    public String toString()
    {
        return "Dichotomy";
    }

    @Call
    public ArrayList<String> dichotomy(int value)
    {

        ArrayList<String> commands = new ArrayList<>();

        long l = 1;

        while (l < value)
        {
            commands.add(String.valueOf(l));
            l = 2 * l;
        }

        return commands;
    }
    
    @Override
	public ArrayList<Object> callParameterOverlay() {
		// TODO
		return null;
	}

}