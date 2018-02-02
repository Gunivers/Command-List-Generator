package net.gunivers.cmdlg.util;

import net.gunivers.cmdlg.Main;

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

}
