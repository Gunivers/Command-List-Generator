package net.gunivers.listgenerator.gui.handlers;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSnackbar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import net.gunivers.listgenerator.gui.CommandListGeneratorController;
import net.gunivers.listgenerator.util.Functionality;

public class ButtonNextHandler implements EventHandler<ActionEvent>
{
    private JFXListView<Label> listView;

    private JFXSnackbar bar = new JFXSnackbar(CommandListGeneratorController.MAIN_PANE);

    private JFXDialog dialog;

    public ButtonNextHandler(JFXListView listView, JFXDialog dialog)
    {
        this.dialog = dialog;
        this.listView = listView;
    }

    @Override
    public void handle(ActionEvent event)
    {
        if (listView.getSelectionModel().getSelectedItems().size() > 0)
        {
            Functionality functionality = Functionality.getFunctionalitie(listView.getSelectionModel().getSelectedItems().get(0).getText());

        } else
        {
            dialog.close();
            bar.show("Please select a functionality !", 3 * 1000);
        }
    }
}
