package net.gunivers.commandlistgenerator.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class FileIO {

	/**
	 * 
	 * @param data
	 *            a String to save
	 * @param path
	 * @throws FileNotFoundException
	 */
	public static void save(String data, String path) throws FileNotFoundException, IOException
	{
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8));
		out.write(data);
		out.close();
	}

	/**
	 * 
	 * @param path
	 * @return data a String read
	 * @throws IOException
	 */
	public static String get(String path) throws FileNotFoundException, IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
		
		String str;
		String result = "";
		
		while ((str = in.readLine()) != null) {
		    result += str + '\n';
		}
		 result = result.substring(0, result.length() - 1);   
		
        in.close();

		return result;
	}
}
