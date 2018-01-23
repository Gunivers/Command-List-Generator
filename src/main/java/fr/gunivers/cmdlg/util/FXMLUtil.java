package fr.gunivers.cmdlg.util;

import fr.gunivers.cmdlg.Main;

import java.net.URL;

public class FXMLUtil {

    public static URL getFXMLURL(String name) {
        URL url = Main.class.getResource("../../../fxml/" + name + ".fxml");
        return url == null ? Main.class.getResource("/fxml/" + name + ".fxml") : url;
    }

}
