package net.gunivers.commandlistgenerator.functionality;

import java.util.ArrayList;

import net.gunivers.core.language.tuple.Tuple;
import net.gunivers.core.language.tuple.Tuple4;

public class ScoreInterpolation extends Functionality {

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
	private static double interp(double start, double end, int commandRange, double power, boolean revert, int nbCommands) {

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

	@Override
	public String toString() {
		return "ScoreInterpolation";
	}

	/**
	 * Generate the strings
	 *
	 * @return ArrayList<String> strings list
	 */
	@Override
	public ArrayList<String> generate(Tuple t, Integer nbLoop) {
		//<start, end, power, revert>
		Tuple4<Double, Double, Double, Boolean> tuple = Tuple.castTo(t,Tuple.newTuple(Double.class, Double.class, Double.class, Boolean.class));

		ArrayList<String> commands = new ArrayList<>();

		for (int i = 0; i < nbLoop; i++)
			commands.add((String
					.valueOf(Math.round(interp(tuple._1, tuple._2, i, tuple._3, tuple._4, nbLoop + 1)) + ((i == 0) ? 0 : 1)))
					+ ".." + (String.valueOf(Math.round(interp(tuple._1, tuple._2, i + 1, tuple._3, tuple._4, nbLoop + 1)))));

		return commands;

	}

	@Override
	public String getHelp() {
		return "Ask KubbyDev to know how it works :/";
	}
}
