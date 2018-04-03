package net.gunivers.listgenerator.functionality.maths;

import java.util.ArrayList;

import net.gunivers.listgenerator.util.Call;
import net.gunivers.listgenerator.util.Functionality;

public class ScoreInterpolation extends Functionality {

	@Override
	public String toString() {
		return "ScoreInterpolation";
	}
	
    /**
     * Generate the commands
     *
     * @return ArrayList<String> commands list
     */
    @Call
    public ArrayList<String> scoreInterpolation(double start, double end, double power, boolean revert, String objective, int nbCommands)
    {

        ArrayList<String> commands = new ArrayList<>();

        for (int i = 0; i < nbCommands; i++)
            commands.add("score_" + objective + "_min="
                    + (String.valueOf(Math.round(interp(start, end, nbCommands + 1, i, power, revert)) + ((i == 0) ? 0 : 1)))
                    + ",score_" + objective + "="
                    + (String.valueOf(Math.round(interp(start, end, nbCommands + 1, i + 1, power, revert)))));

        return commands;
        
    }
	
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
    private static double interp(double start, double end, int nbCommands, int commandRange, double power, boolean revert)
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
	
}
