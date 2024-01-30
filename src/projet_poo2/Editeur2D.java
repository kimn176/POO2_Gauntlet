/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_poo2;

/**
 *
 * @author kimngan
 */
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Editeur2D extends Application {
    
    private Carte carte;

    @Override
    public void start(Stage primaryStage) {
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
        leftPane.setPrefWidth(200);
        leftPane.setStyle("-fx-background-color: lightgray");
        
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
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setLeft(leftPane);
        root.setCenter(carte.getCartePane());

        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setTitle("Plan Editeur du Gaunlet");
        primaryStage.setScene(scene);
        primaryStage.show();
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

    public static void main(String[] args) {
        launch(args);
    }
}
