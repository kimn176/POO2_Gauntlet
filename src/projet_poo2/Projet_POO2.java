/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projet_poo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.Objects;


/**
 *
 * @author kimngan, ninogimenez, lucasespinar
 */

public class Projet_POO2 extends Application {


    private Scene scene;
    private Scene editeur;
    private Scene option;
    private Stage primaryStage;

    /*
    * code pays ISO 3166
    */
    private static String lang = "FR";

    public static Projet_POO2 app;

    public Projet_POO2() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Projet_POO2.app = this;

        URL urlMainScene = getClass().getResource("main_menu.fxml");
        assert urlMainScene != null;
        scene = new Scene(FXMLLoader.load(urlMainScene));

        URL urlEditor = getClass().getResource("Editor.fxml");
        assert urlEditor != null;
        editeur = new Scene(FXMLLoader.load(urlEditor));

        URL urlOption = getClass().getResource("Option.fxml");
        assert urlOption != null;
        option = new Scene(FXMLLoader.load(urlOption));

        this.primaryStage = primaryStage;
        showHome();
        URL urlIco = getClass().getResource("img/icon.png");
        assert urlIco != null;
        Image img = new Image(urlIco.toString());
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getIcons().add(img);
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

    public void showOption(){
        primaryStage.setTitle("options");
        primaryStage.setScene(option);
    }

    public static Projet_POO2 getApp() {
        return app;
    }

    public void setDarkMode() {
        option.getStylesheets().add(Objects.requireNonNull(getClass().getResource("stylesheet/darkStyle.css")).toExternalForm());
        editeur.getStylesheets().add(Objects.requireNonNull(getClass().getResource("stylesheet/darkStyle.css")).toExternalForm());
    }

    public String getLang() {
        return lang;
    }

    public void setLang() {

    }

    public static void main(String[] args) {
        System.out.println("Game Started");
        launch(args);
    }

}