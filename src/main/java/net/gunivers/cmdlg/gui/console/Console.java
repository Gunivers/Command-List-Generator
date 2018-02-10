package net.gunivers.cmdlg.gui.console;

import net.gunivers.cmdlg.Main;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Console
{

	private static final JTextPane allTextPane = new JTextPane();
	private static final JTextPane infoTextPane = new JTextPane();
	private static final JTextPane debugTextPane = new JTextPane();
	private static final JTextPane errorTextPane = new JTextPane();

	private static JTabbedPane tabbedPane = new JTabbedPane();

	public static JFrame mainFrame = new JFrame("Console");

	/**
	 * Start console.
	 */
	public static void start()
	{
		//if mainFrame is visible to prevent bug
		if (!mainFrame.isVisible())
		{

			//set the new print stream.
			System.setOut(new PrintStream(new ConsoleOutputStream(), true));
			System.setErr(new PrintStream(new ConsoleErrOutputStream(), true));

			//init all tab
			initTab(allTextPane);
			initTab(infoTextPane);
			initTab(errorTextPane);
			initTab(debugTextPane);

			//adding tab to the pane
			tabbedPane.addTab("Info Log", new JTable().add(new JScrollPane(infoTextPane)));
			tabbedPane.addTab("Debug Log", new JTable().add(new JScrollPane(debugTextPane)));
			tabbedPane.addTab("Error Log", new JTable().add(new JScrollPane(errorTextPane)));
			tabbedPane.addTab("All Log", new JTable().add(new JScrollPane(allTextPane)));

			//#FixJava (tab has not the good pane)
			tabbedPane.setComponentAt(0, new JScrollPane(infoTextPane));
			tabbedPane.setComponentAt(1, new JScrollPane(debugTextPane));
			tabbedPane.setComponentAt(2, new JScrollPane(errorTextPane));
			tabbedPane.setComponentAt(3, new JScrollPane(allTextPane));

			tabbedPane.getComponentAt(0).setFont(Font.getFont("Roboto"));
			tabbedPane.getComponentAt(1).setFont(Font.getFont("Roboto"));
			tabbedPane.getComponentAt(2).setFont(Font.getFont("Roboto"));
			tabbedPane.getComponentAt(3).setFont(Font.getFont("Roboto"));
			//#FixJava 2 (ScrollPane not refreshing on resize)
			tabbedPane.addComponentListener(new ComponentAdapter()
			{
				@Override
				public void componentResized(ComponentEvent e)
				{
					JTabbedPane tabbedPane = (JTabbedPane) e.getComponent();
					int tabCount = tabbedPane.getTabCount();
					for (int i = 0; i < tabCount; i++)
					{
						Component c = tabbedPane.getComponentAt(i);
						c.setPreferredSize(new Dimension(c.getSize().width, c.getPreferredSize().height));
					}
				}
			});

			//set the dimension of the windows
			mainFrame.setPreferredSize(new Dimension(750, 400));
			//set the icon
			mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(Console.class.getResource("/icon/console.png")));
			//add tab pane
			mainFrame.add(tabbedPane, BorderLayout.CENTER);
			//sync close with the main app
			mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			//pack the main for validate all option
			mainFrame.pack();

			logDebug("Console Start");
		}
	}

	/**
	 * Stop console
	 */
	public static void stop()
	{
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement element = stackTrace[2];
		if (element.getClassName().equals(Main.class.getName()))
			mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
	}

	/**
	 * Use for add a info on the console, you cant chose the color
	 *
	 * @param text
	 * @param color
	 */
	private static void addText(JTextPane pane, String text, Color color)
	{
		//get the StyledDoc
		StyledDocument doc = pane.getStyledDocument();
		//add a new Style for StyledDoc
		Style style = pane.addStyle("Color Style", null);
		//set the new Style
		StyleConstants.setForeground(style, color);

		if (!text.endsWith("\n"))
			text = text + "\n";


		try
		{
			//insert string to TextPane
			doc.insertString(doc.getLength(), text, style);
		} catch (BadLocationException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Log info on the console
	 *
	 * @param text
	 */
	public static void logInfo(String text)
	{
		Date date = new Date();
		String d = new SimpleDateFormat("HH:mm:ss").format(date);
		addText(infoTextPane, d + " [INFO]: " + text, Color.ORANGE);
		addText(allTextPane, d + " [INFO]: " + text, Color.ORANGE);
	}

	/**
	 * Log debug on the console
	 *
	 * @param text
	 */
	public static void logDebug(String text)
	{
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement element = stackTrace[2];
		String d = new SimpleDateFormat("HH:mm:ss").format(new Date());
		String t = d + " <" + element.getClassName() + ":" + element.getLineNumber() + ":" + element.getMethodName() + ">";
		t = t.endsWith(">>") ? t.replace(">>", ">") + " " : t + " ";
		addText(debugTextPane, t + " [DEBUG]: " + text, Color.MAGENTA);
		addText(allTextPane, t + " [DEBUG]: " + text, Color.MAGENTA);
	}

	/**
	 * For update the scroll bar on the bottom.
	 */
	private static void updateCurrentJScrollPane()
	{
		///get the current
		JScrollPane scrollPane = (JScrollPane) tabbedPane.getSelectedComponent();
		//get scroll bar
		JScrollBar bar = scrollPane.getVerticalScrollBar();
		//if scroll bar existing
		if (bar != null)
		{
			//set bar at se max value
			bar.setValue(bar.getMaximum());
		}
	}

	/**
	 * The class to sync System.out.print to the console.
	 */
	public static class ConsoleOutputStream extends OutputStream
	{

		private StringBuilder builder = new StringBuilder();

		@Override
		public void write(int b)
		{
			builder.append((char) b);

			if (builder.toString().endsWith("\n"))
			{
				addText(infoTextPane, builder.toString(), Color.WHITE);
				addText(allTextPane, builder.toString(), Color.WHITE);
				builder = new StringBuilder();
				updateCurrentJScrollPane();
			}
		}
	}

	/**
	 * The class to sync System.out.print to the console.
	 */
	public static class ConsoleErrOutputStream extends OutputStream
	{

		private StringBuilder builder = new StringBuilder();

		@Override
		public void write(int b)
		{
			builder.append((char) b);

			if (builder.toString().endsWith("\n"))
			{
				String message = builder.toString();
				addText(errorTextPane, message, message.contains("net.gunivers") ? Color.WHITE : Color.RED);
				addText(allTextPane, message, message.contains("net.gunivers") ? Color.WHITE : Color.RED);
				builder = new StringBuilder();
				updateCurrentJScrollPane();
			}
		}
	}

	/**
	 * init tab for custom display
	 *
	 * @param textPane
	 */
	private static void initTab(JTextPane textPane)
	{
		textPane.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		textPane.setEditable(false);
		textPane.setBackground(Color.BLACK);
	}
}