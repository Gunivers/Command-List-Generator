package net.gunivers.core.language;

import java.util.HashMap;

public enum Locale
{
	ENGLISH("en", "English"),
	FRENCH("fr", "Français");

	private String countryCode, name;
	private final static HashMap<String, Locale> hashMap = new HashMap<>();

	private Locale(String countryCode, String name)
	{
		this.countryCode = countryCode;
		this.name = name;
	}

	public String getCountryCode()
	{
		return countryCode;
	}

	public String getName()
	{
		return name;
	}

	public static Locale fromCountryCode(String s)
	{
		return hashMap.get(s);
	}

	static
	{
		for (Locale l : values())
		{
			hashMap.put(l.getCountryCode(), l);
		}
	}
}
