package projet_poo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    public VBox vbox;

    @FXML
    Button optionButton;
    @FXML
    Button editorButton;
    @FXML
    Button exitButton;

    @FXML
    public void editorAction(ActionEvent event) {
        System.out.println("Action Editer");
        Projet_POO2 poo_projet = Projet_POO2.app;
        poo_projet.showEditeur();
    }

    @FXML
    public void optionAction(ActionEvent event) {
        System.out.println("Action Option");
        Projet_POO2.app.showOption();
    }

    @FXML
    public void exitAction(ActionEvent event) throws Exception {
        System.out.println("Action Quitter");
        Projet_POO2.app.stop();
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Création d'une image pour l'arrière-plan
        Image backgroundImage = new Image(getClass().getResource("sprites/page_accueil.jpg").toExternalForm());
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        vbox.setBackground(new Background(background));
    }
}
