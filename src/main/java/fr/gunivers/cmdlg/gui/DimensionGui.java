package fr.gunivers.cmdlg.gui;

import fr.gunivers.cmdlg.gui.console.Console;

public class DimensionGui {

    private int height;
    private int width;
    private int prefHeigt;
    private int prefWidth;
    private String name;

    public DimensionGui(int width, int height, int prefWidth, int prefHeigt, String name) {
        this.height = height;
        this.width = width;
        this.prefWidth = prefWidth;
        this.prefHeigt = prefHeigt;
        this.name = name;
        Console.logDebug("new DimensionGui(width=" + width + ", height=" + height + ", prefWidth=" + prefWidth + " ,prefWidth=" + prefWidth + ", name=" + name + ")");
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getPrefHeigt() {
        return prefHeigt;
    }

    public int getPrefWidth() {
        return prefWidth;
    }

    @Override
    public String toString() {
        return String.valueOf("gui-" + name + "-" + getWidth() + "x" + getHeight());
    }
}
