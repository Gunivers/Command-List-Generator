package net.gunivers.listgenerator;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javafx.fxml.FXML;
import net.gunivers.listgenerator.util.Tag;

/**
 * 
 * @author Oromis
 * Main class of this program
 */
public class CommandListGenerator {

	
	@FXML
	public int maxSize;
	
	@FXML
	public String mold;
	
	/**
	 * @return a list of commands based on the mold with all tag replaced by value
	 */
	@SuppressWarnings("unchecked")
	@FXML	
	public ArrayList<String> generateOutput() {
		
		HashMap<String, ArrayList<String>> replaceTag = new HashMap<String, ArrayList<String>>();
		
		for(Tag<?> t : Tag.getTags()) {
			try {
				ArrayList<String> list = (ArrayList<String>) t.getType().getMethod().invoke(t.getType(), t.getParameters());
				if(list.size() < maxSize) maxSize = list.size();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		ArrayList<String> commands = new ArrayList<String>();
		
		for(int i = 0; i < maxSize; i++) {
			String command = mold;
			for(Entry<String, ArrayList<String>> entry : replaceTag.entrySet()) {
				command.replace(entry.getKey(), entry.getValue().get(i));
			}
			commands.add(command);
		}
		
		return commands;
	}
}
