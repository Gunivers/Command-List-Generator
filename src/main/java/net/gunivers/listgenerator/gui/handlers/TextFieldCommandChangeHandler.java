package net.gunivers.listgenerator.gui.handlers;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import net.gunivers.listgenerator.gui.CommandListGeneratorController;
import net.gunivers.listgenerator.util.Tag;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFieldCommandChangeHandler implements EventHandler<KeyEvent> {

	private TextField commandTextField;

	public TextFieldCommandChangeHandler(TextField input) {
		this.commandTextField = input;
	}

	@Override
	public void handle(KeyEvent event) {
		String text = commandTextField.getText();

		ArrayList<String> currentTag = new ArrayList<String>();

		Pattern p = Pattern.compile("#\\w+#");
		Matcher m = p.matcher(text);

		while (m.find()) {
			if ((m.start() == 0 || (m.start() > 0 && text.charAt(m.start() - 1) != '\\')) && text.charAt(m.end() - 1) != '\\') {
				String tag = m.group().replaceAll("#", "");
				if (!Tag.tags.containsKey(tag))
					Tag.tags.put(tag, new Tag(tag));
				currentTag.add(tag);
			}
		}

		if (Tag.tags.size() > 0) {
			ArrayList<String> toRemove = new ArrayList<String>();
			loop: 
			for (String t : Tag.tags.keySet()) {
				for (String ta : currentTag)
					if (t.equals(ta))
						continue loop;
				toRemove.add(t);
			}

			for (String t : toRemove)
				Tag.tags.remove(t);

			CommandListGeneratorController.CONTROLLER.checksTag(Tag.tags.keySet());
		}
	}
}
