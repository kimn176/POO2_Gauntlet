package scenes.singlePlayer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import util.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class SinglePlayerController implements Initializable {
    @FXML
    public HBox hbox = new HBox();;
//
//    @FXML
//    Button optionButton;
//    @FXML
//    Button editorButton;
//    @FXML
//    Button exitButton;
//
//    @FXML
//    public void editorAction(ActionEvent event) {
//        System.out.println("Action Editer");
//        Window poo_projet = Window.app;
//        poo_projet.showEditeur();
//    }
//
//    @FXML
//    public void optionAction(ActionEvent event) {
//        System.out.println("Action Option");
//        Window.app.showOption();
//    }
//
//    @FXML
//    public void exitAction(ActionEvent event) throws Exception {
//        System.out.println("Action Quitter");
//        Window.app.stop();
//        System.exit(0);
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Création d'une image pour l'arrière-plan
        Image backgroundImage = new Image(getClass().getResource("/sprites/page_accueil.jpg").toExternalForm());
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        hbox.setBackground(new Background(background));
    }
}

