package net.gunivers.listgenerator.functionality;

import net.gunivers.listgenerator.util.Call;
import net.gunivers.listgenerator.util.Functionality;

import java.util.ArrayList;

public class Interpolation extends Functionality
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

    /**
     * Return the round by 10^-precision
     *
     * @param number:    the number
     * @param precision: number of decimals
     * @return number rounded
     */
    private static double round(double number, int precision)
    {

        int power = (int) Math.pow(10, precision);
        number *= power;
        return ((double) Math.round(number)) / power;
    }

    @Override
    public String toString()
    {
        return "Interpolation";
    }

    /**
     * Generate the strings
     *
     * @return ArrayList<String> strings list
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

}
