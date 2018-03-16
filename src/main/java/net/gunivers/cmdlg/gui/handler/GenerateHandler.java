package net.gunivers.cmdlg.gui.handler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class GenerateHandler implements EventHandler<ActionEvent>
{
    private TextField command_textfield;
    private ListView<Label> generator_list;

    public GenerateHandler(TextField command_textfield, ListView<Label> listView)
    {
        this.generator_list = listView;
        this.command_textfield = command_textfield;
    }

    @Override
    public void handle(ActionEvent event)
    {
        if (generator_list.getSelectionModel().getSelectedItems() == null)
        {
            showError("Please select a Generator Type !");
            return;
        }

        String command = command_textfield.getText();

        if (command.isEmpty())
        {
            showError("Please insert a command !");
            return;
        }

    }

    private void showError(String error)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Please look the error");
        alert.setContentText(error);
        alert.showAndWait();
    }
}
