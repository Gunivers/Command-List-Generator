package net.gunivers.commandlistgenerator.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**<strong>FileIO</strong>
 * This class manage the input/output of this program with the files. Able to append down some String and Presets
 * @author A~Z
 * @author Oromis
 */
public class FileIO
{
	/**
	 * 
	 * @param path
	 *            String | the file's location
	 * @param data
	 *            String | to save
	 * @throws FileNotFoundException, IOException
	 */
	public static void save(String path, String data)
	{
		try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8)))
		{
			out.write(data);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**<strong>get</strong>
	 * 
	 * @param path
	 *            String | the file's location
	 * @return String
	 *               the data read, or null if got nothing
	 * @throws FileNotFoundException, IOException
	 */
	public static String get(String path)
	{
		String result = "";
		
		try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8)))
		{
			String str;
		
			while ((str = in.readLine()) != null)
			{
				result += str + '\n';
			}
			result = result.substring(0, result.length() - 1);
		} catch(IOException e)
		{
			e.printStackTrace();
		}

		return result.equals("") ? null : result;
	}
	
	/** <strong>serialize</strong>
	 * This method will save a set in the given path.
	 * 
	 * @param path
	 *            String | the file location
	 * @param command
	 *               String | the command
	 * @param maxCommand
	 *                  int | the max commands amount
	 * @param tags
	 *            Tag[] | the tags of the set
	 * @return boolean
	 *                the success of the serialization
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
	
	/**<strong>deserialize</strong>
	 * This method will get a set deserializing it from the given path file.
	 * @param path
	 *            String | the file location
	 * @return Object[3]
	 *                 the set
	 */
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
