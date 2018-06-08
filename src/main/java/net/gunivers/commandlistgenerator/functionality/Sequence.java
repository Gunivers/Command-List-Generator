package net.gunivers.commandlistgenerator.functionality;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.jfoenix.controls.JFXSnackbar;

import net.gunivers.commandlistgenerator.gui.CommandListGeneratorController;
import net.gunivers.commandlistgenerator.util.Calculator;
import net.gunivers.commandlistgenerator.util.Type;
import net.gunivers.core.utils.tuple.Tuple;
import net.gunivers.core.utils.tuple.Tuple4;

/**
 * @author Oromis A function to increment at each loop
 */
public class Sequence extends Functionality {

	/**
	 * 
	 */
	private static final long serialVersionUID = 653116429362297064L;
	private JFXSnackbar bar = new JFXSnackbar(CommandListGeneratorController.MAIN_PANE);

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
		save.add(bd.toString() + end);
		
		Double precedValue = tuple._1;

		for (int i = 0; i < nbLoop; i++) {
			try {
				Calculator calc = new Calculator(tuple._2.replaceAll("[uU]", Double.toString(precedValue)));
				precedValue = calc.calculate();
				BigDecimal bd2 = new BigDecimal(precedValue);
				bd2 = bd2.setScale(tuple._3, BigDecimal.ROUND_HALF_UP);
				save.add(bd2.toString() + end);
			} catch(Exception e) {
				bar.show(l.get("gui.sequence.error.sequencemalformat").replace("%s%", tuple._2), 5 * 1000);
			}
		}
		
		return save;
	}

	@Override
	public String toString() {
		return l.get("gui.sequence.title");
	}

	@Override
	public String getHelp() {
		String operation = l.get("gui.sequence.parameter.operation");
		String initialValue = l.get("gui.sequence.parameter.initialvalue");
		String round = l.get("gui.functionalities.decimale");
		
		return 	l.get("gui.sequence.description")
				+ "\n"
				+ "\n" + l.get("gui.functionalities.description.parameters") + ":"
				+ "\n" + operation + ": " + l.get("gui.sequence.description.operation")
				+ "\n" + initialValue +": " + l.get("gui.sequence.description.initialvalue")
				+ "\n" + round + ": " + l.get("gui.functionalities.description.decimale")
				+ "\n"
				+ "\n" + l.get("gui.functionalities.description.example") + ":"
				+ "\n " + l.get("gui.commandlistgenerator.command") + ": cmd #Sequence:example#"
				+ "\n " + operation + ": \"U + 1\"; " + initialValue + ": 0; " + round + ": 0;"
				+ "\n cmd 0"
				+ "\n cmd 1"
				+ "\n cmd 2"
				+ "\n [...]"
				+ "\n " + operation + ": \"U + 2.3\"; " + initialValue + ": 5; " + round + ": 1;"
				+ "\n cmd 5.0"
				+ "\n cmd 7.3"
				+ "\n cmd 9.6"
				+ "\n [...]";
	}

	@Override
	public String getDefaultName() {
		return "Sequence";
	}
}
