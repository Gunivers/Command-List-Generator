package net.gunivers.cmdlg.util;

import net.gunivers.cmdlg.Main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class Util {

    public static URL getFXMLURL(String name) {
        URL url = Main.class.getResource("../../../fxml/" + name + ".fxml");
        return url == null ? Main.class.getResource("/fxml/" + name + ".fxml") : url;
    }

    public static URL getSVGURL(String name) {
        URL url = Main.class.getResource("../../../fxml/" + name + ".svg");
        return url == null ? Main.class.getResource("/fxml/" + name + ".svg") : url;
    }

    public static void copyStream(File file, InputStream in) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
