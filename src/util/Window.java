package util;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import character.Warrior;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import listeneer.editeur.EditeurListener;
import listeneer.mainscene.MainListener;
import listeneer.multiplayer.MultiListener;
import listeneer.option.OptionListener;
import listeneer.singleplayer.SingleListener;
import sound.SoundEnum;
import sound.SoundManager;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 *
 * @author kimngan, ninogimenez, lucasespinar
 */

public class Window extends Application {
    private Stage primaryStage;
    private String local = "en";
    public static Window app; //Permet de manipuler le projet et les scenes par methodes

    @Override
    public void start(Stage primaryStage) throws Exception {

        Window.app = this;

        this.primaryStage = primaryStage;
        showHome();
        URL urlIco = getClass().getResource("../img/icon.png");
        assert urlIco != null;
        Image img = new Image(urlIco.toString());
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getIcons().add(img);
        primaryStage.show();

        new SoundManager().playSound(SoundEnum.OUIOUI);
        Warrior warrior = new Warrior();
        Warrior warrior2 = new Warrior();
        System.out.println(warrior.calculateDamage(warrior2));
        warrior2.setDefenseBoost(1);
        System.out.println(warrior.calculateDamage(warrior2));
        System.out.println(warrior.priorityOver(warrior2));

    }

    public Scene generateScene(String fxml, EventHandler<KeyEvent> eventHandler){
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("resources.UIResources", new Locale(local));
            assert bundle != null;
            URL urlmultiPlayer = getClass().getResource(fxml);
            assert urlmultiPlayer != null;
            Scene scene = new Scene(FXMLLoader.load(urlmultiPlayer, bundle));
            scene.setOnKeyPressed(eventHandler);
            return scene;
        }catch (IOException e){

            return null;
        }
    }

    public void showHome(){
        primaryStage.setTitle("Page d'Accueil du Jeu");
        primaryStage.setScene(this.generateScene("../scenes/main/MainMenu.fxml", new MainListener()));
    }

    public void showSinglePlayer(){
        primaryStage.setTitle("SinglePlayer");
        primaryStage.setScene(this.generateScene("../scenes/singlePlayer/SinglePlayer.fxml", new SingleListener()));
    }

    public void showMultiPlayer(){
        primaryStage.setTitle("MultiPlayer");
        primaryStage.setScene(this.generateScene("../scenes/multiPlayer/MultiPlayer.fxml", new MultiListener()));
    }

    public void showEditeur(){
        primaryStage.setTitle("Editeur");
        primaryStage.setScene(this.generateScene("../scenes/editor/Editor.fxml", new EditeurListener()));
    }

    public void showOption(){
        primaryStage.setTitle("options");
        primaryStage.setScene(this.generateScene("../scenes/option/Option.fxml", new OptionListener()));
    }

    public static Window getApp() {
        return app;
    }

    /*
    public void setDarkMode() {
        option.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../stylesheet/darkStyle.css")).toExternalForm());
        editeur.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../stylesheet/darkStyle.css")).toExternalForm());
    }
     */

    public String getLang() {
        return local;
    }

    public void setLang() {

    }

    public static void main(String[] args) {
        launch(args); //Démarré depuis une autre instance
    }

}
