package util;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

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

public class Window extends Application {

    private Scene scene;
    private Scene editeur;
    private Scene option;
    private Scene singlePlayer;
    private Stage primaryStage;
    private Scene multiPlayer;

    private Scene game;
    private static String lang = "FR"; //code pays ISO 3166
    public static Window app; //Permet de manipuler le projet et les scenes par methodes

    @Override
    public void start(Stage primaryStage) throws Exception {

        Window.app = this;

        URL urlMainScene = getClass().getResource("../scenes/main/MainMenu.fxml");
        assert urlMainScene != null;
        scene = new Scene(FXMLLoader.load(urlMainScene));

        URL urlsinglePlayer = getClass().getResource("../scenes/singlePlayer/SinglePlayer.fxml");
        assert urlsinglePlayer != null;
        singlePlayer = new Scene(FXMLLoader.load(urlsinglePlayer));

        URL urlmultiPlayer = getClass().getResource("../scenes/multiPlayer/MultiPlayer.fxml");
        assert urlmultiPlayer != null;
        multiPlayer = new Scene(FXMLLoader.load(urlmultiPlayer));

        URL urlEditor = getClass().getResource("../scenes/editor/Editor.fxml");
        assert urlEditor != null;
        editeur = new Scene(FXMLLoader.load(urlEditor));

        URL urlOption = getClass().getResource("../scenes/option/Option.fxml");
        assert urlOption != null;
        option = new Scene(FXMLLoader.load(urlOption));

        URL urlGame = getClass().getResource("../scenes/game/Game.fxml");
        assert urlGame != null;
        game = new Scene(FXMLLoader.load(urlGame));

        this.primaryStage = primaryStage;
        showHome();
        URL urlIco = getClass().getResource("../img/icon.png");
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

    public void showSinglePlayer(){
        primaryStage.setTitle("SinglePlayer");
        primaryStage.setScene(singlePlayer);
    }

    public void showMultiPlayer(){
        primaryStage.setTitle("MultiPlayer");
        primaryStage.setScene(multiPlayer);
    }

    public void showEditeur(){
        primaryStage.setTitle("Editeur");
        primaryStage.setScene(editeur);
    }

    public void showOption(){
        primaryStage.setTitle("Options");
        primaryStage.setScene(option);
    }

    public void showGame(String[] argv) {
        primaryStage.setTitle("Game");
        primaryStage.setScene(game);
    }

    public static Window getApp() {
        return app;
    }

    public void setDarkMode() {
        option.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../stylesheet/darkStyle.css")).toExternalForm());
        editeur.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../stylesheet/darkStyle.css")).toExternalForm());
    }

    public String getLang() {
        return lang;
    }

    public void setLang() {

    }

    public static void main(String[] args) {
        launch(args); //Démarré depuis une autre instance
    }

}
