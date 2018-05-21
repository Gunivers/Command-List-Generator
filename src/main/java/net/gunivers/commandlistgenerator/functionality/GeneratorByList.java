package net.gunivers.commandlistgenerator.functionality;

import java.util.ArrayList;
import java.util.Arrays;

import net.gunivers.core.language.tuple.Tuple;
import net.gunivers.core.language.tuple.Tuple1;

/**
 * @authors A~Z, Oromis
 * Generate by list
 */
public class GeneratorByList extends Functionality
{

	@Override
	public String toString()
	{
		return "List";
	}
	
	/**
	 * @param list a table of String
	 * @return an ArrayList of the content of list
	 */
	@Override
	public ArrayList<String> generate(Tuple t) {
		Tuple1<String[]> tuple = Tuple.castTo(t, Tuple.newTuple(String[].class));
		return (ArrayList<String>) Arrays.asList(tuple._1);
	}
}
