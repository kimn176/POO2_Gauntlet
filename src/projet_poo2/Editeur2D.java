/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_poo2;

/**
 *
 * @author kimngan
 */
import java.util.Arrays;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Editeur2D extends Application {

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

        // Créer la carte
        Rectangle map = new Rectangle(600, 400);
        map.setFill(Color.WHITE);

        // Créer la scène
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setLeft(leftPane);
        root.setCenter(map);

        // Ajouter les objets au bandeau gauche
//        for (String object : Arrays.asList("Ground", "Wall", "Enemy")) {
//            Rectangle objectButton = new Rectangle(100, 50);
//            objectButton.setOnMouseClicked(event -> {
//                // Ajouter l'objet à la carte
//            });
//            leftPane.getChildren().add(objectButton);
//        }

        // Ajouter le zoom au bandeau gauche
        Slider zoomSlider = new Slider();
        zoomSlider.setMax(10);
        zoomSlider.setValue(1);
        zoomSlider.setOnMouseReleased(event -> {
            // Appliquer le zoom à la carte
        });
        leftPane.getChildren().add(zoomSlider);

    
        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setTitle("Plan Editeur du Gaunlet");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
