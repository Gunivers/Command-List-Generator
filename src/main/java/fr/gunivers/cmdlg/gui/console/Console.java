package fr.gunivers.cmdlg.gui.console;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Console {

    private static final JTextPane allTextPane = new JTextPane();
    private static JFrame mainFrame = new JFrame("Console");
    private static JScrollPane allScrollPane = null;

    public static boolean DEBUG = true;

    /**
     * Start console.
     */
    public static void start() {
        System.setOut(new PrintStream(new ConsoleOutputStream(), true));
        System.setErr(new PrintStream(new ConsoleErrOutputStream(), true));

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(new Dimension(650, 400));
        mainFrame.setResizable(false);

        allTextPane.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        allTextPane.setEditable(false);
        allTextPane.setBackground(Color.BLACK);


        allScrollPane = new JScrollPane(allTextPane);

        allTextPane.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSize();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSize();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSize();
            }

            private void updateSize() {
                allScrollPane.revalidate();
            }
        });

        mainFrame.add(allScrollPane);
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
        StyledDocument doc = allTextPane.getStyledDocument();
        Style style = allTextPane.addStyle("Color Style", null);
        StyleConstants.setForeground(style, color);

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
    private static void addText(String text, Color color) {
        StyledDocument doc = allTextPane.getStyledDocument();
        Style style = allTextPane.addStyle("Color Style", null);
        StyleConstants.setForeground(style, color);

        if (!text.endsWith("\n"))
            text = text + "\n";


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
    private static void addText(String text) {
        addText(text, Color.WHITE);
    }

    public static void logInfo(String text) {
        Date date = new Date();
        String d = new SimpleDateFormat("HH:mm:ss").format(date);
        addText(d + " [INFO]: " + text, Color.ORANGE);
    }

    public static void logDebug(String text) {
        if (DEBUG) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            StackTraceElement element = stackTrace[2];
            String d = new SimpleDateFormat("HH:mm:ss").format(new Date());
            String t = "<" + element.getClassName() + ":" + element.getLineNumber() + ":" + element.getMethodName() + "> " + d;
            addText(t + " [DEBUG]: " + text, Color.MAGENTA);
        }
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

    /**
     * The class to sync System.out.print to the console.
     */
    public static class ConsoleErrOutputStream extends OutputStream {
        @Override
        public void write(int b) throws IOException {
            addCmd(String.valueOf(Character.valueOf((char) b)), Color.RED);
        }
    }
}
