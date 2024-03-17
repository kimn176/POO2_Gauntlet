package scenes.multiPlayer;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class MultiListener implements EventHandler<KeyEvent> {
    @Override
    public void handle(KeyEvent event) {
        System.out.println("Multi: " + event.getText());
    }
}
