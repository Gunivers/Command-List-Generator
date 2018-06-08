package net.gunivers.core.utils;

public class SystemOS
{
    public static String getOsName()
    {
        return System.getProperty("os.name");
    }

    public static boolean isWindows()
    {
        return (getOsName().indexOf("win") >= 0);
    }

    public static boolean isMac()
    {
        return (getOsName().indexOf("mac") >= 0 || (getOsName().indexOf("darwin") >= 0));
    }

    public static boolean isUnix()
    {
        return (getOsName().indexOf("nix") >= 0 || getOsName().indexOf("nux") >= 0 || getOsName().indexOf("aix") > 0 );
    }

    public static boolean isSolaris()
    {
        return (getOsName().indexOf("sunos") >= 0);
    }

    /**
     * @return the version of OS
     */
    public static String getOsVersion()
    {
        return System.getProperty("os.version");
    }

    /**
     * @return get the architecture of OS
     */
    public static String getArch()
    {
        return System.getProperty("os.arch");
    }
}
