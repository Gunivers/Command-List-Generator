package net.gunivers.listgenerator.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import net.gunivers.listgenerator.gui.handlers.ButtonEditHandler;
import net.gunivers.listgenerator.gui.handlers.ButtonGenerateHandler;
import net.gunivers.listgenerator.gui.handlers.CommandChangeHandler;
import net.gunivers.listgenerator.gui.handlers.list.SyncListHandler;

public class CommandListGeneratorController implements Initializable
{


    public static StackPane MAIN_PANE;

    @FXML
    private StackPane PANE;

    @FXML
    private JFXButton BUTTON_GENERATE;

    @FXML
    private JFXButton BUTTON_EDIT;

    @FXML
    private JFXTextArea COMMAND_OUTPUT;

    @FXML
    private JFXTextField COMMAND_INPUT;

    @FXML
    private JFXTextField MAX_COMMAND;

    @FXML
    private JFXListView<Label> TAG_LIST;

    @FXML
    private JFXListView<Label> TYPE_LIST;


    private SyncListHandler syncListHandler = null;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        MAIN_PANE = PANE;

        syncListHandler = new SyncListHandler(TAG_LIST, TYPE_LIST);

        MAX_COMMAND.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*"))
            {
                String msg = newValue.replaceAll("[^\\d]", "");
                MAX_COMMAND.setText(msg);
            }
        });

        TAG_LIST.getItems().add(new Label("test"));
        TAG_LIST.getItems().add(new Label("test"));
        TAG_LIST.getItems().add(new Label("test"));
        TAG_LIST.getItems().add(new Label("test"));

        TYPE_LIST.getItems().add(new Label("test"));
        TYPE_LIST.getItems().add(new Label("test"));
        TYPE_LIST.getItems().add(new Label("test"));
        TYPE_LIST.getItems().add(new Label("test"));

        COMMAND_INPUT.setOnKeyTyped(new CommandChangeHandler(COMMAND_INPUT));
        COMMAND_INPUT.setOnKeyReleased(new CommandChangeHandler(COMMAND_INPUT));

        BUTTON_GENERATE.setOnAction(new ButtonGenerateHandler(BUTTON_GENERATE, COMMAND_INPUT, COMMAND_OUTPUT, getMaxSize()));

        BUTTON_EDIT.setOnAction(new ButtonEditHandler(syncListHandler, getMaxSize()));
    }

    public SyncListHandler getSyncListHandler()
    {
        return syncListHandler;
    }

    public int getMaxSize()
    {
        try
        {
            return Integer.valueOf(MAX_COMMAND.getText());
        } catch (Exception e)
        {
            return 0;
        }
    }
}
