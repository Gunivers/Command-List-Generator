package net.gunivers.listgenerator.gui.handlers;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import net.gunivers.listgenerator.gui.CommandListGeneratorController;
import net.gunivers.listgenerator.util.Functionality;

import java.io.IOException;

public class ButtonNextHandler implements EventHandler<ActionEvent>
{
    private JFXListView<Label> listView;

    private JFXSnackbar bar = new JFXSnackbar(CommandListGeneratorController.MAIN_PANE);

    public static JFXDialog dialog;

    public static JFXDialog newDialog;

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
            newDialog = new JFXDialog();
            JFXDialogLayout layout = null;

            try
            {
                layout = FXMLLoader.load(getClass().getResource("/fxml/functionality/" + functionality.toString() + ".fxml"));
            } catch (IOException e)
            {
                e.printStackTrace();
            }

            newDialog.setContent(layout);

            dialog.close();
            newDialog.show(CommandListGeneratorController.MAIN_PANE);
        } else
        {
            dialog.close();
            bar.show("Please select a functionality !", 3 * 1000);
        }
    }
}
