package net.gunivers.listgenerator.util;

import net.gunivers.listgenerator.util.value.IValue;

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
    private IValue[] parameters;
    private String id;

    /**
     * Constructor of Tag
     *
     * @param id the id of the tag
     */
    public Tag(String id)
    {
        this.id = id;
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
     * @param type the type of the tag
     */
    public void setType(Functionality type)
    {
        this.type = type;
    }

    /**
     * @return the parameters of the tag
     */
    public IValue[] getParameters()
    {
        return parameters;
    }

    /**
     * @param parameters the parameters of the tag
     */
    public void setParameters(IValue[] parameters)
    {
        this.parameters = parameters;
    }
}
