package net.gunivers.listgenerator.gui.handlers;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class CommandChangeHandler implements EventHandler<KeyEvent>
{
	
	private TextField commandTextField;
	
    public CommandChangeHandler(TextField input) {
    	this.commandTextField = input;
	}

	@Override
    public void handle(KeyEvent event)
    {
    	
    }
}
