package scenes.choixPlayer;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class ChoixPlayerListener implements EventHandler<KeyEvent> {
    @Override
    public void handle(KeyEvent event) {
        System.out.println("Choisir Player: " + event.getText());
    }
}
