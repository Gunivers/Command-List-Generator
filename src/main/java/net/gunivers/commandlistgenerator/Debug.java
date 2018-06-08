package net.gunivers.commandlistgenerator;

public class Debug
{
    public static Boolean DEBUG = false;

    public static void initialize(String arg)
    {
        DEBUG = true;

        if (arg.split("=").length > 1)
        {
            DEBUG = Boolean.valueOf(arg.split("=")[1]);
        }

    }
}
