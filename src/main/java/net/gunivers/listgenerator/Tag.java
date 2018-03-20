package net.gunivers.listgenerator;

/**
 * 
 * @author theo
 * This object represent a tag that can be put in the mold 
 * @param <T> the type of the parameter(s)
 */
public class Tag<T> {
	
	private Type type;
	private T parameters;
	
	/**
	 * Constructor of Tag
	 * @param type the type of the tag
	 * @param parameters the parameters of the tag
	 */
	Tag(Type type, T parameters) {
		this.type = type;
		this.parameters = parameters;
	}

	/**
	 * @return the type of the tag
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @return the parameters of the tag
	 */
	public T getParameters() {
		return parameters;
	}
	
	

}
