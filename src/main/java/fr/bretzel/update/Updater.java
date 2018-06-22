package fr.bretzel.update;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Updater
{
    //Name of the updater
    private String projectName;
    //Update URL, always end by .json
    private String url;
    //The home page of the update
    private String home_page;
    //List of version
    private ArrayList<Version> versions = new ArrayList<>();
    //Last version
    private Version last_snapshot;
    //Last last_release
    private Version last_release;
    //Current version of the updater
    private Version current;
    //All json from the URL
    private JSONObject jsonObject;
    //boolean if is not online.
    private boolean onLine = true;

    public Updater(String url, String projectName, Version current)
    {
        this.current = current;
        this.url = url;

        this.projectName = projectName;
    }

    /**
     * @return To the last snapshot version !
     */
    public Version getLastSnapshot()
    {
        return last_snapshot;
    }

    /**
     * @return to the last Release version
     */
    public Version getLastRelease()
    {
        return last_release;
    }

    private void setLastRelease(Version last_release)
    {
        this.last_release = last_release;
    }

    private void setLastSnapshot(Version last_snapshot)
    {
        this.last_snapshot = last_snapshot;
    }

    /**
     * @return to the current version of the program
     */
    public Version getCurrentVersion()
    {
        return current;
    }

    /**
     * @return To the project name
     */
    public String getProjectName()
    {
        return projectName;
    }

    /**
     * @return of all json
     */
    public JSONObject getJsonObject()
    {
        return jsonObject;
    }

    /**
     * @return Return true if you are online and return false if you are offline
     */
    public boolean isOnLine()
    {
        return onLine;
    }

    /**
     * @param onLine set onLine or Offline
     */
    public void setOnLine(boolean onLine)
    {
        this.onLine = onLine;
    }

    /**
     * @param chanel The chanel if you want get all version
     * @return To all version of your choice
     */
    public Set<Version> getVersions(UpdateChanel chanel)
    {
        ArrayList<Version> list = new ArrayList<>();
        for (Version version : getVersions())
        {
            if (version.getChanel() == chanel) list.add(version);
        }

        return new HashSet<>(list);
    }

    /**
     * @return To all version
     */
    public Set<Version> getVersions()
    {
        return new HashSet<>(versions);
    }

    /**
     * @return to the update url link
     */
    public String getUpdateUrl()
    {
        return url;
    }

    /**
     * @param chanel The chanel of your choice !
     * @return a boolean if a new version is present !
     */
    public boolean hasNewVersion(UpdateChanel chanel)
    {
        if (!isOnLine()) return false;

        if (last_release == null || last_snapshot == null) return false;

        return chanel == UpdateChanel.RELEASE ? getLastRelease().getMajor() > getCurrentVersion().getMajor() || getLastRelease().getMinor() > getCurrentVersion().getMinor() || getLastRelease().getDebug() > getCurrentVersion().getDebug() : getLastSnapshot().getMajor() > getCurrentVersion().getMajor() || getLastSnapshot().getMinor() > getCurrentVersion().getMinor() || getLastSnapshot().getDebug() > getCurrentVersion().getDebug();
    }

    /**
     * Run the runnable if a new version is available.
     * @param chanel The chanel of your choice !
     * @param runnable the action if you want execute
     */
    public void hasNewVersion(UpdateChanel chanel, Runnable runnable)
    {
        if (hasNewVersion(chanel))
            new Thread(() -> runnable.run()).start();
    }

    /**
     * @param version String usage: 0.0.0
     * @return Check version exist or not exist
     */
    public boolean containsVersion(String version)
    {
        for (Version v : versions)
            if (v.toSimpleVersion().equalsIgnoreCase(version)) return true;
        return false;
    }

    /**
     * @param version
     * @return Check version exist or no
     */
    public boolean containsVersion(Version version)
    {
        for (Version v : versions)
            if (v.toString().equalsIgnoreCase(version.toString())) return true;
        return false;
    }

    /**
     * @param version is the new version to add in, if the version already exist,
     */
    private void addVersion(Version version)
    {
        if (containsVersion(version)) for (Version v : versions)
        {
            if (v.toString().equalsIgnoreCase(version.toString()))
            {
                v.updateVersion(version);
                return;
            }
        }

        if (!containsVersion(version))
        {
            versions.add(version);
        }
    }

    /**
     * Simple init, please use that at the start
     */
    public void init()
    {
        if (isInMainThread())
            System.out.println("WARING PLEASE DON'T USE UPDATER IN THE MAIN THREAD, THAT CAUSE A LAGGING AND FREEZING ISSUE !");

        try
        {
            jsonObject = loadFromURL(url);
            home_page = (String) jsonObject.get("home_page");

            try
            {
                new URL(home_page);
            } catch (MalformedURLException e)
            {
                System.out.println("Incorrect home page url for: " + getProjectName() + " updater !");
                this.home_page = new String(new byte[]{104, 116, 116, 112, 58, 47, 47, 103, 105, 102, 100, 97, 110, 99, 101, 112, 97, 114, 116, 121, 46, 103, 105, 112, 104, 121, 46, 99, 111, 109, 47});
            }
        } catch (ConnectException e)
        {
            setOnLine(false);
            System.out.println("Cant connect to: " + getUpdateUrl() + " error: " + e.getMessage());
        } catch (IOException | ParseException e)
        {
            e.printStackTrace();
        }

        if (isOnLine())
        {
            updateListVersion();
            checkLastVersion();
        }
    }

    /**
     * set the Last version for Release chanel and Snapshot chanel
     */
    private void checkLastVersion()
    {
        if (!isOnLine()) return;

        Version lastRelease = new Version(-0x7fffffff, -0x7fffffff, -0x7fffffff);
        Version lastSnapshot = new Version(-0x7fffffff, -0x7fffffff, 0x7fffffff);

        for (Version version : getVersions())
        {
            if (version.getChanel() == UpdateChanel.RELEASE)
            {
                if (version.getMajor() > lastRelease.getMajor() || version.getMinor() > lastRelease.getMinor() || version.getDebug() > lastRelease.getDebug())
                {
                    lastRelease = version;
                }
            } else
            {
                if (version.getMajor() > lastSnapshot.getMajor() || version.getMinor() > lastSnapshot.getMinor() || version.getDebug() > lastSnapshot.getDebug())
                {
                    lastSnapshot = version;
                }
            }
        }

        setLastRelease(lastRelease);
        setLastSnapshot(lastSnapshot);
    }

    /**
     * Get all version ans check if version is correct, if version is already exist, just sync the two version
     */
    private void updateListVersion()
    {
        if (!isOnLine()) return;

        int versionSize = -1;
        JSONArray array = new JSONArray();

        if (getJsonObject().containsKey("versions") && getJsonObject().get("versions") instanceof JSONArray)
        {
            array = (JSONArray) getJsonObject().get("versions");
            versionSize = array.size();
        }

        if (versionSize <= 0)
        {
            System.out.println("No version has been found !");
            return;
        }

        for (int index = 0; array.size() > index; index++)
        {
            JSONObject jsonVersion = (JSONObject) array.get(index);

            if (jsonVersion.containsKey("version"))
            {
                Version version = new Version((String) jsonVersion.get("version"));
                String[] changelog = new String[0];

                if (!jsonVersion.containsKey("chanel")) version.setChanel(UpdateChanel.RELEASE);
                else
                    version.setChanel(((String) jsonVersion.get("chanel")).equalsIgnoreCase("release") ? UpdateChanel.RELEASE : UpdateChanel.SNAPSHOT);

                if (!jsonVersion.containsKey("patch"))
                {
                    System.out.println("Cant not be regitered version: " + jsonVersion + " No patch url found !");
                    continue;
                } else
                {
                    version.setPatch((String) jsonVersion.get("patch"));
                }
                if (jsonVersion.containsKey("changelog") && jsonVersion.get("changelog") instanceof JSONArray)
                {
                    JSONArray jsonChangelog = (JSONArray) jsonVersion.get("changelog");
                    changelog = new String[jsonChangelog.size()];

                    for (int i = 0; jsonChangelog.size() > i; i++)
                    {
                        changelog[i] = (String) jsonChangelog.get(i);
                    }
                }
                version.setChangelog(changelog);
                addVersion(version);
            }
        }
    }

    /**
     * Clear all version and rescan the update link
     */
    public void reloadVersion()
    {
        if (!isOnLine()) return;

        if (isInMainThread())
            System.out.println("WARING PLEASE DON'T USE UPDATER IN THE MAIN THREAD, THAT CAUSE A LAGGING AND FREEZING ISSUE !");

        versions.clear();
        updateListVersion();
        checkLastVersion();
    }

    private boolean isInMainThread()
    {
        Thread current = Thread.currentThread();
        return current.getId() == 1 || current.getId() == 25 || current.getId() == 23;
    }

    private JSONObject loadFromURL(String url) throws IOException, ParseException
    {
        return (JSONObject) new JSONParser().parse(new BufferedReader(new InputStreamReader(new URL(url).openStream(), Charset.forName("UTF-8"))));
    }
}
