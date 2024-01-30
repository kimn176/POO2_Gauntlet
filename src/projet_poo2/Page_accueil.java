/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_poo2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author kimngan
 */

public class Page_accueil extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Création d'une image pour l'arrière-plan
        Image backgroundImage = new Image(getClass().getResource("sprites/page_accueil.jpg").toExternalForm());
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        StackPane root = new StackPane();
        root.setBackground(new Background(background));

        // Création de boutons
        Button jouerButton = new Button("Jouer");
        Button optionsButton = new Button("Options");
        Button quitterButton = new Button("Quitter");
        
        // Style pour les boutons
        jouerButton.setStyle("-fx-background-color: #BDB76B; -fx-text-fill: black;");
        optionsButton.setStyle("-fx-background-color: #BDB76B; -fx-text-fill: black;");
        quitterButton.setStyle("-fx-background-color: #BDB76B; -fx-text-fill: black;");
        
        jouerButton.setPrefSize(150, 40);
        optionsButton.setPrefSize(150, 40);
        quitterButton.setPrefSize(150, 40);

        // Ajout des boutons à la mise en page
        root.getChildren().addAll(jouerButton, optionsButton, quitterButton);

        // Mise en page des boutons
        StackPane.setMargin(jouerButton, new Insets(0, 0, 100, 0));
        StackPane.setMargin(optionsButton, new Insets(0, 0, 0, 0));
        StackPane.setMargin(quitterButton, new Insets(0, 0, -100, 0));

        // Gestion des actions des boutons 
        jouerButton.setOnAction(e -> System.out.println("Action Jouer"));
        optionsButton.setOnAction(e -> System.out.println("Action Options"));
        quitterButton.setOnAction(e -> primaryStage.close());
       
        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setTitle("Page d'Accueil du Jeu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

