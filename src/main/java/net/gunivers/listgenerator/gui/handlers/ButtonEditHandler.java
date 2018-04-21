package net.gunivers.listgenerator.gui.handlers;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import net.gunivers.listgenerator.gui.CommandListGeneratorController;

import java.io.IOException;

public class ButtonEditHandler implements EventHandler<ActionEvent>
{
    public static JFXDialog dialog;

    @Override
    public void handle(ActionEvent event)
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
    }
}
