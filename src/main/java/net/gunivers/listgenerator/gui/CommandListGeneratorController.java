package net.gunivers.listgenerator.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import net.gunivers.listgenerator.gui.handlers.ButtonEditHandler;
import net.gunivers.listgenerator.gui.handlers.ButtonGenerateHandler;
import net.gunivers.listgenerator.gui.handlers.CommandChangeHandler;
import net.gunivers.listgenerator.gui.handlers.list.SyncListHandler;

import java.net.URL;
import java.util.ResourceBundle;

@SuppressWarnings({"unchecked", "rawtypes"})
public class CommandListGeneratorController implements Initializable
{

    @FXML
    private JFXButton BUTTON_GENERATE;

    @FXML
    private JFXButton BUTTON_EDIT;

    @FXML
    private JFXTextArea COMMAND_OUTPUT;

    @FXML
    private JFXTextField COMMAND_INPUT;

    @FXML
    private JFXListView TAG_LIST;

    @FXML
    private JFXListView TYPE_LIST;

    private SyncListHandler syncListHandler = null;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        syncListHandler = new SyncListHandler(TAG_LIST, TYPE_LIST);

        BUTTON_EDIT.setOnAction(new ButtonEditHandler(syncListHandler));

        COMMAND_INPUT.setOnKeyTyped(new CommandChangeHandler());

        BUTTON_GENERATE.setOnAction(new ButtonGenerateHandler(BUTTON_GENERATE, COMMAND_INPUT, 0));
    }

    public SyncListHandler getSyncListHandler()
    {
        return syncListHandler;
    }
}
