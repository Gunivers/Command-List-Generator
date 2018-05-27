package net.gunivers.core.language;

import com.sun.istack.internal.Nullable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Language
{
	//			   Key	   Value
	public HashMap<String, String> stringToComponent = new HashMap<>();

	//Default locale
	private Locale locale;

	public static Language defaultLanguage;

	//Link Locale to Language
	private static HashMap<Locale, Language> localeToLanguage = new HashMap<>();

	//check is already init
	private static boolean isInit = false;

	/**
	 * Simple constructor
	 *
	 * @param locale
	 */
	private Language(Locale locale)
	{
		this.locale = locale;
	}

	/**
	 * @param k is key
	 * @return a boolean is key exist
	 */
	public boolean has(String k)
	{
		return stringToComponent.containsKey(k);
	}

	/**
	 * @param k is key
	 * @return to the registered text
	 */
	public String get(String k)
	{
		if (has(k))
		{
			return stringToComponent.get(k);
		}
		String value = (this != defaultLanguage) ? defaultLanguage.get(k) : null;
		if (isNullOrEmpty(value))
			return k;
		else
			return value;
	}

	public void registerValue(String key, String value)
	{
		this.stringToComponent.put(key, value);
	}

	/**
	 * This method return to language is local is null return to the default language !
	 *
	 * @param locale the key of language
	 * @return a language
	 */
	public static Language getLanguage(Locale locale)
	{
		//Init if language is not init
		if (!isInit)
		{
			init();
		}

		if (localeToLanguage.containsKey(locale))
			return localeToLanguage.get(locale);
		return defaultLanguage;
	}

	/**
	 * @return To the language name
	 */
	public String getLanguageName()
	{
		return locale.getName();
	}

	private static void init()
	{
		isInit = true;
		try
		{
			for (Locale locale : Locale.values())
			{
				Language language = new Language(locale);
				String path = "/lang/" + locale.getCountryCode() + "_" + locale.getCountryCode().toUpperCase() + ".lang".trim();
				InputStream input = Language.class.getResourceAsStream(path);

				if (input == null)
				{
					System.out.println("Cannot register the language " + locale.getCountryCode().toUpperCase());
					continue;
				}

				BufferedReader reader = new BufferedReader(new InputStreamReader(input));
				String line;

				while ((line = reader.readLine()) != null)
				{
					//Check is line is blank or is a comment
					if (isNullOrEmpty(line) || line.charAt(0) == '#')
					{
						continue;
					}

					String[] args = line.split("=");
					String k = args[0].trim();
					String value = args[1].trim();

					if (language.has(k))
					{
						System.out.println("Language: The key: " + k + " has been already registered for language: " + language.getLanguageName());
					} else
					{
						language.registerValue(k, value);
					}
				}

				reader.close();
				localeToLanguage.put(locale, language);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		Language.defaultLanguage = Language.getLanguage(Locale.ENGLISH);
	}

	static boolean isNullOrEmpty(@Nullable String string)
	{
		return string == null || string.isEmpty();
	}
}
