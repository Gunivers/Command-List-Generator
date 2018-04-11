package net.gunivers.listgenerator.gui.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import net.gunivers.listgenerator.util.Tag;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class ButtonGenerateHandler implements EventHandler<ActionEvent>
{

    public int maxSize;
    public String mold;
    private Button button;
    private TextField commandTextField;

    public ButtonGenerateHandler(Button button, TextField textField, int maxSize)
    {
        this.mold = textField.getText();
        this.button = button;
        this.commandTextField = textField;
        this.maxSize = maxSize;
    }

    @Override
    public void handle(ActionEvent event)
    {
        HashMap<String, ArrayList<String>> replaceTag = new HashMap<>();

        for (Tag<?> t : Tag.getTags())
        {
            try
            {
                ArrayList<String> list = (ArrayList<String>) t.getType().getMethod().invoke(t.getType(), t.getParameters());
                if (list.size() < getMaxSize())
                    maxSize = list.size();
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
            {
                e.printStackTrace();
            }
        }

        ArrayList<String> commands = new ArrayList<>();

        for (int i = 0; i < getMaxSize(); i++)
        {
            String command = mold;
            for (Entry<String, ArrayList<String>> entry : replaceTag.entrySet())
            {
                command.replace(entry.getKey(), entry.getValue().get(i));
            }
            commands.add(command);
        }

        //TODO Disp in output
        //return commands;
    }

    public Button getButton()
    {
        return button;
    }

    public int getMaxSize()
    {
        return maxSize;
    }

    public String getMold()
    {
        return mold;
    }

    public TextField getCommandTextField()
    {
        return commandTextField;
    }
}
