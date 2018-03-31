package net.gunivers.listgenerator.util;

import net.gunivers.listgenerator.functionality.Functionality;

/**
 * 
 * @author theo
 * This object represent a tag that can be put in the mold 
 * @param <T> the type of the parameter(s)
 */
public class Tag<T> {
	
	private Functionality type;
	private T parameters;
	
	/**
	 * Constructor of Tag
	 * @param type the type of the tag
	 * @param parameters the parameters of the tag
	 */
	public Tag(Functionality type, T parameters) {
		this.type = type;
		this.parameters = parameters;
	}

	/**
	 * @return the type of the tag
	 */
	public Functionality getType() {
		return type;
	}

	/**
	 * @return the parameters of the tag
	 */
	public T getParameters() {
		return parameters;
	}
}
