package net.gunivers.commandlistgenerator.functionality;

import java.util.ArrayList;
import java.util.Arrays;

import net.gunivers.core.language.tuple.Tuple;
import net.gunivers.core.language.tuple.Tuple1;
import net.gunivers.core.minecraft.Entity;

/**
 * @authors A~Z, Oromis Generate by list
 */
public class GeneratorByList {

	public String toString() {
		return "List";
	}

	/**
	 * @param list
	 *            a table of String
	 * @return an ArrayList of the content of list
	 */
	public ArrayList<String> generate(Tuple t) {
		Tuple1<String[]> tuple = Tuple.castTo(t, Tuple.newTuple(String[].class));
		return (ArrayList<String>) Arrays.asList(tuple._1);
	}
	
	public  ArrayList<Class<? extends Enum<?>>> getEnums() {
		ArrayList<Class<? extends Enum<?>>> list = new ArrayList<>();
		list.add(Entity.class);
		return list;
	}
}
