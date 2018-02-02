package fr.gunivers.cmdlg.generators.math;

import fr.gunivers.cmdlg.api.PrimitiveGenerator;
import fr.gunivers.cmdlg.util.GeneratorType;
import java.util.ArrayList;
import java.util.Iterator;

public class InterpScoreGenerator extends PrimitiveGenerator {

	private GeneratorType type = GeneratorType.INTERPSCORE;
	
	/**Constructor
	 * 
	 * @param command: the command to generate
	 * @param args: arguments for this generator
	 */
    public InterpScoreGenerator(String[] command, Object... args) {
        super(command, args);
    }

    @Override
    public Iterator<String> generate() {
    	
    	ArrayList<String> commands = new ArrayList<>();

		int start = (int) getArgs()[0];
		int end = (int) getArgs()[1];
		double power = (double) getArgs()[2];  
		boolean revert = (boolean) getArgs()[3];
		String objective = (String) getArgs()[4];
		int nbCommands = (int) getArgs()[5];
		
		for(int i = 0; i < nbCommands; i++)
			commands.add("score_" + objective + "_min="
					+ (String.valueOf(Math.round(interp(start, end, nbCommands + 1, i, power, revert)) + ((i == 0) ? 0 : 1)))
					+ ",score_" + objective + "="
					+ (String.valueOf(Math.round(interp(start, end, nbCommands + 1, i + 1, power, revert)))));
        
        return commands.iterator();
    }

	/**
	* Return value a alpha% of interval [start; end]
	*@param start: start of interval
	*@param end: end interval
	*@param alpha: percentage (0 a 1)
	*@return value a alpha% of interval [start; end]
	*/
	public static double linearInterp(double start, double end, double alpha) {
		return (end-start)*alpha + start;
	}
	
	/**
	* Calculate a alpha and return value a (alpha^power)% or 1-(1-alpha)^power% of interval [start; end]
	*@param start:start de l'intervale
	*@param end:end de l'intervale
	*@param nbCommands:nombre de commands to generate
	*@param nbCommands:numero of command to generate
	*@param power: curve of rising values
	*@param revert: reversion of the curve rise (fast then slow or slow then fast)
	*@return value
	*/
	public static double interp(double start, double end, int nbCommands, int commandRange, double power, boolean revert) {
		
		double alpha;
		if(nbCommands > 1)
			alpha = (double) commandRange/ (double) (nbCommands-1);	
		else 
			alpha = 0.5;
		
		if(revert)
			alpha = 1-alpha;
		
		alpha = Math.pow(alpha, power);
		
		if(revert)
			alpha = 1-alpha;
		
		return linearInterp(start, end, alpha);
	}

    @Override
    public GeneratorType getType() {
        return type;
    }
    
}
