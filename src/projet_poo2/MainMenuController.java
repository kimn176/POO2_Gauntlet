package projet_poo2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    public VBox vbox;
    @FXML
    public StackPane pane;
    @FXML
    Button optionButton;
    @FXML
    Button playButton;
    @FXML
    Button exitButton;
    @FXML
    private ImageView imageView = new ImageView();

    @FXML
    public void playAction(ActionEvent event) {
        System.out.println("Action Jouer");
        Projet_POO2 pooprojet = (Projet_POO2) Projet_POO2.getApp();
        pooprojet.showEditeur();
    }

    @FXML
    public void optionAction(ActionEvent event) {
        System.out.println("Action Option");
    }

    @FXML
    public void exitAction(ActionEvent event) throws Exception {
        System.out.println("Action Quitter");
        Projet_POO2.app.stop();
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
