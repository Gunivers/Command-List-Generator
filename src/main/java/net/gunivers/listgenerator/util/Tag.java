package net.gunivers.listgenerator.util;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @param <T> the type of the parameter(s)
 * @author Oromis
 * This object represent a tag in the mold
 */
public class Tag
{

    public static HashMap<String, Tag> tags = new HashMap<String, Tag>();
    
    private Functionality type;
    private ArrayList<String> parameters;
    private String id;

    /**
     * Constructor of Tag
     * @param id         the id of the tag
     */
    public Tag(String id)
    {
        this.id = id;
    }

    /**
     * @param type the type of the tag
     */
    public void setType(Functionality type) {
		this.type = type;
	}

    /**
     * @param parameters the parameters of the tag
     */
	public void setParameters(ArrayList<String> parameters) {
		this.parameters = parameters;
	}

	/**
     * @return the id of the tag
     */
    public String getId()
    {
        return id;
    }

    /**
     * @return the type of the tag
     */
    public Functionality getType()
    {
        return type;
    }

    /**
     * @return the parameters of the tag
     */
    public ArrayList<String> getParameters()
    {
        return parameters;
    }
}
