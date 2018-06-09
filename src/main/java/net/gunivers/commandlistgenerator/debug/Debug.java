package net.gunivers.commandlistgenerator.debug;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import net.gunivers.commandlistgenerator.ShutdownThread;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class Debug
{
    public static Boolean DEBUG = false;

    public static Stage STAGE;

    public static Thread DEBUG_THREAD;

    private static Controller CONTROLLER;

    public static void initialize(String arg)
    {
        DEBUG = true;

        if (arg.split("=").length > 1)
        {
            DEBUG = Boolean.valueOf(arg.split("=")[1]);
        }

        if (DEBUG)
        {
            System.setErr(new PrintStream(new ConsoleOutPutStream()));
            System.setOut(new PrintStream(new ConsoleOutPutStream()));

            DEBUG_THREAD = new Thread(() -> Platform.runLater(() ->
            {
                FXMLLoader loader = null;

                try
                {
                    loader = new FXMLLoader(new URL(Debug.class.getResource("/fxml/Debug.fxml").toExternalForm()));

                    loader.setController(CONTROLLER = new Controller());

                    loader.load();

                } catch (IOException e)
                {
                    e.printStackTrace();
                }

                STAGE = new Stage();
                STAGE.setScene(new Scene(loader.getRoot()));
                STAGE.setTitle("Debug Console");
                STAGE.show();
                STAGE.setOnCloseRequest(new ShutdownThread());
            }));
            DEBUG_THREAD.start();
        }
    }

    private static class ConsoleOutPutStream extends OutputStream
    {
        private StringBuilder builder = new StringBuilder();

        @Override
        public void write(int b) throws IOException
        {
            builder.append((char) b);

            String d = new SimpleDateFormat("HH:mm:ss").format(new Date());

            if (builder.toString().endsWith("\n"))
            {
                CONTROLLER.append("[" + d + "] " + builder.toString());
                builder = new StringBuilder();
            }
        }
    }

    private static class Controller implements Initializable
    {
        @FXML
        private TextArea TEXT_AREA;

        @Override
        public void initialize(URL location, ResourceBundle resources)
        {

        }

        public void append(String line)
        {
            TEXT_AREA.appendText(line);
        }
    }
}
