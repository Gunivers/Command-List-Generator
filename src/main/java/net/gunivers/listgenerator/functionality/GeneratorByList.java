package net.gunivers.listgenerator.functionality;

import net.gunivers.listgenerator.util.Functionality;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @authors A~Z, Oromis
 * Generate by list
 */
public class GeneratorByList extends Functionality
{

    /**
     * @param list a table of String
     * @return an ArrayList of the content of list
     */
    public ArrayList<String> generate(String[] list)
    {
        return (ArrayList<String>) Arrays.asList(list);
    }

    @Override
    public String toString()
    {
        return "List";
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
