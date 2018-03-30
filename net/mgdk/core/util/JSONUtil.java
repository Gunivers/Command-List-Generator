package net.mgdk.core.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.InputStream;
import java.util.Scanner;

public class JSONUtil
{
    //Utility to load a json from InputStream
    public static JSONObject loadFrom(InputStream inputStream)
    {
        try
        {
            return (JSONObject) new JSONParser().parse(convertStreamToString(inputStream));
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertStreamToString(InputStream is)
    {
        Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "{}";
    }
}
