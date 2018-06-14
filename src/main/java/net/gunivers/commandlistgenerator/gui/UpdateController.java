package net.gunivers.commandlistgenerator.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import net.gunivers.commandlistgenerator.CommandListGenerator;

public class UpdateController implements Initializable {
	
	private static String newVersion;
	private static String version = "";
	
	@FXML
	private Label NEW;
	
	@FXML
	private Label ACTUAL;
	
	@FXML
	private Hyperlink LINK;
	
	@FXML
	private JFXButton CLOSE;
	
	private static JFXDialog dialog = new JFXDialog();
	
	public static void availableUpdate() throws IOException, URISyntaxException {
		String urlS = "https://raw.githubusercontent.com/Gunivers/Minecraft-GDK/download/last-version.txt";
    	URL url = new URL(urlS);
    	BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
    	String inputLine;
        while ((inputLine = br.readLine()) != null)
            newVersion = inputLine;
        br.close();
        
       Path path = Paths.get(UpdateController.class.getClassLoader().getResource("version").toURI());
       Stream<String> lines = Files.lines(path);
       lines.forEach(line -> version += line);
       lines.close();
       
        if(!newVersion.equals(version)) {
	
			JFXDialogLayout layout = null;
			try {
				layout = FXMLLoader.load(UpdateController.class.getResource("/fxml/Update.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			dialog.setContent(layout);
			dialog.show(CommandListGeneratorController.MAIN_PANE);
        }
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		NEW.setText(CommandListGenerator.LANGUAGE.get("gui.updater.newversion") + " " + newVersion);
		ACTUAL.setText(CommandListGenerator.LANGUAGE.get("gui.updater.currentversion") + " " + version);
		LINK.setText(CommandListGenerator.LANGUAGE.get("gui.updater.hyperlink"));
		CLOSE.setText(CommandListGenerator.LANGUAGE.get("gui.updater.button.ignore"));
		CLOSE.setOnAction(eventHandler -> close());
	}
	
	private void close() {
		dialog.close();
	}

}
