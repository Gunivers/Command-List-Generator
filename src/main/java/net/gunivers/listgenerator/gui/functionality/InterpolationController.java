package net.gunivers.listgenerator.gui.functionality;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import net.gunivers.listgenerator.gui.CommandListGeneratorController;
import net.gunivers.listgenerator.gui.handlers.ButtonEditHandler;
import net.gunivers.listgenerator.gui.handlers.ButtonNextHandler;
import net.gunivers.listgenerator.gui.handlers.list.SyncListHandler;
import net.gunivers.listgenerator.gui.util.FunctionalityController;
import net.gunivers.listgenerator.gui.util.OnlyDoubleChangeListener;
import net.gunivers.listgenerator.util.Functionality;
import net.gunivers.listgenerator.util.Tag;
import net.gunivers.listgenerator.util.value.*;

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

    private int INDEX = CommandListGeneratorController.SYNC_LIST_HANDLER.getListViewOne().getSelectionModel().getSelectedIndex();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        setDialog(ButtonNextHandler.newDialog);

        getDoneButton().setOnAction(event -> saveAll());

        TEXT_FIELD_1.textProperty().addListener(new OnlyDoubleChangeListener(TEXT_FIELD_1));
        TEXT_FIELD_2.textProperty().addListener(new OnlyDoubleChangeListener(TEXT_FIELD_2));
        TEXT_FIELD_3.textProperty().addListener(new OnlyDoubleChangeListener(TEXT_FIELD_3));

        if (CommandListGeneratorController.CONTROLLER.getTypeList().getSelectionModel().getSelectedItem() != null &&
                !CommandListGeneratorController.CONTROLLER.getTypeList().getSelectionModel().getSelectedItem().getText().isEmpty())
        {
            setDialog(ButtonEditHandler.dialog);
            IValue[] values = ValueManager.getValues(CommandListGeneratorController.CONTROLLER.getTagList().getSelectionModel().getSelectedItem().getText());
            TEXT_FIELD_1.setText(((DoubleValue) values[0]).get() + "");
            TEXT_FIELD_2.setText(((DoubleValue) values[1]).get() + "");
            TEXT_FIELD_3.setText(((DoubleValue) values[2]).get() + "");
            CHECK_BOX_1.setSelected(((BooleanValue) values[3]).get());
            CHECK_BOX_2.setSelected(((BooleanValue) values[4]).get());
        }
    }

    @Override
    public void saveAll()
    {
        Tag tag = Tag.tags.get(((Label) CommandListGeneratorController.SYNC_LIST_HANDLER.getListViewOne().getItems().get(INDEX)).getText());
        tag.setType(Functionality.getFunctionalitie("Interpolation"));

        try
        {
            ValueManager.register(tag, Double.valueOf(TEXT_FIELD_1.getText()), Double.valueOf(TEXT_FIELD_2.getText()), Double.valueOf(TEXT_FIELD_3.getText()), CHECK_BOX_1.isSelected(), CHECK_BOX_2.isSelected());
        } catch (Exception e)
        {
            return;
        }

        CommandListGeneratorController.SYNC_LIST_HANDLER.putInAndSelect(SyncListHandler.ListNumber.TWO, new Label(tag.getType().toString()), INDEX);
        getDialog().close();
    }
}
