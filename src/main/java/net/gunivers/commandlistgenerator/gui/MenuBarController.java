package net.gunivers.commandlistgenerator.gui;

import java.io.File;

import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import net.gunivers.commandlistgenerator.CommandListGenerator;
import net.gunivers.commandlistgenerator.util.FileIO;
import net.gunivers.commandlistgenerator.util.Tag;
import net.gunivers.core.language.Language;
import net.gunivers.core.utils.tuple.Tuple4;

public class MenuBarController {
	
	private JFXSnackbar bar = new JFXSnackbar(CommandListGeneratorController.MAIN_PANE);
	private Language l = CommandListGenerator.LANGUAGE;
	
	private final MenuBar MENU;
	private final JFXTextField COMMAND;
	private final JFXTextField MAX;	
	private final JFXTextArea OUTPUT;	
	
	public MenuBarController(MenuBar menu, JFXTextField command, JFXTextField max, JFXTextArea output) {
		this.MENU = menu;
		this.COMMAND = command;
		this.OUTPUT = output;
		this.MAX = max;
		System.out.println(MENU.getMenus().size());
		final Menu file = MENU.getMenus().get(0);
		file.getItems().clear();
		file.setText(l.get("gui.commandlistgenerator.menu.file"));
		MenuItem open = new MenuItem(l.get("gui.commandlistgenerator.menu.file.open"));
		open.setOnAction(actionEvent -> load());
		MenuItem save = new MenuItem(l.get("gui.commandlistgenerator.menu.file.save"));
		save.setOnAction(actionEvent -> save());
		MenuItem export = new MenuItem(l.get("gui.commandlistgenerator.menu.file.export"));
		file.getItems().addAll(open, save, export);
		final Menu edit = MENU.getMenus().get(1);
		edit.getItems().clear();
		final Menu help = MENU.getMenus().get(2);
		help.getItems().clear();
		help.setText(l.get("gui.commandlistgenerator.menu.help"));
		Menu language = new Menu(l.get("gui.commandlistgenerator.menu.help.language"));
		language.getItems().addAll(new MenuItem("English"), new MenuItem("Français"));
		CheckMenuItem helpI = new CheckMenuItem(l.get("gui.commandlistgenerator.menu.help.help"));
		MenuItem about = new MenuItem(l.get("gui.commandlistgenerator.menu.help.about"));
		help.getItems().addAll(language, helpI, about);	
	}
	
	private void save() {
		FileChooser fc = new FileChooser();
        ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Command List Generator file", "*.clg");
        fc.getExtensionFilters().add(extFilter);
        File file = fc.showSaveDialog(null);
        if(file != null){
            try {
                FileIO.serialize(file.getAbsolutePath(), COMMAND.getText(), OUTPUT.getText(), Integer.parseInt(MAX.getText()), Tag.tags.values().toArray(new Tag[Tag.tags.size()]));
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

}
