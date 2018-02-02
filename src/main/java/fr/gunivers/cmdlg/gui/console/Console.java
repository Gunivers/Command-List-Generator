package fr.gunivers.cmdlg.gui.console;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Console {

    private static final JTextPane allTextPane = new JTextPane();
    private static final JTextPane infoTextPane = new JTextPane();
    private static final JTextPane debugTextPane = new JTextPane();

    private static JFrame mainFrame = new JFrame("Console");

    /**
     * Start console.
     */
    public static void start() {
        //if mainFrame is visible to prevent bug
        if (!mainFrame.isVisible()) {

            //set the new print stream.
            System.setOut(new PrintStream(new ConsoleOutputStream(), true));
            System.setErr(new PrintStream(new ConsoleErrOutputStream(), true));

            //init the tabbed pane
            JTabbedPane tabbedPane = new JTabbedPane();

            //init all tab
            initTab(allTextPane);
            initTab(infoTextPane);
            initTab(debugTextPane);

            //adding tab to the pane
            tabbedPane.addTab("Info Log", new JTable().add(new JScrollPane(infoTextPane)));
            tabbedPane.addTab("Debug Log", new JTable().add(new JScrollPane(debugTextPane)));
            tabbedPane.addTab("All Log", new JTable().add(new JScrollPane(allTextPane)));

            //#FixJava (tab has not the good pane)
            tabbedPane.setComponentAt(0, new JScrollPane(infoTextPane));
            tabbedPane.setComponentAt(1, new JScrollPane(debugTextPane));
            tabbedPane.setComponentAt(2, new JScrollPane(allTextPane));

            //#FixJava 2 (ScrollPane not refreshing on resize)
            tabbedPane.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    JTabbedPane tabbedPane = (JTabbedPane) e.getComponent();
                    int tabCount = tabbedPane.getTabCount();
                    for (int i = 0; i < tabCount; i++) {
                        Component c = tabbedPane.getComponentAt(i);
                        c.setPreferredSize(new Dimension(c.getSize().width, c.getPreferredSize().height));
                    }
                }
            });

            //set the dimension of the windows
            mainFrame.setPreferredSize(new Dimension(750, 400));
            //add tab pane
            mainFrame.add(tabbedPane, BorderLayout.CENTER);
            //sync close with the main app
            mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            //pack the main for validate all option
            mainFrame.pack();
            //set main frame visible
            mainFrame.setVisible(true);
        }
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
        //get the StyledDoc
        StyledDocument doc = allTextPane.getStyledDocument();
        //add a new Style for StyledDoc
        Style style = allTextPane.addStyle("Color Style", null);
        //set the new Style
        StyleConstants.setForeground(style, color);

        try {
            //insert string to TextPane
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
    private static void addText(JTextPane pane, String text, Color color) {
        //get the StyledDoc
        StyledDocument doc = pane.getStyledDocument();
        //add a new Style for StyledDoc
        Style style = pane.addStyle("Color Style", null);
        //set the new Style
        StyleConstants.setForeground(style, color);

        if (!text.endsWith("\n"))
            text = text + "\n";


        try {
            //insert string to TextPane
            doc.insertString(doc.getLength(), text, style);
        }
        catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Log info on the console
     * @param text
     */
    public static void logInfo(String text) {
        Date date = new Date();
        String d = new SimpleDateFormat("HH:mm:ss").format(date);
        addText(infoTextPane, d + " [INFO]: " + text, Color.ORANGE);
        addText(allTextPane, d + " [INFO]: " + text, Color.ORANGE);
    }

    /**
     * Log debug on the console
     * @param text
     */
    public static void logDebug(String text) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];
        String d = new SimpleDateFormat("HH:mm:ss").format(new Date());
        String t = "<" + element.getClassName() + ":" + element.getLineNumber() + ":" + element.getMethodName() + "> " + d;
        addText(debugTextPane, t + " [DEBUG]: " + text, Color.MAGENTA);
        addText(allTextPane, t + " [DEBUG]: " + text, Color.MAGENTA);
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

    /**
     * init tab for custom display
     * @param textPane
     */
    private static void initTab(JTextPane textPane) {
        textPane.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        textPane.setEditable(false);
        textPane.setBackground(Color.BLACK);
    }
}