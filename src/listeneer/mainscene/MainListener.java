package listeneer.mainscene;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class MainListener implements EventHandler<KeyEvent> {
    @Override
    public void handle(KeyEvent event) {
        System.out.println("Main: " + event.getText());
    }
}
