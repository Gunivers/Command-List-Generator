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
import net.gunivers.listgenerator.util.Tag;

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


    private SyncListHandler syncListHandler = null;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        CONTROLLER = this;
        MAIN_PANE = PANE;

        syncListHandler = new SyncListHandler(TAG_LIST, TYPE_LIST);

        MAX_COMMAND.textProperty().addListener(new OnlyIntChangeListener(MAX_COMMAND));

<<<<<<< HEAD
        COMMAND_INPUT.setOnKeyTyped(new TextFieldCommandChangeHandler(COMMAND_INPUT));
=======
        TextFieldCommandChangeHandler handler = new TextFieldCommandChangeHandler(COMMAND_INPUT);

        COMMAND_INPUT.setOnKeyTyped(handler);
>>>>>>> eb70209587e8ce1356b6767d3779ae0fa6e8a256

        BUTTON_GENERATE.setOnAction(new ButtonGenerateHandler(BUTTON_GENERATE, COMMAND_INPUT, COMMAND_OUTPUT, getMaxSize()));

        BUTTON_EDIT.setOnAction(new ButtonEditHandler());
    }

    public SyncListHandler getSyncListHandler()
    {
        return syncListHandler;
    }

    public void checksTag(Set<String> tags)
    {
<<<<<<< HEAD
        ArrayList<String> displayed = new ArrayList<>();
=======
       List<String> listASupprimé = new ArrayList<>();
>>>>>>> eb70209587e8ce1356b6767d3779ae0fa6e8a256
        for (Label l : TAG_LIST.getItems())
            displayed.add(l.getText());

        ArrayList<Label> needRemoveTag = new ArrayList<>();

        ArrayList<String> needAddedTag = new ArrayList<>();

        for (String tag : tags)
        {
            if (!displayed.contains(tag))
            {
                needAddedTag.add(tag);
            }
        }

<<<<<<< HEAD
        for (Label label : TAG_LIST.getItems())
        {
            if (!tags.contains(label.getText()))
                needRemoveTag.add(label);
=======
       if (!listASupprimé.isEmpty() || !tags.isEmpty())
        {
        	System.out.println("aa\n");
            ArrayList<Label> bgj = new ArrayList<>();
            for (Label s : TAG_LIST.getItems())
                bgj.add(s);
>>>>>>> eb70209587e8ce1356b6767d3779ae0fa6e8a256

        }

        for (Label label : needRemoveTag)
            TAG_LIST.getItems().remove(label);

        for (String str : needAddedTag)
            TAG_LIST.getItems().add(new Label(str));
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
