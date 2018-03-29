package plugin;

import net.mgdk.plugin.core.Plugin;


public class TestPlugin extends Plugin
{

    @Override
    public String name()
    {
        return null;
    }

    @Override
    public String[] createdBy()
    {
        return new String[0];
    }

    @Override
    public double version()
    {
        return 0;
    }

    @Override
    public String[] description()
    {
        return new String[0];
    }

    @Override
    public String updateURL()
    {
        return null;
    }

    @Override
    public String image()
    {
        return null;
    }

    @Start
    public void start()
    {
    }

    @Load
    public void load()
    {
    }

    @Quit
    public void quit()
    {
    }
}
