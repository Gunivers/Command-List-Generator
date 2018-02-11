package net.gunivers.cmdlg.gui;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import net.gunivers.cmdlg.Main;

import java.io.IOException;
import java.net.URL;

public class Dialog
{
	private GridPane gridPane;
	private StackPane stackPane;

	private URL fxmlURL = Main.class.getResource("/fxml/Dialog.fxml");

	public Dialog() {}

	public Dialog(URL fxmlURL) {
		this.fxmlURL = fxmlURL;
	}

	public void show() throws IOException
	{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/Dialog.fxml"));
		GridPane dialog_pane = loader.load();
		dialog_pane.setPadding(new Insets(50, 50, 50, 50));

		StackPane back_pane_color = new StackPane();
		back_pane_color.setOnMouseClicked(event -> showMenuStage());
		back_pane_color.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		back_pane_color.setOpacity(1 * 0.35);

		StackPane pane = new StackPane(Main.MAIN_STAGE.getScene().getRoot(), back_pane_color, dialog_pane);
		pane.setOnKeyReleased(this::onKeyPressed);
		this.gridPane = dialog_pane;
		this.stackPane = pane;

		Main.MAIN_STAGE.getScene().setRoot(pane);
	}

	public void setHeader(Node node)
	{
		gridPane.add(node, 0, 0);
	}

	public void setBody(Node node)
	{
		gridPane.add(node, 0, 1);
	}

	public void setBotom(Node node)
	{
		gridPane.add(node, 0, 2);
	}

	public void addAction(Node node) {
		gridPane.add(node, 0, 3);
	}

	private void onKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ESCAPE)
			showMenuStage();
	}

	private void showMenuStage()
	{
		VBox box = (VBox) stackPane.getChildren().get(0);
		stackPane.getChildren().clear();
		Main.MAIN_STAGE.getScene().setRoot(box);
	}
}
