package net.gunivers.listgenerator.gui.functionality;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import net.gunivers.listgenerator.gui.CommandListGeneratorController;
import net.gunivers.listgenerator.gui.handlers.ButtonNextHandler;
import net.gunivers.listgenerator.gui.handlers.list.SyncListHandler;
import net.gunivers.listgenerator.gui.util.FunctionalityController;
import net.gunivers.listgenerator.gui.util.OnlyIntChangeListener;
import net.gunivers.listgenerator.util.Tag;
import net.gunivers.listgenerator.util.value.ValueManager;

import java.net.URL;
import java.util.ResourceBundle;

public class DuplicationController extends FunctionalityController implements Initializable
{
    @FXML
    private JFXTextField TOP_TEXT;

    @FXML
    private JFXTextField MIDDLE_TEXT;

    @FXML
    private JFXTextField BOTTOM_TEXT;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        getDoneButton().setOnAction(event -> saveAll());

        MIDDLE_TEXT.textProperty().addListener(new OnlyIntChangeListener(MIDDLE_TEXT));
        BOTTOM_TEXT.textProperty().addListener(new OnlyIntChangeListener(BOTTOM_TEXT));
    }

    @Override
    public void saveAll()
    {
        int index = CommandListGeneratorController.SYNC_LIST_HANDLER.getListViewOne().getSelectionModel().getSelectedIndex();

        Label label = new Label("Duplication");
        Label tag = (Label) CommandListGeneratorController.SYNC_LIST_HANDLER.getListViewOne().getItems().get(index);

        try
        {
            ValueManager.register(Tag.tags.get(tag.getText()), TOP_TEXT.getText(), Integer.valueOf(MIDDLE_TEXT.getText()), Integer.valueOf(BOTTOM_TEXT.getText()));
        } catch (Exception e)
        {
            return;
        }

        CommandListGeneratorController.SYNC_LIST_HANDLER.putInAndSelect(SyncListHandler.ListNumber.TWO, label, index);
        ButtonNextHandler.newDialog.close();
    }
}
