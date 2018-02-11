package net.gunivers.cmdlg.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import net.gunivers.cmdlg.Main;

import java.io.IOException;
import java.net.URL;

public class Dialog
{
	@FXML
	private TextField TEXT_FIELD_TO;

	@FXML
	private TextArea TEXT_AREA_BY;

	@FXML
	private Button BUTTON_DONE;

	@FXML
	private Button BUTTON_EXIT;

	private StackPane stackPane;

	private URL fxmlURL;

	public Dialog()
	{
		fxmlURL = Main.class.getResource("/fxml/Dialog.fxml");
		init();
	}

	public Dialog(URL fxmlURL)
	{
		this.fxmlURL = fxmlURL;
		init();
	}

	public void show()
	{
		Main.MAIN_STAGE.getScene().setRoot(stackPane);
	}

	public void showMenuStage()
	{
		VBox box = (VBox) stackPane.getChildren().get(0);
		stackPane.getChildren().clear();
		Main.MAIN_STAGE.getScene().setRoot(box);
	}

	public Button getDoneButton()
	{
		return BUTTON_DONE;
	}

	public Button getExitButton()
	{
		return BUTTON_EXIT;
	}

	public TextArea getTextAreaBy()
	{
		return TEXT_AREA_BY;
	}

	public TextField getTextFieldTo()
	{
		return TEXT_FIELD_TO;
	}

	private void onKeyPressed(KeyEvent event)
	{
		if (event.getCode() == KeyCode.ESCAPE)
			showMenuStage();
	}

	private void init()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(fxmlURL);
			loader.setController(this);
			VBox dialog_pane = loader.load();
			dialog_pane.setPadding(new Insets(50, 50, 50, 50));

			StackPane back_pane_color = new StackPane();
			back_pane_color.setOnMouseClicked(event -> showMenuStage());
			back_pane_color.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
			back_pane_color.setOpacity(0.35);

			StackPane pane = new StackPane(Main.MAIN_STAGE.getScene().getRoot(), back_pane_color, dialog_pane);

			if (Main.CURRENT_THEME.getCssUrl() != null)
				pane.getStylesheets().add(Main.CURRENT_THEME.getCssUrl().toExternalForm());

			pane.setOnKeyReleased(this::onKeyPressed);

			this.stackPane = pane;
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
