package net.gunivers.listgenerator.functionality;

import java.lang.reflect.Method;
import java.util.HashMap;

import net.gunivers.listgenerator.util.Call;

public abstract class Functionality {
	
	/**
	 * This Map stocks all functionalies
	 */
	private static HashMap<String, Functionality> functionalities = new HashMap<String, Functionality>();
	
	/**
	 * Save each instance of a functionality
	 */
	{
		functionalities.put(this.toString(), this);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public abstract String toString();
	
	public static HashMap<String, Functionality> getFunctionalities() {
		return functionalities;
	}
	
	/**
	 * @return the method to call to get the tag output
	 */
	public Method getMethod() {
		for(final Method method : this.getClass().getMethods())
			if(method.isAnnotationPresent(Call.class))
				return method;
		return null;
	}
	
}
