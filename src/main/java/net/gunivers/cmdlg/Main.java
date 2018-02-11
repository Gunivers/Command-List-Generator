package net.gunivers.cmdlg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.gunivers.cmdlg.gui.console.Console;
import net.gunivers.cmdlg.gui.Theme;
import net.gunivers.cmdlg.util.GeneratorType;
import net.gunivers.cmdlg.util.Util;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main extends Application
{

	public static Stage MAIN_STAGE;

	public static LinkedHashMap<String, GeneratorType> nameToGeneratorType = new LinkedHashMap<>();

	static
	{
		try
		{
			GraphicsEnvironment ge =
					GraphicsEnvironment.getLocalGraphicsEnvironment();
			File tempFile = new File(System.getProperty("java.io.tmpdir") + "/CmdLgTemFontLoader.tmp");
			Util.copyStream(tempFile, Main.class.getResourceAsStream("/fonts/Roboto-Regular.ttf"));
			Font font = Font.createFont(Font.TRUETYPE_FONT, tempFile);
			ge.registerFont(font);
		} catch (IOException | FontFormatException e)
		{
			e.printStackTrace();
		}
		Console.start();

		for (GeneratorType type : GeneratorType.values())
		{
			Console.logDebug("Register new Generator Type: " + type);
			nameToGeneratorType.put(type.name(), type);
		}
	}

	public static void main(String[] args)
	{
		try
		{
			launch(args);
		} catch (Exception e)
		{
			e.printStackTrace();
			Console.mainFrame.setVisible(true);
			JOptionPane.showMessageDialog(null, "Error cant be start: \n" + e.fillInStackTrace(), "Error !", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Init all class #bordel
	 *
	 * @param stage
	 */
	@Override
	public void start(Stage stage)
	{
		MAIN_STAGE = stage;
		try
		{
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/Menu.fxml"));
			loader.load();

			Scene scene = new Scene(loader.getRoot());
			stage.setScene(scene);
			stage.setMinWidth(640);
			stage.setMinHeight(400);
			stage.getIcons().add(new Image("icon/menu.png"));

			stage.show();
			//set the console main frame visible
			Console.mainFrame.setVisible(true);

			loadTheme(Theme.CMDLG);

			Console.logInfo("You are showing the main stage");

			stage.setOnHiding(event -> Console.stop());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static GeneratorType generatorTypeByDisplayName(String s)
	{
		Console.logDebug("s -> " + s);
		for (GeneratorType type : nameToGeneratorType.values())
		{
			Console.logDebug("type -> " + type);
			if (s.equalsIgnoreCase(type.getName()))
			{
				Console.logDebug("return -> " + type);
				return type;
			}
		}
		return null;
	}

	public static Theme CURRENT_THEME = Theme.JAVA_DEFAULT;

	public static void loadTheme(Theme theme)
	{
		if (CURRENT_THEME != theme)
		{
			if (MAIN_STAGE.getScene().getStylesheets().size() > 0 && MAIN_STAGE.getScene().getStylesheets().contains(CURRENT_THEME.getCssUrl().toExternalForm()))
			{
				MAIN_STAGE.getScene().getStylesheets().remove(CURRENT_THEME.getCssUrl().toExternalForm());
			}
			try
			{
				MAIN_STAGE.getScene().getStylesheets().add(theme.getCssUrl().toExternalForm());
			} catch (Exception e)
			{
			}
			CURRENT_THEME = theme;
		}
	}
}
