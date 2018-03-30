package net.mgdk.core;

import net.mgdk.plugin.PluginFinder;
import net.mgdk.plugin.core.Plugin;

import java.io.File;

public class MGDK
{

    private static File APP_DIR = null;             //Value for the main directory.
    private static File PLUGIN_DIR = null;          //Value for the plugin directory.


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

        /**
         * Find jar plugin with PluginFiner
         */
        PluginFinder finder = findJarPlugin();


    }

    /**
     * Basic method to find all plugin
     */
    private static PluginFinder findJarPlugin()
    {
        if (PLUGIN_DIR != null && PLUGIN_DIR.listFiles().length > 0)
        {
            PluginFinder finder = new PluginFinder(PLUGIN_DIR);

            if (finder.getValidPlugin().size() <= 0)
            {
                System.out.println("No valid plugin found !");
                return null;
            }

            System.out.println("Find " + finder.getAllJar().size() + " jars, for " + finder.getValidPlugin().size() + " plugins.");

            return finder;

        } else
        {
            System.out.println("No plugin found.");
            return null;
        }
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

        APP_DIR = new File(path);

        if (!APP_DIR.exists())
        {
            boolean mkdir = APP_DIR.mkdir();
            if (!mkdir)
                throw new NullPointerException("Incorrect value for: " + APP_DIR);
        }
    }

    /**
     * Basic method to create the plugin directory
     */
    private static void initPluginDirectory()
    {
        PLUGIN_DIR = new File(APP_DIR, "plugins");

        if (!PLUGIN_DIR.exists())
        {
            boolean mkdir = PLUGIN_DIR.mkdir();

            if (!mkdir)
                throw new NullPointerException("Incorrect value for: " + PLUGIN_DIR);
        }
    }

    /**
     * @return To the main directory.
     */
    public static File getMainDirectory()
    {
        return APP_DIR;
    }
}
