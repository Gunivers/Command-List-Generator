package net.gunivers.cmdlg.gui.handler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.gunivers.cmdlg.Main;

public class AboutHandler implements EventHandler<ActionEvent>
{
	@Override
	public void handle(ActionEvent event)
	{
		Stage stage = new Stage();
		stage.initOwner(Main.MAIN_STAGE);
		WebView webView = new WebView();
		webView.getEngine().load("https://gunivers.net/");
		Pane pane = new Pane();
		pane.getChildren().add(webView);
		stage.setScene(new Scene(pane));
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
	}
}
