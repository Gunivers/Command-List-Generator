package net.gunivers.cmdlg.gui.handler.filter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class RemoveFilterHandler implements EventHandler<ActionEvent>
{
    private ListView<Label> filter_list;

    public RemoveFilterHandler(ListView<Label> listView)
    {
        this.filter_list = listView;
    }

    @Override
    public void handle(ActionEvent event)
    {
        Label selected_label = filter_list.getSelectionModel().getSelectedItems().get(0);
        if (selected_label != null)
            filter_list.getItems().remove(selected_label);
    }
}
