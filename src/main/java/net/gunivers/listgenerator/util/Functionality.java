package net.gunivers.listgenerator.util;

import java.lang.reflect.Method;
import java.util.HashMap;


/**
 * @author Oromis
 * Abstract method representing a functionality of the Command List Generator
 */
public abstract class Functionality
{

    /**
     * This Map stocks all functionalities
     */
    private static HashMap<String, Functionality> functionalities = new HashMap<String, Functionality>();

    /**
     * Save each instance of a functionality
     */
    {
        functionalities.put(this.toString(), this);
    }

    /**
     * @return a HashMap of all available functionnalities
     */
    public static HashMap<String, Functionality> getFunctionalities()
    {
        return functionalities;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public abstract String toString();

    /**
     * @return the method to call to get the tag output
     */
    public Method getMethod()
    {
        for (final Method method : this.getClass().getMethods())
            if (method.isAnnotationPresent(Call.class))
                return method;
        return null;
    }
}
