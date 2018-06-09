package net.gunivers.commandlistgenerator.functionality;

import java.util.ArrayList;
import java.util.Arrays;

import net.gunivers.commandlistgenerator.functionality.list.EntityList;
import net.gunivers.commandlistgenerator.functionality.list.MaterialList;
import net.gunivers.core.utils.tuple.Tuple;
import net.gunivers.core.utils.tuple.Tuple1;

/**
 * @authors A~Z, Oromis Generate by list
 */
public class GeneratorByList extends Functionality {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3580307125805726896L;

	{
		new MaterialList();
		new EntityList();
	}
	
	@Override
	public ArrayList<String> generate(Tuple t, Integer nbLoop) {
		Tuple1<String[]> tuple = Tuple.castTo(t, Tuple.newTuple(String[].class));
		ArrayList<String> list = new ArrayList<String>();
		list.addAll(Arrays.asList(tuple._1));
		return list;
	}
	
	@Override
	public String getHelp() {
		return l.get("gui.list.description1") + '\n' + l.get("gui.list.description2");
	}

	@Override
	public String getDefaultName() {
		return "List";
	}

	@Override
	public String toString() {
		return l.get("gui.list.title");
	}
}
