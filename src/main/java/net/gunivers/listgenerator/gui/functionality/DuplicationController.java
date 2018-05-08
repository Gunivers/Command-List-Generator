package net.gunivers.listgenerator.gui.functionality;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import net.gunivers.listgenerator.gui.CommandListGeneratorController;
import net.gunivers.listgenerator.gui.handlers.ButtonEditHandler;
import net.gunivers.listgenerator.gui.handlers.ButtonNextHandler;
import net.gunivers.listgenerator.gui.handlers.list.SyncListHandler;
import net.gunivers.listgenerator.gui.util.FunctionalityController;
import net.gunivers.listgenerator.gui.util.OnlyIntChangeListener;
import net.gunivers.listgenerator.util.Functionality;
import net.gunivers.listgenerator.util.Tag;
import net.gunivers.listgenerator.util.value.IValue;
import net.gunivers.listgenerator.util.value.IntValue;
import net.gunivers.listgenerator.util.value.StringValue;
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

    private int INDEX = CommandListGeneratorController.SYNC_LIST_HANDLER.getListViewOne().getSelectionModel().getSelectedIndex();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        setDialog(ButtonNextHandler.newDialog);

        getDoneButton().setOnAction(event -> saveAll());

        MIDDLE_TEXT.textProperty().addListener(new OnlyIntChangeListener(MIDDLE_TEXT));
        BOTTOM_TEXT.textProperty().addListener(new OnlyIntChangeListener(BOTTOM_TEXT));

        if (CommandListGeneratorController.CONTROLLER.getTypeList().getSelectionModel().getSelectedItem() != null &&
                !CommandListGeneratorController.CONTROLLER.getTypeList().getSelectionModel().getSelectedItem().getText().isEmpty())
        {
            setDialog(ButtonEditHandler.dialog);
            IValue[] values = ValueManager.getValues(CommandListGeneratorController.CONTROLLER.getTagList().getSelectionModel().getSelectedItem().getText());
            TOP_TEXT.setText(((StringValue) values[0]).get());
            MIDDLE_TEXT.setText(((IntValue) values[1]).get() + "");
            BOTTOM_TEXT.setText(((IntValue) values[2]).get() + "");
        }
    }

    @Override
    public void saveAll()
    {
        Tag tag = Tag.tags.get(((Label) CommandListGeneratorController.SYNC_LIST_HANDLER.getListViewOne().getItems().get(INDEX)).getText());
        tag.setType(Functionality.getFunctionalitie("Duplication"));

        try
        {
            ValueManager.register(tag, TOP_TEXT.getText(), Integer.valueOf(MIDDLE_TEXT.getText()), Integer.valueOf(BOTTOM_TEXT.getText()), CommandListGeneratorController.CONTROLLER.getMaxCommand());
        } catch (Exception e)
        {
            return;
        }

        CommandListGeneratorController.SYNC_LIST_HANDLER.putInAndSelect(SyncListHandler.ListNumber.TWO, new Label(tag.getType().toString()), INDEX);
        getDialog().close();
    }
}
