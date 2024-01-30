/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_poo2;

/**
 *
 * @author kimngan
 */

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.util.Map;

public class Editeur2D extends BorderPane {

    private final Projet_POO2 projet_poo2;

    public Editeur2D(Projet_POO2 projet_poo2) {

        this.projet_poo2 = projet_poo2;

        this.createTopBar();

        Carte carte = new Carte(10);

        this.createLeftPane(carte);

        // Créer la scène
        this.setCenter(carte);

    }

    private void createTopBar(){
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("Fichier");
        MenuItem menuItemNew = new MenuItem("Nouvelle carte");
        MenuItem menuItemLoad = new MenuItem("Charger une carte");
        menuFile.getItems().addAll(menuItemNew, menuItemLoad);

        Menu menuOption = new Menu("Option");
        MenuItem menuItemQuitter = new MenuItem("Quiter");
        menuItemQuitter.setOnAction(action -> projet_poo2.showHome());
        menuOption.getItems().add(menuItemQuitter);

        menuBar.getMenus().addAll(menuFile, menuOption);

        this.setTop(menuBar);
    }

    private Slider createLeftPane(Carte carte){

        Slider zoomSlider = new Slider();
        zoomSlider.setMax(10);
        zoomSlider.setMin(1);
        zoomSlider.setValue(1);
        zoomSlider.setOnMouseReleased(event -> {
            double newSize = 200*zoomSlider.getValue();
        });

        // Créer le bandeau gauche
        FlowPane leftPane = new FlowPane();
        this.setPrefWidth(200);
        this.setStyle("-fx-background-color: lightgray");

        for(Map.Entry<String, Image> imageEntry : carte.allPage().entrySet()){

            ImageView imageView = new ImageView(imageEntry.getValue());
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);

            Button button = new Button(imageEntry.getKey(), imageView);
            button.setPrefSize(80, 80); // Ajuster la taille du bouton

            leftPane.getChildren().add(button);

            button.setOnAction(action -> carte.current = imageEntry.getKey());

        }

        leftPane.getChildren().add(zoomSlider);

        this.setLeft(leftPane);

        return zoomSlider;
    }

}
