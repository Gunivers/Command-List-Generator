package net.mgdk.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MGDK
{

    private static File APP_LOC = null;             //Value for the main directory.
    private static File PLUGIN_LOC = null;          //Value for the plugin directory.

    private static List<File> FILES_IN_PLUGIN = new ArrayList<>();


    public static void main(String[] args)
    {
        /**
         * Init the main directory.
         */
        initMainDirectory();

        /**
         * Init the plugin directory.
         */
        initPluginDirectory();
    }

    /**
     * Basic method to create the main directory
     */
    private static void initMainDirectory()
    {
        //get the user home directory
        String path = System.getProperty("user.home");

        //if the variable is a directory or a file.
        if (path.endsWith(File.separator))
            path = path + "minecraft-gdk/";
        else path = path + "/minecraft-gdk/";

        APP_LOC = new File(path);

        if (!APP_LOC.exists())
        {
            boolean mkdir = APP_LOC.mkdir();
            if (!mkdir)
                throw new NullPointerException("Incorrect value for: " + APP_LOC);
        }
    }

    /**
     * Basic method to create the plugin directory
     */
    private static void initPluginDirectory()
    {
        PLUGIN_LOC = new File(APP_LOC, "plugins");

        if (!PLUGIN_LOC.exists())
        {
            boolean mkdir = PLUGIN_LOC.mkdir();

            if (!mkdir)
                throw new NullPointerException("Incorrect value for: " + PLUGIN_LOC);
        }
    }

    /**
     * @return To the main directory.
     */
    public static File getMainDirectory()
    {
        return APP_LOC;
    }
}
