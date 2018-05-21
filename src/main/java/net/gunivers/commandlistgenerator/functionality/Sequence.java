package net.gunivers.commandlistgenerator.functionality;

import java.math.BigDecimal;
import java.util.ArrayList;

import net.gunivers.commandlistgenerator.util.Calculator;
import net.gunivers.commandlistgenerator.util.Type;
import net.gunivers.core.language.tuple.Tuple;
import net.gunivers.core.language.tuple.Tuple4;

/**
 * @author Oromis A function to increment at each loop
 */
public class Sequence extends Functionality {

	/**
	 * @param initValue
	 *            first value of the loop
	 * @param incr
	 *            number to add at each loop
	 * @param nbLoop
	 *            number of loop
	 * @return an ArrayList<String> with all the value replacing the tag
	 */
	@Override
	public ArrayList<String> generate(Tuple t, Integer nbLoop) {
		//<initValue, operation, round, type>
		Tuple4<Double, String, Integer, Type> tuple = Tuple.castTo(t, Tuple.newTuple(Double.class, String.class, Integer.class, Type.class));
		String end = "";

		if (tuple._1 != null) {
			if (tuple._4.equals(Type.LONG))
				end = "L";

			else if (tuple._4.equals(Type.FLOAT))
				end = "F";

			else if (tuple._4.equals(Type.DOUBLE))
				end = "D";

			else if (tuple._4.equals(Type.SHORT))
				end = "s";

			else if (tuple._4.equals(Type.BYTE))
				end = "b";
			if (tuple._3 == 0 && (end == "D" || end == "D"))
				end = ".0" + end;
		}

		ArrayList<String> save = new ArrayList<String>();

		BigDecimal bd = new BigDecimal(tuple._1);
		bd = bd.setScale(tuple._3, BigDecimal.ROUND_HALF_UP);
		save.add(bd.toString());

		for (int i = 0; i < nbLoop; i++) {
			Calculator calc = new Calculator(tuple._2.replaceAll("[uU]", Double.toString(tuple._1)));
			Double initValue = calc.calculate();
			BigDecimal bd2 = new BigDecimal(initValue);
			bd2 = bd2.setScale(tuple._3, BigDecimal.ROUND_HALF_UP);
			save.add(bd2.toString() + end);
		}
		
		return save;
	}

	@Override
	public String toString() {
		return "Sequence";
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return null;
	}
}
