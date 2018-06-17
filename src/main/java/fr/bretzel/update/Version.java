package fr.bretzel.update;

import java.util.regex.Pattern;

public class Version
{
    private int minor = 0, major = 0, debug = 0;
    private String patch;
    private UpdateChanel chanel = UpdateChanel.RELEASE;
    private String[] changelog = new String[]{};

    public Version(int major, int minor, int debug, UpdateChanel chanel)
    {
        this.major = major;
        this.minor = minor;
        this.debug = debug;
        this.chanel = chanel;
    }

    public Version(int major, int minor, int debug)
    {
        this.major = major;
        this.minor = minor;
        this.debug = debug;
    }

    public Version(String version)
    {
        String[] args = version.trim().split(Pattern.quote("."));

        this.major = Integer.valueOf(args[0]);
        this.minor = Integer.valueOf(args[1]);
        this.debug = Integer.valueOf(args[2]);

        //if args is > 4 for adding a new version type example: 0.0.0.release
        if (args.length >= 4)
            this.chanel = args[3].equalsIgnoreCase("release") ? UpdateChanel.RELEASE : UpdateChanel.SNAPSHOT;
    }


    /**
     *
     * @return to the update chanel of version
     */
    public UpdateChanel getChanel()
    {
        return chanel;
    }

    /**
     *
     * @param chanel set the update chanel
     */
    public void setChanel(UpdateChanel chanel)
    {
        this.chanel = chanel;
    }

    /**
     *
     * @param patch set the patch link
     */
    public void setPatch(String patch)
    {
        this.patch = patch;
    }

    /**
     *
     * @return get the patch link of the version
     */
    public String getPatch()
    {
        return patch;
    }

    /**
     * @return return to the minor version example: 0.0.0
     * ^
     * That is the debug version
     */
    public int getDebug()
    {
        return debug;
    }

    /**
     * @return return to the minor version example: 0.0.0
     * ^
     * That is the minor version
     */
    public int getMajor()
    {
        return major;
    }

    /**
     * @return return to the minor version example: 0.0.0
     * ^
     * That is the major version
     */
    public int getMinor()
    {
        return minor;
    }

    /**
     * @return To the changelog of the version if version is online only
     */
    public String[] getChangelog()
    {
        return changelog;
    }

    //Set the new changelog
    public void setChangelog(String[] changelog)
    {
        this.changelog = changelog;
    }

    //Update the version, for the moment just replace the changelog
    public void updateVersion(Version version)
    {
        setChangelog(version.getChangelog());
    }

    @Override
    public String toString()
    {
        return "major: " + getMajor() + ", minor: " + getMinor() + ", debug: " + getDebug();
    }

    /**
     *
     * @return To a simple version display
     */
    public String toSimpleVersion()
    {
        return getMajor() + "." + getMinor() + "." + getDebug();
    }
}
