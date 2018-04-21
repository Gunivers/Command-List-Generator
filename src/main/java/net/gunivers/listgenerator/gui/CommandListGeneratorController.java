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
import java.util.*;

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

        TextFieldCommandChangeHandler handler = new TextFieldCommandChangeHandler(COMMAND_INPUT);

        COMMAND_INPUT.setOnKeyTyped(handler);

        BUTTON_GENERATE.setOnAction(new ButtonGenerateHandler(BUTTON_GENERATE, COMMAND_INPUT, COMMAND_OUTPUT, getMaxSize()));

        BUTTON_EDIT.setOnAction(new ButtonEditHandler());
    }

    public SyncListHandler getSyncListHandler()
    {
        return syncListHandler;
    }

    public void checksTag(Set<String> tags)
    {
       List<String> listASupprimé = new ArrayList<>();
        for (Label l : TAG_LIST.getItems())
            listASupprimé.add(l.getText());

        List<String> list = new ArrayList<>();
        for (String s : listASupprimé)
            list.add(s);

        listASupprimé.removeAll(tags);

        tags.removeAll(list);

       if (!listASupprimé.isEmpty() || !tags.isEmpty())
        {
        	System.out.println("aa\n");
            ArrayList<Label> bgj = new ArrayList<>();
            for (Label s : TAG_LIST.getItems())
                bgj.add(s);

            if (bgj.size() > 0)
                for (Label label : bgj)
                    for (String str : listASupprimé)
                        if (label.getText().equalsIgnoreCase(str))
                            TAG_LIST.getItems().remove(label);


            for (String str : tags)
                TAG_LIST.getItems().add(new Label(str));
        }
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
