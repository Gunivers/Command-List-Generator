package net.mgdk.plugin;

import net.mgdk.core.util.JSONUtil;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class PluginFinder
{
    //List of jar in directory
    private List<JarFile> files = new ArrayList<>();
    //Map to store the main class and name with the jar file
    private Map<JarFile, String[]> validPlugin = new HashMap<>();

    /**
     * @param directory the directory of plugin
     */
    public PluginFinder(File directory)
    {
        //if is a directory, for prevent a crash
        if (directory.isDirectory())
        {
            //List all jar
            for (File file : directory.listFiles())
            {
                if (file.getName().endsWith(".jar"))
                {
                    try
                    {
                        files.add(new JarFile(file, false));
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            //get all jar file and verify if jar has mgdk.json and if mgdk.json is valid
            for (JarFile file : files)
            {
                if (file.getEntry("mgdk.json") != null)
                {
                    ZipEntry entry = file.getEntry("mgdk.json");
                    try
                    {
                        JSONObject object = JSONUtil.loadFrom(file.getInputStream(entry));

                        String main, name;

                        if (object.containsKey("Main-Class"))

                        {
                            main = (String) object.get("Main-Class");
                        } else
                        {
                            System.out.println("Invalid mgdk.json in " + file.getName() + ", The Main-Class is not correct.");
                            continue;
                        }

                        if (object.containsKey("Name"))
                        {
                            name = (String) object.get("Name");
                        } else
                        {
                            System.out.println("Invalid mgdk.json in " + file.getName() + ", The Name is not correct.");
                            continue;
                        }

                        validPlugin.put(file, new String[]{main, name});
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                } else
                {
                    System.out.println("No mgdk.json found in " + file.getName());
                    continue;
                }
            }
        } else
        {
            System.out.println(directory.toString() + " is not a directory.");
        }
    }

    public List<JarFile> getAllJar()
    {
        return files;
    }

    public Collection<JarFile> getValidPlugin()
    {
        return validPlugin.keySet();
    }
}
