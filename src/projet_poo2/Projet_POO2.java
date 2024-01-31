/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projet_poo2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author kimngan
 */


public class Projet_POO2 extends Application {

    Scene scene = new Scene(new Page_accueil(this), 1000, 800);
    Scene editeur = new Scene(new Editeur2D(this), 1000, 800);
    Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        showHome();
        primaryStage.show();

    }

    public void showHome(){

        primaryStage.setTitle("Page d'Accueil du Jeu");
        primaryStage.setScene(scene);

    }

    public void showEditeur(){

        primaryStage.setTitle("Editeur");
        primaryStage.setScene(editeur);

    }

    public static void main(String[] args) {
        launch(args);
    }

}