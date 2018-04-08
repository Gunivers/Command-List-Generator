package net.gunivers.listgenerator.gui.handlers.list;

import javafx.scene.control.ListView;

public class SyncListHandler
{
    private ListView listView1, listView2;

    public SyncListHandler(ListView listView1, ListView listView2)
    {
        this.listView1 = listView1;
        this.listView2 = listView2;
        if (listView1.getSelectionModel().getSelectedItems().size() > 0 || listView2.getSelectionModel().getSelectedItems().size() > 0)
        {
            listView1.getSelectionModel().clearAndSelect(0);
            listView2.getSelectionModel().clearAndSelect(0);
        }

        listView1.setOnMouseClicked(event -> {
            System.out.println(listView1.getSelectionModel().getSelectedIndex());
            sync(listView1.getSelectionModel().getSelectedIndex());
        });

        listView2.setOnMouseClicked(event -> {
            sync(listView2.getSelectionModel().getSelectedIndex());
        });
    }

    public void sync(int index)
    {
        listView1.getSelectionModel().clearAndSelect(index);
        listView2.getSelectionModel().clearAndSelect(index);
    }
}
