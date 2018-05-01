package net.gunivers.listgenerator.gui;

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
import net.gunivers.listgenerator.gui.handlers.TextFieldCommandChangeHandler;
import net.gunivers.listgenerator.gui.handlers.list.SyncListHandler;
import net.gunivers.listgenerator.gui.util.OnlyIntChangeListener;
import net.gunivers.listgenerator.util.value.ValueManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;

public class CommandListGeneratorController implements Initializable
{
    public static CommandListGeneratorController CONTROLLER;

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


    public static SyncListHandler<Label> SYNC_LIST_HANDLER = null;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        CONTROLLER = this;
        MAIN_PANE = PANE;

        SYNC_LIST_HANDLER = new SyncListHandler(TAG_LIST, TYPE_LIST);

        MAX_COMMAND.textProperty().addListener(new OnlyIntChangeListener(MAX_COMMAND));

        COMMAND_INPUT.setOnKeyTyped(new TextFieldCommandChangeHandler(COMMAND_INPUT));

        BUTTON_GENERATE.setOnAction(new ButtonGenerateHandler(BUTTON_GENERATE, COMMAND_INPUT, COMMAND_OUTPUT, getMaxCommand()));

        BUTTON_EDIT.setOnAction(new ButtonEditHandler());
    }

    public SyncListHandler getSyncListHandler()
    {
        return SYNC_LIST_HANDLER;
    }

    public void checksTag(Set<String> tags)
    {
        ArrayList<String> displayed = new ArrayList<>();
        for (Label l : TAG_LIST.getItems())
            displayed.add(l.getText());

        ArrayList<Label> needRemoveTag = new ArrayList<>();

        ArrayList<Label> needAddedTag = new ArrayList<>();

        for (String tag : tags)
        {
            if (!displayed.contains(tag))
            {
                needAddedTag.add(new Label(tag));
            }
        }

        for (Label label : TAG_LIST.getItems())
        {
            if (!tags.contains(label.getText()))
                needRemoveTag.add(label);

        }

        for (Label label : needRemoveTag)
        {
            int index = TAG_LIST.getItems().indexOf(label);

            if (!TYPE_LIST.getItems().get(index).getText().isEmpty())
                ValueManager.removeValues(TAG_LIST.getItems().get(index).getText());

            TAG_LIST.getItems().remove(index);
            TYPE_LIST.getItems().remove(index);
        }

        for (Label label : needAddedTag)
        {
            TAG_LIST.getItems().add(label);
            TYPE_LIST.getItems().add(new Label());
        }

        TAG_LIST.refresh();
        TYPE_LIST.refresh();
    }

    public int getMaxCommand()
    {
        try
        {
            return Integer.valueOf(MAX_COMMAND.getText());
        } catch (Exception e)
        {
            return 0;
        }
    }

    public JFXListView<Label> getTagList()
    {
        return TAG_LIST;
    }

    public JFXListView<Label> getTypeList()
    {
        return TYPE_LIST;
    }
}
