package net.gunivers.cmdlg.gui;

import java.net.URL;

public enum Theme
{
	JAVA_DEFAULT("Java Default", null),
	GUNIVERS("Gunivers", Theme.class.getResource("/css/Gunivers.css")),
	CMDLG("CMDLG Default", Theme.class.getResource("/css/CMDLG.css"));

	private URL CSS_URL;
	private String name;

	Theme(String name, URL url)
	{
		this.CSS_URL = url;
		this.name = name;
	}

	public static Theme getThemeByName(String name)
	{
		for (Theme t : values())
		{
			if (t.getName().equalsIgnoreCase(name))
				return t;
		}
		return null;
	}

	public URL getCssUrl()
	{
		return CSS_URL;
	}

	public String getName()
	{
		return name;
	}
}
