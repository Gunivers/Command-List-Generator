package net.gunivers.listgenerator.util;

import java.util.ArrayList;

/**
 * @param <T> the type of the parameter(s)
 * @author Oromis
 * This object represent a tag in the mold
 */
public class Tag<T>
{

    private static ArrayList<Tag<?>> tags;
    
    private Functionality type;
    private T parameters;
    private String id;

    /**
     * Constructor of Tag
     *
     * @param type       the type of the tag
     * @param parameters the parameters of the tag
     * @param id         the id of the tag
     */
    public Tag(Functionality type, T parameters, String id)
    {
        this.type = type;
        this.parameters = parameters;
        this.id = id;
    }

    /**
     * @return the list of all tags in the mold
     */
    public static ArrayList<Tag<?>> getTags()
    {
        return tags;
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
    public T getParameters()
    {
        return parameters;
    }
}
