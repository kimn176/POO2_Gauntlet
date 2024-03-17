package listeneer.game;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class GameListener implements EventHandler<KeyEvent> {
    @Override
    public void handle(KeyEvent event) {
        System.out.println("Game: " + event.getText());
    }
}
