package net.gunivers.listgenerator.gui.handlers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import net.gunivers.listgenerator.util.Tag;

public class ButtonGenerateHandler implements EventHandler<ActionEvent> {
	
	
	//TODO Output of length's box
	public int maxSize;
	
	//TODO Input
	public String mold;
	
	@SuppressWarnings("unchecked")
	@Override
	public void handle(ActionEvent event) {
		HashMap<String, ArrayList<String>> replaceTag = new HashMap<String, ArrayList<String>>();

		for (Tag<?> t : Tag.getTags()) {
			try {
				ArrayList<String> list = (ArrayList<String>) t.getType().getMethod().invoke(t.getType(), t.getParameters());
				if (list.size() < maxSize)
					maxSize = list.size();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		ArrayList<String> commands = new ArrayList<String>();

		for (int i = 0; i < maxSize; i++) {
			String command = mold;
			for (Entry<String, ArrayList<String>> entry : replaceTag.entrySet()) {
				command.replace(entry.getKey(), entry.getValue().get(i));
			}
			commands.add(command);
		}

		//TODO Disp in output
		//return commands;
	}
}
