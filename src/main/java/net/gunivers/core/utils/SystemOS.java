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
        return (getOsName().indexOf("nix") >= 0 || getOsName().indexOf("nux") >= 0 || getOsName().indexOf("aix") > 0);
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

    public static String getJavaVersion()
    {
        return System.getProperty("java.version");
    }


    public static String getJavaClassPath()
    {
        return System.getProperty("java.class.path");
    }

    public static String getJavaHome()
    {
        return System.getProperty("java.home");
    }

    public static String getJavaVendor()
    {
        return System.getProperty("java.vendor");
    }

    public static String getJavaVendorUrl()
    {
        return System.getProperty("java.vendor.url");
    }

    public static String getFileSeparator()
    {
        return System.getProperty("file.separator");
    }

    public static String getLineSeparator()
    {
        return System.getProperty("line.separator");
    }

    public static String getPathSeparator()
    {
        return System.getProperty("path.separator");
    }

    public static String getUserDir()
    {
        return System.getProperty("user.dir");
    }

    public static String getUserHome()
    {
        return System.getProperty("user.home");
    }

    public static String getUserName()
    {
        return System.getProperty("user.name");
    }
}
