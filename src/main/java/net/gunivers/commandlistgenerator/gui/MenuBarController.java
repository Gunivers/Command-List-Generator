package net.gunivers.commandlistgenerator.gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import net.gunivers.commandlistgenerator.CommandListGenerator;
import net.gunivers.commandlistgenerator.functionality.list.List;
import net.gunivers.commandlistgenerator.gui.util.FunctionalityController;
import net.gunivers.commandlistgenerator.util.FileIO;
import net.gunivers.commandlistgenerator.util.HelpFunctionality;
import net.gunivers.commandlistgenerator.util.Tag;
import net.gunivers.core.language.Language;
import net.gunivers.core.language.Locale;
import net.gunivers.core.utils.tuple.Tuple4;

public class MenuBarController {

	private JFXSnackbar bar = new JFXSnackbar(CommandListGeneratorController.MAIN_PANE);
	private Language l = CommandListGenerator.LANGUAGE;

	private final MenuBar MENU;
	private final JFXTextField COMMAND;
	private final JFXTextField MAX;
	private final JFXTextArea OUTPUT;
	private final ArrayList<String> MENU_ITEMS = new ArrayList<String>();
	private final ArrayList<String> FILE_ITEMS = new ArrayList<String>();
	private final ArrayList<String> EDIT_ITEMS = new ArrayList<String>();
	private final ArrayList<String> HELP_ITEMS = new ArrayList<String>();

	public MenuBarController(MenuBar menu, JFXTextField command, JFXTextField max, JFXTextArea output) {
		this.MENU = menu;
		this.COMMAND = command;
		this.OUTPUT = output;
		this.MAX = max;
		
		MENU_ITEMS.add("gui.commandlistgenerator.menu.file");
		MENU_ITEMS.add("gui.commandlistgenerator.menu.edit");
		MENU_ITEMS.add("gui.commandlistgenerator.menu.help");
		
		FILE_ITEMS.add("gui.commandlistgenerator.menu.file.open");
		FILE_ITEMS.add("gui.commandlistgenerator.menu.file.save");
		FILE_ITEMS.add("gui.commandlistgenerator.menu.file.export");
		
		HELP_ITEMS.add("gui.commandlistgenerator.menu.help.language");
		HELP_ITEMS.add("gui.commandlistgenerator.menu.help.help");
		HELP_ITEMS.add("gui.commandlistgenerator.menu.help.about");
		
		final Menu file = MENU.getMenus().get(0);
		file.getItems().clear();
		MenuItem open = new MenuItem();
		open.setOnAction(actionEvent -> load());
		MenuItem save = new MenuItem();
		save.setOnAction(actionEvent -> save());
		MenuItem export = new MenuItem();
		export.setOnAction(actionEvent -> export());
		file.getItems().addAll(open, save, export);
		
		final Menu edit = MENU.getMenus().get(1);
		edit.getItems().clear();
		
		final Menu help = MENU.getMenus().get(2);
		help.getItems().clear();
		Menu language = new Menu();
		for(Locale lang : Locale.values()) {
			CheckMenuItem m = new CheckMenuItem(lang.getName());
			if(lang.getName().equals(l.getLanguageName()))
				m.setSelected(true);
			m.setOnAction(actionEvent -> changeLanguage(lang.getName(), language, m));
			language.getItems().add(m);
		}
		CheckMenuItem helpI = new CheckMenuItem();
		helpI.setSelected(CommandListGenerator.prefs.getBoolean("Activate Help", true));
		helpI.setOnAction(actionEvent -> help(helpI));
		MenuItem about = new MenuItem();
		about.setOnAction(actionEvent -> about());
		help.getItems().addAll(language, helpI, about);
		setText();
	}
	
	private void setText() {
		ObservableList<MenuItem> ol0 = MENU.getMenus().get(0).getItems();
		ObservableList<MenuItem> ol1 = MENU.getMenus().get(1).getItems();
		ObservableList<MenuItem> ol2 = MENU.getMenus().get(2).getItems();
		for(int i = 0; i < MENU.getMenus().size(); i++)
			MENU.getMenus().get(i).setText(l.get(MENU_ITEMS.get(i)));
		for(int i = 0; i < ol0.size(); i++) 
			ol0.get(i).setText(l.get(FILE_ITEMS.get(i)));
		for(int i = 0; i < ol1.size(); i++) 
			ol1.get(i).setText(l.get(EDIT_ITEMS.get(i)));
		for(int i = 0; i < ol2.size(); i++) 
			ol2.get(i).setText(l.get(HELP_ITEMS.get(i)));
		
	}

	private void save() {
		FileChooser fc = new FileChooser();
		ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Command List Generator file", "*.clg");
		fc.getExtensionFilters().add(extFilter);
		File file = fc.showSaveDialog(null);
		if (file != null) {
			try {
				FileIO.serialize(file.getAbsolutePath(), COMMAND.getText(), OUTPUT.getText(),
						Integer.parseInt(MAX.getText()), Tag.tags.values().toArray(new Tag[Tag.tags.size()]));
				bar.show(l.get("gui.list.toast.exportcomplet"), 5 * 1000);
			} catch (NullPointerException e) {
				bar.show(l.get("gui.list.error.exportfail"), 5 * 1000);
			}
		}
	}
	
	private void export() {
		FileChooser fc = new FileChooser();
		ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Minecraft Function", "*.mcfunction");
		fc.getExtensionFilters().add(extFilter);
		File file = fc.showSaveDialog(null);
		if (file != null) {
			try {
				FileIO.save(file.getAbsolutePath(),OUTPUT.getText());
				bar.show(l.get("gui.list.toast.exportcomplet"), 5 * 1000);
			} catch (NullPointerException e) {
				bar.show(l.get("gui.list.error.exportfail"), 5 * 1000);
			}
		}
	}
	

	private void load() {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("List file", "*.list"));
		File f = fc.showOpenDialog(null);
		try {
			Tuple4<String, String, Integer, Tag[]> content = FileIO.deserialize(f.getAbsolutePath());
			COMMAND.setText(content._1);
			MAX.setText(content._2);
			OUTPUT.setText(content._3.toString());
			bar.show(l.get("gui.list.toast.importcomplet"), 5 * 1000);
		} catch (NullPointerException e) {
			bar.show(l.get("gui.list.error.importfail"), 5 * 1000);
		}
	}

	private void about() {
		JFXDialog dialog = new JFXDialog();

		JFXDialogLayout layout = null;
		try {
			layout = FXMLLoader.load(getClass().getResource("/fxml/About.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		dialog.setContent(layout);
		dialog.show(CommandListGeneratorController.MAIN_PANE);
	}
	
	private void help(CheckMenuItem m) {
		CommandListGenerator.prefs.putBoolean("Activate Help", m.isSelected());
	}
	
	private void changeLanguage(String name, Menu m, CheckMenuItem cmi) {
		if(!name.equals(CommandListGenerator.LANGUAGE.getLanguageName())) {
			for(MenuItem mi : m.getItems())
				if(mi instanceof CheckMenuItem && !mi.getText().equals(name))
					((CheckMenuItem) mi).setSelected(false);
			CommandListGenerator.LANGUAGE = Language.getLanguage(Locale.fromName(name));
			l = CommandListGenerator.LANGUAGE;
			CommandListGenerator.prefs.put("Language", name);
			setText();
			FunctionalityController.refreshLanguage();
			CommandListGeneratorController.CONTROLLER.setText();
			HelpFunctionality.refreshText();
			List.refreshText();
		} else
			cmi.setSelected(true);
			
	}

}
