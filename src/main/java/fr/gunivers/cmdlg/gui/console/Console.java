package fr.gunivers.cmdlg.gui.console;

import fr.gunivers.cmdlg.Main;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Console {

    private static final JTextPane textPane = new JTextPane();
    private static JFrame mainFrame = new JFrame("Console");
    private static JScrollPane scrollPane = new JScrollPane();


    /**
     * Start console.
     */
    public static void start() {
        System.setOut(new PrintStream(Main.newOutputStream, true));
        System.setErr(new PrintStream(Main.newOutputStream, true));

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        textPane.setPreferredSize(new Dimension(650, 400));
        textPane.setEditable(false);
        textPane.setBackground(Color.BLACK);

        mainFrame.add(new JScrollPane(textPane));
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    /**
     * Stop console
     */
    public static void stop() {
        mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
    }

    /**
     * This add a basic System.out.print
     * @param text
     * @param color
     */
    private static void addCmd(String text, Color color) {
        StyledDocument doc = textPane.getStyledDocument();
        Style style = textPane.addStyle("Color Style", null);
        StyleConstants.setForeground(style, color);

        if(scrollPane.getVerticalScrollBar() != null) {
            JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
            scrollBar.setValue(scrollBar.getMaximum());
        }

        try {
            doc.insertString(doc.getLength(), text, style);
        }
        catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Use for add a info on the console, you cant chose the color
     * @param text
     * @param color
     */
    public static void addText(String text, Color color) {
        StyledDocument doc = textPane.getStyledDocument();
        Style style = textPane.addStyle("Color Style", null);
        StyleConstants.setForeground(style, color);

        if (!text.endsWith("\n"))
            text = text + "\n";

        if(scrollPane.getVerticalScrollBar() != null) {
            JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
            scrollBar.setValue(scrollBar.getMaximum());
        }

        try {
            doc.insertString(doc.getLength(), text, style);
        }
        catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Use for add a info on the console, you cant chose the color
     * @param text
     */
    public static void addText(String text) {
        addText(text, Color.WHITE);
    }

    /**
     * The class to sync System.out.print to the console.
     */
    public static class ConsoleOutputStream extends OutputStream {
        @Override
        public void write(int b) throws IOException {
            addCmd(String.valueOf(Character.valueOf((char) b)), Color.WHITE);
        }
    }
}
