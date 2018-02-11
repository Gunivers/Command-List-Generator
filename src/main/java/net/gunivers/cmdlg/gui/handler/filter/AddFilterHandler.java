package net.gunivers.cmdlg.gui.handler.filter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import net.gunivers.cmdlg.gui.Dialog;

import java.io.IOException;


public class AddFilterHandler implements EventHandler<ActionEvent>
{
	private ListView<Label> filter_list;

	public AddFilterHandler(ListView<Label> listView) {
		this.filter_list = listView;
	}

	@Override
	public void handle(ActionEvent event)
	{
		Dialog dialog = new Dialog();
		try
		{
			dialog.show();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
