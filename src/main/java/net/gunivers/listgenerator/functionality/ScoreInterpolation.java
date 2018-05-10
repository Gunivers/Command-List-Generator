package net.gunivers.listgenerator.functionality;

import net.gunivers.listgenerator.util.Call;
import net.gunivers.listgenerator.util.Functionality;

import java.util.ArrayList;

public class ScoreInterpolation extends Functionality
{

	/**
	 * Return value a alpha % of interval [start; end]
	 *
	 * @param start: start of interval
	 * @param end:   end of interval
	 * @param alpha: percentage (0 to 1)
	 * @return value alpha% of interval [start; end]
	 */
	private static double linearInterp(double start, double end, double alpha)
	{
		return (end - start) * alpha + start;
	}

	/**
	 * Calculate a alpha and return it (alpha^power)% or 1-(1-alpha)^power% of interval [start; end]
	 *
	 * @param start:        start of interval
	 * @param end:          end of interval
	 * @param nbCommands:   number of commands to generate
	 * @param commandRange: range of the command that has to be generated
	 * @param power:        curve of risings values
	 * @param revert:       reversion of the curve rise (fast then slow or slow then fast)
	 * @return value
	 */
	private static double interp(double start, double end, int commandRange, double power, boolean revert, int nbCommands)
	{

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
	public String toString()
	{
		return "ScoreInterpolation";
	}

	/**
	 * Generate the strings
	 *
	 * @return ArrayList<String> strings list
	 */
	@Call
	public ArrayList<String> scoreInterpolation(double start, double end, double power, boolean revert, int nbCommands)
	{

		ArrayList<String> commands = new ArrayList<>();

		for (int i = 0; i < nbCommands; i++)
			commands.add(
					(String.valueOf(Math.round(interp(start, end, i, power, revert, nbCommands + 1)) + ((i == 0) ? 0 : 1)))
							+ ".."
							+ (String.valueOf(Math.round(interp(start, end, i + 1, power, revert, nbCommands + 1)))));

		return commands;

	}

	@Override
	public ArrayList<Object> callParameterOverlay()
	{
		// TODO
		return null;
	}
}
