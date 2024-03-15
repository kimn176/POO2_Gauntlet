package scenes.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import util.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    public VBox vbox;
    public Button testButton;

    @FXML
    Button singleButton;
    @FXML
    Button MultiplButton;
    @FXML
    Button optionButton;
    @FXML
    Button editorButton;
    @FXML
    Button exitButton;

    @FXML
    public void editorAction(ActionEvent event) {
        System.out.println("Action Editer");
        Window poo_projet = Window.app;
        poo_projet.showEditeur();
    }

    @FXML
    public void singlePlayerAction(ActionEvent event) {
        System.out.println("Action Single Player");
        Window.app.showSinglePlayer();
    }

    @FXML
    public void multipPlayerAction(ActionEvent event) {
        System.out.println("Action Multipl Player");
        Window.app.showMultiPlayer();
    }

    @FXML
    public void optionAction(ActionEvent event) {
        System.out.println("Action Option");
        Window.app.showOption();
    }

    @FXML
    public void exitAction(ActionEvent event) throws Exception {
        System.out.println("Action Quitter");
        Window.app.stop();
        System.exit(0);
    }

    @FXML
    public void gameAction(ActionEvent actionEvent) {
        System.out.println("Game Action");
        Window.app.showGame(null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Création d'une image pour l'arrière-plan
        Image backgroundImage = new Image(getClass().getResource("/sprites/page_accueil.jpg").toExternalForm());
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        vbox.setBackground(new Background(background));
    }
}
