/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projet_poo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


/**
 *
 * @author kimngan, ninogimenez, lucasespinar
 */


public class Projet_POO2 extends Application {


    Scene scene;
    Scene editeur = new Scene(new Editeur2D(this), 1000, 800);
    Stage primaryStage;

    public static Application app;

    public Projet_POO2() throws IOException {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        URL url = getClass().getResource("main_menu.fxml");
        scene = new Scene(FXMLLoader.load(url));

        this.primaryStage = primaryStage;
        showHome();
        primaryStage.show();
        Projet_POO2.app = this;

    }

    public void showHome(){

        primaryStage.setTitle("Page d'Accueil du Jeu");
        primaryStage.setScene(scene);
    }

    public void showEditeur(){

        primaryStage.setTitle("Editeur");
        primaryStage.setScene(editeur);

    }

    public static Application getApp() {
        return app;
    }

    public static void main(String[] args) {
        String userDirectory = System.getProperty("user.dir");
        System.out.println(userDirectory);
        launch(args);
    }

}