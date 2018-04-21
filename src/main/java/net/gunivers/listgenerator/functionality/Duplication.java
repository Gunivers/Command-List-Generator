package net.gunivers.listgenerator.functionality;

import net.gunivers.listgenerator.util.Call;
import net.gunivers.listgenerator.util.Functionality;

import java.net.URL;
import java.util.ArrayList;

public class Duplication extends Functionality
{

    @Override
    public String toString()
    {
        return "Duplication";
    }

    @Call
    public ArrayList<String> duplication(String txt, double multiplier, double add, int nbreCommands)
    {

        ArrayList<String> commands = new ArrayList<>();

        for (int i = 0; i < nbreCommands; i++)
            commands.add(duplicate(txt, (int) Math.round((i + 1) * multiplier + add)));

        return commands;
    }

    private String duplicate(String txt, int times)
    {

        StringBuilder ret = new StringBuilder();

        for (int i = 0; i < times; i++)
            ret.append(txt);

        return ret.toString();
    }

    @Override
    public ArrayList<Object> callParameterOverlay()
    {
        // TODO
        return null;
    }

<<<<<<< HEAD
    @Override
    public URL getFXML()
    {
        return null;
    }
=======
	@Override
	public URL getFXML() {
		// TODO Auto-generated method stub
		return null;
	}
>>>>>>> 38fc4c186ffb5a55956bd18e15c78c6b0ebe5202

}