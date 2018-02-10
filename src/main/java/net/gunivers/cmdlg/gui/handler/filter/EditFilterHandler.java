package net.gunivers.cmdlg.gui.handler.filter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class EditFilterHandler implements EventHandler<ActionEvent>
{
	private ListView<Label> filter_list;

	public EditFilterHandler(ListView<Label> listView) {
		this.filter_list = listView;
	}

	@Override
	public void handle(ActionEvent event)
	{

	}
}
