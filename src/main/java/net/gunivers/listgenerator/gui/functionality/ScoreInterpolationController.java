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

import java.net.URL;
import java.util.ResourceBundle;

public class ScoreInterpolationController extends FunctionalityController implements Initializable
{
    @FXML
    private JFXCheckBox CHECK_BOX;

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
        ButtonNextHandler.newDialog.close();
        int index = CommandListGeneratorController.SYNC_LIST_HANDLER.getListViewOne().getSelectionModel().getSelectedIndex();
        Label label = new Label("ScoreInterpolation");
        CommandListGeneratorController.SYNC_LIST_HANDLER.putInAndSelect(SyncListHandler.ListNumber.TWO, label, index);
    }
}
