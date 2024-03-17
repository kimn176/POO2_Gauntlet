package scenes.editor;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class EditeurListener implements EventHandler<KeyEvent> {
    @Override
    public void handle(KeyEvent event) {
        System.out.println("Editeur: " + event.getText());
    }
}
