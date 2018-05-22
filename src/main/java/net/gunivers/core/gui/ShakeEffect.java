package net.gunivers.core.gui;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.TextInputControl;
import javafx.util.Duration;

public class ShakeEffect {

	public static void shake(Node n) {
		Timeline tl = new Timeline(
				new KeyFrame(Duration.millis(0), new KeyValue(n.translateXProperty(), 0,	Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.millis(100), new KeyValue(n.translateXProperty(), -10, Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.millis(200), new KeyValue(n.translateXProperty(), 10, Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.millis(300), new KeyValue(n.translateXProperty(), -10, Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.millis(400), new KeyValue(n.translateXProperty(), 10, Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.millis(500), new KeyValue(n.translateXProperty(), -10, Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.millis(600), new KeyValue(n.translateXProperty(), 10, Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.millis(700), new KeyValue(n.translateXProperty(), -10, Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.millis(800), new KeyValue(n.translateXProperty(), 10, Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.millis(900), new KeyValue(n.translateXProperty(), -10, Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.millis(1000), new KeyValue(n.translateXProperty(), 0, Interpolator.EASE_BOTH))
        );
		tl.setDelay(Duration.seconds(0.2));
		tl.play();
	}
	
	public static void shake(Node... n) {
		for(Node node : n)
			shake(node);
	}
	
	public static boolean isFullOrElseShake(TextInputControl... nodes) {
		boolean isFull = true;
		for(TextInputControl n : nodes)
			if(n.getText().equals("")) {
				shake(n);
				isFull = false;
			}
		return isFull;
	}
}
