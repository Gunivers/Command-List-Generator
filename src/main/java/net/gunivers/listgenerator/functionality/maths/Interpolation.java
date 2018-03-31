package net.gunivers.listgenerator.functionality.maths;

import java.util.ArrayList;

import net.gunivers.listgenerator.functionality.Functionality;
import net.gunivers.listgenerator.util.Call;

public class Interpolation extends Functionality {

	@Override
	public String toString() {
		return "Interpolation";
	}
	
    /**
     * Generate the commands
     *
     * @return ArrayList<String> commands list
     */
    @Call
    public ArrayList<String> interpolation(double start, double end, double power, boolean revert, int nbreDecimales, boolean noExt, int nbCommands)
    {

        ArrayList<String> commands = new ArrayList<>();

        int commandeD = 0;
        int commandeF = nbCommands - 1;
        int step = 1;

        if (noExt)
        {
            commandeD = 1;
            nbCommands = nbCommands * 2 + 1;
            commandeF = nbCommands - 1;
            step = 2;
        }

        for (int i = commandeD; i <= commandeF; i += step)
            if (nbreDecimales == 0)
                commands.add(String.valueOf((int) Math.round(interp(start, end, nbCommands, i, power, revert))));
            else
                commands.add(String.valueOf(round(interp(start, end, nbCommands, i, power, revert), nbreDecimales)));

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
    public static double linearInterp(double start, double end, double alpha)
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
    public static double interp(double start, double end, int nbCommands, int commandRange, double power, boolean revert)
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

    /**
     * Return the round by 10^-precision
     *
     * @param number:    the number
     * @param precision: number of decimals
     * @return number rounded
     */
    public static double round(double number, int precision)
    {

        int power = (int) Math.pow(10, precision);
        number *= power;
        return ((double) Math.round(number)) / power;
    }
	
}
