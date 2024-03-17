package scenes.singlePlayer;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class SingleListener implements EventHandler<KeyEvent> {
    @Override
    public void handle(KeyEvent event) {
        System.out.println("Single: " + event.getText());
    }
}
