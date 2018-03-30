package net.mgdk.plugin;

import java.lang.reflect.Method;

public class PluginLoader
{
    private String clazz;
    private Method start, load, quit;

    public PluginLoader(String mainClazz)
    {
        this.clazz = mainClazz;
    }

    public Method getLoad()
    {
        return load;
    }

    public Method getQuit()
    {
        return quit;
    }

    public Method getStart()
    {
        return start;
    }

    public String getClazz()
    {
        return clazz;
    }
}
