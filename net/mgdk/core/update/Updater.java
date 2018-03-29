package net.mgdk.core.update;

public class Updater
{
    private String updateURL = "";
    private String jsonValue = "";

    public Updater(String url)
    {
        this.updateURL = url;
    }

    public String getJsonValue()
    {
        return jsonValue;
    }

    public String getUpdateURL()
    {
        return updateURL;
    }
}
