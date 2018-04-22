package net.gunivers.listgenerator.gui.handlers;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXSnackbar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import net.gunivers.listgenerator.gui.CommandListGeneratorController;

import java.io.IOException;

public class ButtonEditHandler implements EventHandler<ActionEvent>
{

    private JFXSnackbar bar = new JFXSnackbar(CommandListGeneratorController.MAIN_PANE);

    public static JFXDialog dialog;

    @Override
    public void handle(ActionEvent event)
    {
        if (CommandListGeneratorController.CONTROLLER.getTagList().getSelectionModel().getSelectedItems().size() > 0)
        {
            dialog = new JFXDialog();

            JFXDialogLayout layout = null;

            try
            {
                layout = FXMLLoader.load(getClass().getResource("/fxml/ButtonEdit.fxml"));
            } catch (IOException e)
            {
                e.printStackTrace();
            }

            dialog.setContent(layout);
            dialog.show(CommandListGeneratorController.MAIN_PANE);
        } else
        {
            bar.show("Please select a TAG !", 3 * 1000);
        }
    }
}
