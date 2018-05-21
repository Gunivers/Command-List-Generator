package net.gunivers.commandlistgenerator.util;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Save {

	/**
	 * 
	 * @param data
	 *            a String to save
	 * @param path
	 * @throws FileNotFoundException
	 */
	public static void save(String data, String path) throws FileNotFoundException, IOException
	{
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(path));

		dos.writeChars(data);

		dos.close();
	}

	/**
	 * 
	 * @param path
	 * @return data a String read
	 * @throws IOException
	 */
	public static String get(String path) throws FileNotFoundException, IOException
	{
		BufferedInputStream bis = new BufferedInputStream(new DataInputStream(new FileInputStream(path)));
		byte[] buffer = new byte[8];
		StringBuilder sb = new StringBuilder();

		while (bis.read(buffer) != -1)
		{
			for (byte b : buffer)
			{
				sb.append((char) b);
			}
		}

		bis.close();

		return sb.toString();
	}
}
