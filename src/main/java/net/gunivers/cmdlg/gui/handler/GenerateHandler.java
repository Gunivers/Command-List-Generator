package net.gunivers.cmdlg.gui.handler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public class GenerateHandler implements EventHandler<ActionEvent>
{
	private TextField command_textfield;

	public GenerateHandler(TextField command_textfield)
	{
		this.command_textfield = command_textfield;
	}

	@Override
	public void handle(ActionEvent event)
	{

	}
}
