package net.gunivers.listgenerator.gui.functionality;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import net.gunivers.listgenerator.gui.handlers.ButtonNextHandler;
import net.gunivers.listgenerator.gui.util.FunctionalityController;
import net.gunivers.listgenerator.gui.util.OnlyIntChangeListener;

import java.net.URL;
import java.util.ResourceBundle;

public class DichotomyController extends FunctionalityController implements Initializable
{
    @FXML
    private JFXTextField TOP_TEXT;

    @FXML
    private JFXTextField BOTTOM_TEXT;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        TOP_TEXT.textProperty().addListener(new OnlyIntChangeListener(TOP_TEXT));
        BOTTOM_TEXT.textProperty().addListener(new OnlyIntChangeListener(BOTTOM_TEXT));

        getDoneButton().setOnAction(event -> saveAll());
    }

    @Override
    public void saveAll()
    {
        ButtonNextHandler.newDialog.close();
    }
}
