package net.gunivers.listgenerator.gui.util;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public abstract class FunctionalityController
{
    @FXML
    private JFXButton DONE;

    public JFXButton getDoneButton()
    {
        return DONE;
    }

    public abstract void saveAll();
}
