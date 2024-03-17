package listeneer.option;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class OptionListener implements EventHandler<KeyEvent> {
    @Override
    public void handle(KeyEvent event) {
        System.out.println("Option: " + event.getText());
    }
}
