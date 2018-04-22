package net.gunivers.listgenerator.gui.functionality;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import net.gunivers.listgenerator.gui.CommandListGeneratorController;
import net.gunivers.listgenerator.gui.handlers.ButtonNextHandler;
import net.gunivers.listgenerator.gui.handlers.list.SyncListHandler;
import net.gunivers.listgenerator.gui.util.FunctionalityController;

import java.net.URL;
import java.util.ResourceBundle;

public class ListController extends FunctionalityController implements Initializable
{
    @FXML
    JFXTextArea TEXT_AREA;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        getDoneButton().setOnAction(event -> saveAll());
    }

    @Override
    public void saveAll()
    {
        ButtonNextHandler.newDialog.close();
        int index = CommandListGeneratorController.SYNC_LIST_HANDLER.getListViewOne().getSelectionModel().getSelectedIndex();
        Label label = new Label("List");
        CommandListGeneratorController.SYNC_LIST_HANDLER.putInAndSelect(SyncListHandler.ListNumber.TWO, label, index);
    }
}
