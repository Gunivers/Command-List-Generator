package net.gunivers.cmdlg.gui.handler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import net.gunivers.cmdlg.Main;
import net.gunivers.cmdlg.gui.theme.Theme;

public class SelectThemeHandler implements EventHandler<ActionEvent>
{
	private MenuItem menuItem;

	public SelectThemeHandler(MenuItem item) {
		this.menuItem = item;
	}

	@Override
	public void handle(ActionEvent event)
	{
		Main.loadTheme(Theme.getThemeByName(menuItem.getText()));
	}
}
