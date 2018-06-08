package net.gunivers.commandlistgenerator.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class FileIO {

	/**
	 * 
	 * @param data
	 *            a String to save
	 * @param path
	 * @throws FileNotFoundException, IOException
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
	 * @throws FileNotFoundException, IOException
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
	
	/** <strong>Serialize<strong>
	 * This method will save a set in the gave path.
	 * 
	 * @param path
	 *            the file location
	 * @param command
	 *               the command
	 * @param maxCommand
	 *                  the max commands amount
	 * @param tags
	 *            the tags of the set
	 * @return success
	 *                a boolean
	 */
	public static boolean serialize(String path, String command, int maxCommand, Tag ... tags)
	{
		try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(path))))
		{
			oos.writeObject(command);
			oos.writeInt(maxCommand);
			oos.writeObject(tags);
			
			return true;
			
		} catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static Object[] deserialize(String path)
	{
		try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(path))))
		{
			String command = (String) ois.readObject();
			int maxCommand = ois.readInt();
			Tag[] tags = (Tag[]) ois.readObject();
			
			return new Object[] {command, maxCommand, tags};
			
		} catch (ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
