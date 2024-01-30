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

public class Editeur2D extends BorderPane {
    
    private Carte carte;

    public Editeur2D(Projet_POO2 projet_poo2) {
        // Créer la barre de menu
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("Fichier");
        MenuItem menuItemNew = new MenuItem("Nouvelle carte");
        MenuItem menuItemLoad = new MenuItem("Charger une carte");
        menuFile.getItems().addAll(menuItemNew, menuItemLoad);
        
        Menu menuOption = new Menu("Option");
        MenuItem menuItemQuitter = new MenuItem("Quiter");
        menuOption.getItems().add(menuItemQuitter);
        
        menuBar.getMenus().addAll(menuFile, menuOption);

        // Créer le bandeau gauche
        FlowPane leftPane = new FlowPane();
        this.setPrefWidth(200);
        this.setStyle("-fx-background-color: lightgray");
        
        // Des objets dans le bandeau gauche
        Button exit = createObjectButton("", "sprites/exit.png");
        Button key = createObjectButton("", "sprites/key.png");
        Button keyring = createObjectButton("", "sprites/keyring.png");
        Button potion_life = createObjectButton("", "sprites/potion_life.png");
        Button potion_defense = createObjectButton("", "sprites/potion_defense.png");
        Button potion_magic = createObjectButton("", "sprites/potion_magic.png");
        Button potion_physical = createObjectButton("", "sprites/potion_physical.png");
        Button potion_poison = createObjectButton("", "sprites/potion_poison.png");
        Button potion_speed = createObjectButton("", "sprites/potion_speed.png");
        Button food = createObjectButton("", "sprites/food.png");
        Button treasure = createObjectButton("", "sprites/treasure.png");
        Button wall = createObjectButton("", "sprites/wall.png");
        Button smart_bomb = createObjectButton("", "sprites/smart_bomb.png");
        Button spawner_ghost = createObjectButton("", "sprites/spawner_ghost.png");
        Button spawner_grunt = createObjectButton("", "sprites/spawner_grunt.png");
        Button floor = createObjectButton("", "sprites/floor.png");
        
        leftPane.getChildren().addAll(exit,key,keyring,potion_life, potion_defense,
                potion_magic, potion_physical, potion_poison, potion_speed, food, 
                treasure, wall, smart_bomb, spawner_ghost, spawner_grunt, floor);
        
        // Ajouter le zoom au bandeau gauche
        Slider zoomSlider = new Slider();
        zoomSlider.setMax(10);
        zoomSlider.setValue(1);
        zoomSlider.setOnMouseReleased(event -> 
            carte.updateSize()
        );
        leftPane.getChildren().add(zoomSlider);
        
        // Créer la carte
        carte = new Carte(zoomSlider);

        // Créer la scène
        this.setTop(menuBar);
        this.setLeft(leftPane);
        this.setCenter(carte.getCartePane());

        menuItemQuitter.setOnAction(action -> projet_poo2.showHome());

    }
    
    
    // Créer un bouton pour un objet
    private Button createObjectButton(String objectName, String imagePath) {
        Image image = new Image(getClass().getResource(imagePath).toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50); 
        imageView.setFitHeight(50);

        Button button = new Button(objectName, imageView);
        button.setPrefSize(80, 80); // Ajuster la taille du bouton

        return button;
    }

}
