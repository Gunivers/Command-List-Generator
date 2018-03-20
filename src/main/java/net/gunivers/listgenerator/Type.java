package net.gunivers.listgenerator;

import java.lang.reflect.Method;
import net.gunivers.listgenerator.functionality.*;

/**
 * @author theogiraudet
 * This enum represent the possible types for the tag with their name and their linked method
 */
public enum Type {
	
	INCREMENTATION("Incrémentation", Incrementation.getIncremente());
	
	private String name;
	private Method m;
	
	
	Type(String name, Method m) {
		this.name = name;
		this.m = m;
	}

	/**
	 * @return the name of the functionality
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the method linked to this functionality
	 */
	public Method getMethod() {
		return m;
	}
	
}
