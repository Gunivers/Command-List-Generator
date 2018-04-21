package net.gunivers.listgenerator.functionality;

import java.math.BigDecimal;
import java.util.ArrayList;

import net.gunivers.listgenerator.util.Calculator;
import net.gunivers.listgenerator.util.Call;
import net.gunivers.listgenerator.util.Functionality;

/**
 * @author Oromis
 * A function to increment at each loop
 */
public class Increment extends Functionality
{

    /**
     * @param initValue first value of the loop
     * @param incr      number to add at each loop
     * @param nbLoop    number of loop
     * @return an ArrayList<String> with all the value replacing the tag
     */
    @Call
    public ArrayList<String> generate(Double initValue, String operation, Integer nbLoop, int round, Class<? extends Number> type)
    {
        String end = "";

        if (type != null)
        {
            if (type.equals(Long.class))
                end = "L";

            else if (type.equals(Float.class))
                end = "F";

            else if (type.equals(Double.class))
                end = "D";

            else if (type.equals(Short.class))
                end = "s";

            else if (type.equals(Byte.class))
                end = "b";
            if (round == 0 && (end == "D" || end == "D"))
                end = ".0" + end;
        }


        ArrayList<String> save = new ArrayList<String>();

        BigDecimal bd = new BigDecimal(initValue);
        bd = bd.setScale(round, BigDecimal.ROUND_HALF_UP);
        save.add(bd.toString());

        for (int i = 0; i < nbLoop; i++)
        {

            Calculator calc = new Calculator(operation.replaceAll("[uU]", Double.toString(initValue)));
            initValue = calc.calculate();
            BigDecimal bd2 = new BigDecimal(initValue);
            bd2 = bd2.setScale(round, BigDecimal.ROUND_HALF_UP);
            save.add(bd2.toString() + end);
        }
        return save;
    }

    @Override
    public String toString()
    {
        return "Increment";
    }

    @Override
    public ArrayList<Object> callParameterOverlay()
    {
        // TODO
        return null;
    }
}
