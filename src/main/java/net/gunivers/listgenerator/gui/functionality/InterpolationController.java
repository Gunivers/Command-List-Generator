package net.gunivers.listgenerator.gui.functionality;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import net.gunivers.listgenerator.gui.CommandListGeneratorController;
import net.gunivers.listgenerator.gui.handlers.ButtonNextHandler;
import net.gunivers.listgenerator.gui.handlers.list.SyncListHandler;
import net.gunivers.listgenerator.gui.util.FunctionalityController;
import net.gunivers.listgenerator.gui.util.OnlyDoubleChangeListener;
import net.gunivers.listgenerator.util.Tag;
import net.gunivers.listgenerator.util.value.ValueManager;

import java.net.URL;
import java.util.ResourceBundle;

public class InterpolationController extends FunctionalityController implements Initializable
{
    @FXML
    private JFXCheckBox CHECK_BOX_1;

    @FXML
    private JFXCheckBox CHECK_BOX_2;

    @FXML
    private JFXTextField TEXT_FIELD_1;

    @FXML
    private JFXTextField TEXT_FIELD_2;

    @FXML
    private JFXTextField TEXT_FIELD_3;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        getDoneButton().setOnAction(event -> saveAll());

        TEXT_FIELD_1.textProperty().addListener(new OnlyDoubleChangeListener(TEXT_FIELD_1));
        TEXT_FIELD_2.textProperty().addListener(new OnlyDoubleChangeListener(TEXT_FIELD_2));
        TEXT_FIELD_3.textProperty().addListener(new OnlyDoubleChangeListener(TEXT_FIELD_3));
    }

    @Override
    public void saveAll()
    {
        int index = CommandListGeneratorController.SYNC_LIST_HANDLER.getListViewOne().getSelectionModel().getSelectedIndex();

        Label label = new Label("Interpolation");
        Label tag = (Label) CommandListGeneratorController.SYNC_LIST_HANDLER.getListViewOne().getItems().get(index);

        try
        {
            ValueManager.register(Tag.tags.get(tag.getText()), TEXT_FIELD_1.getText(), TEXT_FIELD_2.getText(), TEXT_FIELD_3.getText(), CHECK_BOX_1.isSelected(), CHECK_BOX_2.isSelected());
        } catch (Exception e)
        {
            return;
        }

        ButtonNextHandler.newDialog.close();
        CommandListGeneratorController.SYNC_LIST_HANDLER.putInAndSelect(SyncListHandler.ListNumber.TWO, label, index);
    }
}
