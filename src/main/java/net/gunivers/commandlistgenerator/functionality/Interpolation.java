package net.gunivers.commandlistgenerator.functionality;

import java.util.ArrayList;

import net.gunivers.core.utils.tuple.Tuple;
import net.gunivers.core.utils.tuple.Tuple6;

public class Interpolation extends Functionality
{
	private static final long serialVersionUID = 9L;

	/**
	 * Return value a alpha % of interval [start; end]
	 *
	 * @param start:
	 *            start of interval
	 * @param end:
	 *            end of interval
	 * @param alpha:
	 *            percentage (0 to 1)
	 * @return value alpha% of interval [start; end]
	 */
	private static double linearInterp(double start, double end, double alpha) {
		return (end - start) * alpha + start;
	}

	/**
	 * Calculate a alpha and return it (alpha^power)% or 1-(1-alpha)^power% of
	 * interval [start; end]
	 *
	 * @param start:
	 *            start of interval
	 * @param end:
	 *            end of interval
	 * @param nbCommands:
	 *            number of commands to generate
	 * @param commandRange:
	 *            range of the command that has to be generated
	 * @param power:
	 *            curve of risings values
	 * @param revert:
	 *            reversion of the curve rise (fast then slow or slow then fast)
	 * @return value
	 */
	private static double interp(double start, double end, int commandRange, double power, boolean revert,
			int nbCommands) {

		double alpha;
		if (nbCommands > 1)
			alpha = (double) commandRange / (double) (nbCommands - 1);
		else
			alpha = 0.5;

		if (revert)
			alpha = 1 - alpha;

		alpha = Math.pow(alpha, power);

		if (revert)
			alpha = 1 - alpha;

		return linearInterp(start, end, alpha);
	}

	/**
	 * Return the round by 10^-precision
	 *
	 * @param number:
	 *            the number
	 * @param precision:
	 *            number of decimals
	 * @return number rounded
	 */
	private static double round(double number, int precision) {

		int power = (int) Math.pow(10, precision);
		number *= power;
		return ((double) Math.round(number)) / power;
	}

	@Override
	public String toString() {
		return l.get("gui.interpolation.title");
	}

	/**
	 * Generate the strings
	 *
	 * @return ArrayList<String> strings list
	 */
	@Override
	public ArrayList<String> generate(Tuple t, Integer nbLoop) {
		//	  <start,  end,    power,  revert,  nbreDecimales, noExt>
		Tuple6<Double, Double, Double, Boolean, Integer, Boolean> tuple = Tuple.castTo(t, Tuple.newTuple(Double.class, Double.class, Double.class, Boolean.class, Integer.class, Boolean.class));
		ArrayList<String> commands = new ArrayList<>();

		int commandeD = 0;
		int commandeF = nbLoop - 1;
		int step = 1;

		if (tuple._6) {
			commandeD = 1;
			nbLoop = nbLoop * 2 + 1;
			commandeF = nbLoop - 1;
			step = 2;
		}

		for (int i = commandeD; i <= commandeF; i += step)
			if (tuple._5 == 0)
				commands.add(String.valueOf((int) Math.round(interp(tuple._1, tuple._2, i, tuple._3, tuple._4, nbLoop))));
			else
				commands.add(String.valueOf(round(interp(tuple._1, tuple._2, i, tuple._3, tuple._4, nbLoop), tuple._5)));

		return commands;
	}

	@Override
	public String getHelp() {
		String start = l.get("gui.interpolation.parameter.start");
		String end = l.get("gui.interpolation.parameter.end");
		String power = l.get("gui.interpolation.parameter.power");
		String round = l.get("gui.functionalities.decimale");
		String revert = l.get("gui.interpolation.parameter.revert");
		String noExtremes = l.get("gui.interpolation.parameter.noextremes");
		String trueS = l.get("gui.others.true");
		String falseS = l.get("gui.others.false");
		
		return 	l.get("gui.sequence.description")
				+ "\n"
				+ "\n" + l.get("gui.functionalities.description.parameters") + ":"
				+ "\n" + start + ": " + l.get("gui.interpolation.description.start")
				+ "\n" + end +": " + l.get("gui.interpolation.description.end")
				+ "\n" + round + ": " + l.get("gui.functionalities.description.decimale")
				+ "\n" + power + ": " + l.get("gui.interpolation.description.power")
				+ "\n" + revert + ": " + l.get("gui.interpolation.description.revert")
				+ "\n" + noExtremes + ":" + l.get("gui.interpolation.description.noextremes")
				+ "\n"
				+ "\n" + l.get("gui.functionalities.description.example") + ":"
				+ "\n 5 " + l.get("gui.commandlistgenerator.commands") + ": #Interpolation:example#"
				+ "\n " + start + ": 0; " + end + ": 10; " + power + ": 1;" + round + ": 0;" + revert + ": " + falseS + ";" + noExtremes + ": " + trueS +";"  
				+ "\n 1"
				+ "\n 3"
				+ "\n 5"
				+ "\n 7"
				+ "\n 9"
				+ "\n " + start + ": 0; " + end + ": 10; " + power + ": 1;" + round + ": 0;" + revert + ": " + falseS + ";" + noExtremes + ": " + falseS +";"  
				+ "\n 0"
				+ "\n 3"
				+ "\n 5"
				+ "\n 8"
				+ "\n 10";
	}

	@Override
	public String getDefaultName() {
		return "Interpolation";
	}
}
