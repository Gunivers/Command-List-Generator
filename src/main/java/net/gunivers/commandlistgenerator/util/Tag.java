package net.gunivers.commandlistgenerator.util;

import java.util.HashMap;

import net.gunivers.commandlistgenerator.functionality.Functionality;
import net.gunivers.core.language.tuple.Tuple;

/**
 * @author Oromis This object represent a tag in the mold
 */
public class Tag {

	public static HashMap<String, Tag> tags = new HashMap<>();
	public static final char tagDelimiter = '#';	
	private Functionality type;
	private String id;
	private Tuple parameters;

	/**
	 * Constructor of Tag
	 *
	 * @param id
	 *            the id of the tag
	 */
	public Tag(String id) {
		this.id = id;
	}

	/**
	 * @return the id of the tag
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the type of the tag
	 */
	public Functionality getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type of the tag
	 */
	public void setType(Functionality type) {
		this.type = type;
	}

	/**
	 * @return the parameters of the tag
	 */
	public Tuple getParameters() {
		return parameters;
	}

	public void setParameters(Tuple parameters) {
		this.parameters = parameters;
	}
}
