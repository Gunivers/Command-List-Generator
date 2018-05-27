package net.gunivers.commandlistgenerator.gui.handlers.list;

import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.ListView;

public class SyncListHandler<E extends Parent>
{
    private ListView<? super E> listView1;
    private ListView<? super E> listView2;

    public SyncListHandler(ListView<? super E> listView1, ListView<? super E> listView2)
    {
        this.listView1 = listView1;
        this.listView2 = listView2;

        if (listView1.getSelectionModel().getSelectedItems().size() <= 0 || listView2.getSelectionModel().getSelectedItems().size() <= 0)
        {
            listView1.getSelectionModel().clearAndSelect(0);
            listView2.getSelectionModel().clearAndSelect(0);
        }

        listView1.setOnMousePressed(event -> sync(listView1.getSelectionModel().getSelectedIndex()));

        listView2.setOnMousePressed(event -> sync(listView2.getSelectionModel().getSelectedIndex()));
    }

    /**
     * This method sync the two ListView.
     *
     * @param index is the index of list view.
     */
    public void sync(int index)
    {
        if (listView1.getItems().size() >= index) listView1.getSelectionModel().clearAndSelect(index);

        if (listView2.getItems().size() >= index) listView2.getSelectionModel().clearAndSelect(index);
    }

    public ListView<? super E> getListViewOne()
    {
        return listView1;
    }

    public ListView<? super E> getListViewTwo()
    {
        return listView2;
    }

    public ObservableList getObjectInList(ListNumber number)
    {
        return number == ListNumber.ONE ? listView1.getItems() : listView2.getItems();
    }

    /**
     * get list view by number
     *
     * @param listNumber
     * @return
     */
    public ListView<? super E> getListView(ListNumber listNumber)
    {
        return listNumber == ListNumber.ONE ? getListViewOne() : getListViewTwo();
    }

    /**
     * Put in the custom javafx ListView and select the index
     *
     * @param listNumber
     * @param value
     * @param index
     */
    public void putInAndSelect(ListNumber listNumber, E value, int index)
    {
        this.putIn(listNumber, value, index);
        getListView(listNumber).getSelectionModel().clearAndSelect(index);
    }

    /**
     * Put in the custom javafx ListView
     *
     * @param listNumber
     * @param value
     * @param index
     */
    public void putIn(ListNumber listNumber, E value, int index)
    {
        ListView<? super E> listView = getListView(listNumber);

        if (!listView.getItems().isEmpty() && listView.getItems().get(index) != null) listView.getItems().remove(index);

        listView.getItems().add(index, value);
    }

    public enum ListNumber
    {
        ONE, TWO
    }
}
