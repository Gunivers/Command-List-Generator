package fr.gunivers.cmdlg.generators.math;

import fr.gunivers.cmdlg.api.PrimitiveGenerator;
import fr.gunivers.cmdlg.util.GeneratorType;

import java.util.ArrayList;
import java.util.Iterator;

public class InterpGenerator extends PrimitiveGenerator {

	private GeneratorType type = GeneratorType.INTERP;
	
	/**Constructor
	 * 
	 * @param command: the command to generate
	 * @param args: arguments for this generator
	 */
    public InterpGenerator(String[] command, Object... args) {
        super(command, args);
    }

    /** Generate the commands
     * 
     * @return ArrayList<String> commands list
     */
    @Override
    public Iterator<String> generate() {
    	
        ArrayList<String> commands = new ArrayList<>();

		double start = (double) getArgs()[0];
		double end = (double) getArgs()[1];
		double power = (double) getArgs()[2];  
		boolean revert = (boolean) getArgs()[3];
		int nbreDecimales = (int) getArgs()[4];
		int nbCommands = (int) getArgs()[5];
		
		for(int i = 0; i < nbCommands; i++)
			commands.add(String.valueOf(round(interp(start, end, nbCommands, i, power, revert), nbreDecimales)));
        
        return commands.iterator();
    }
    					
	/**Return value a alpha % of interval [start; end]
	 * 
	 *@param start: start of interval
	 *@param end: end of interval
	 *@param alpha: percentage (0 to 1)
	 *@return value alpha% of interval [start; end]
	 */
	public static double linearInterp(double start, double end, double alpha) {
		return (end-start)*alpha + start;
	}
	
	/**
	 * Calculate a alpha and return it (alpha^power)% or 1-(1-alpha)^power% of interval [start; end]
	 * 
	 *@param start: start of interval
	 *@param end: end of interval
	 *@param nbCommands: number of commands to generate
	 *@param commandRange: range of the command that has to be generated
	 *@param power: curve of risings values
	 *@param revert: reversion of the curve rise (fast then slow or slow then fast)
	 *
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
	
	/**Return the round by 10^-precision
	 * 
	 *@param number: the number
	 *@param precision: number of decimals
	 *@return number rounded
	 */
	public static double round(double number, int precision) {
		
		int power = (int) Math.pow(10, precision);
		number *= power;
		return ((double) Math.round(number)) / power;
	}   

	/**Get type
	 * 
	 * @return type
	 */
    @Override
    public GeneratorType getType() {
        return type;
    }
    
}
