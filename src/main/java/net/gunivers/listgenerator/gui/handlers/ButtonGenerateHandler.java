package net.gunivers.listgenerator.gui.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
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
    private TextArea output;

    public ButtonGenerateHandler(Button button, TextField textField, TextArea output, int maxSize)
    {
        this.mold = textField.getText();
        this.button = button;
        this.commandTextField = textField;
        this.maxSize = maxSize;
        this.output = output;
    }

    @Override
    public void handle(ActionEvent event)
    {
        HashMap<String, ArrayList<String>> replaceTag = new HashMap<>();

        for (Tag t : Tag.tags.values())
        {
            try
            {
<<<<<<< HEAD
                ArrayList<Object> list = (ArrayList<Object>) t.getType().getMethod().invoke(t.getType(), t.getParameters());
=======
                @SuppressWarnings("unchecked")
                ArrayList<String> list = (ArrayList<String>) t.getType().getMethod().invoke(t.getType(), t.getParameters());
>>>>>>> 4bb9c396e4eaa2e64c466edd22872e687daa093c
                if (list.size() < getMaxSize())
                    maxSize = list.size();
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
            {
                e.printStackTrace();
            }
        }

        ArrayList<String> commands = new ArrayList<>();
        String commandsList = "";

        for (int i = 0; i < getMaxSize(); i++)
        {
            String command = mold;
            for (Entry<String, ArrayList<String>> entry : replaceTag.entrySet())
            {
                command.replace(entry.getKey(), entry.getValue().get(i));
            }
            commands.add(command);
            commandsList += command;
            if (i < getMaxSize() - 1)
                commandsList += "\n";
        }

        output.setText(commandsList);
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
