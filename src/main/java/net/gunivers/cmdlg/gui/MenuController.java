package net.gunivers.cmdlg.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import net.gunivers.cmdlg.Main;
import net.gunivers.cmdlg.gui.console.Console;
import net.gunivers.cmdlg.gui.handler.AboutHandler;
import net.gunivers.cmdlg.gui.handler.QuitHandler;
import net.gunivers.cmdlg.gui.handler.SelectThemeHandler;
import net.gunivers.cmdlg.gui.theme.Theme;
import net.gunivers.cmdlg.util.GeneratorType;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable
{
	/**
	 * ALL MENU ITEM
	 */
	@FXML
	private Menu MENU_THEME;

	@FXML
	private MenuItem QUIT_MENU_ITEM;

	@FXML
	private MenuItem ABOUT_MENU_ITEM;

	/**
	 * ALL LIST VIEW ITEM
	 */
	@FXML
	private ListView<Label> GENERATOR_LIST;

	@FXML
	private ListView<Label> FILTER_LIST;

	/**
	 * ALL TEXT FIELD ITEM
	 */
	@FXML
	private TextField COMMAND_TEXT_FIELD;

	/**
	 * ALL TEXT AREA ITEM
	 */
	@FXML
	private TextArea OUTPUT_TEXT_AREA;

	/**
	 *	This is the first method when the Menu.fxml file is initialized.
	 * @param location location of FXML File
	 * @param resources IDK
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		Console.logDebug("Init Theme Menu");
		//Add All Theme to the menu
		for (Theme t : Theme.values()) {
			MenuItem item = new MenuItem(t.getName());
			item.setOnAction(new SelectThemeHandler(item));
			MENU_THEME.getItems().add(item);
		}

		Console.logDebug("Add handler for Quit Menu Item");
		QUIT_MENU_ITEM.setOnAction(new QuitHandler()); //Set the action for terminate the screen

		Console.logDebug("Add handler for About Menu Item");
		ABOUT_MENU_ITEM.setOnAction(new AboutHandler()); //Set the action for terminate the screen

		Console.logDebug("Add all Label for the Generator list");
		//Add all Generator to the display
		for (GeneratorType type : GeneratorType.values()) {
			GENERATOR_LIST.getItems().add(new Label(type.getName()));
		}

	}
}
