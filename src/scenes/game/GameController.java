package scenes.game;

import character.*;
import character.Character;
import grid.Carte;
import grid.CarteSaver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.ImageData;
import util.ImageEnum;
import util.Window;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    // Zone Players
    public VBox leftVbox;
    public Carte carte;

    public BorderPane borderpane;
    public Slider zoomSlider;
    public Label bestScore;
    public Label level;
    private HBox box;
    private final util.Window Window = util.Window.getApp();
    public ScrollPane scrollPaneCenter;
    private ImageData selectionReminder = null;

    public void exitApp(ActionEvent actionEvent) throws Exception {
        Window.stop();
        System.exit(0);
    }

    @FXML
    public void exitGame(ActionEvent event) {
        Window.showHome();
    }

    @FXML
    public void loadItemMap(ActionEvent event) {
        Carte carte1 = new CarteSaver().read();
        if(carte1 != null) {
            this.carte = carte1;
            this.carte.setOnClick((actionEvent, gridCase) -> carte1.setCell(gridCase.getX(), gridCase.getY(), selectionReminder));
            this.scrollPaneCenter.setContent(carte1);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){

        //player2NotPlayable();

        //Center
        carte = new CarteSaver().read("saves/test.bin");
        box = new HBox();
        box.getChildren().add(carte);
        carte.setBorderVisible(false);
        ScrollPane scrollPaneCenter = new ScrollPane(box);
        carte.setScale(1.2, box);


        /* C'EST POUR ESSAYER */
        Valkyrie valkyrie = new Valkyrie(carte);
        borderpane.setOnKeyPressed(event -> {                                                               // TRY
            if(event.getCode() == KeyCode.S && valkyrie.getY() >= 0) {
                valkyrie.move(0, 10);
            }                                                                                               // TRY
                                                                                                            // TRY
            if(event.getCode() == KeyCode.Z && valkyrie.getY()-10 >= 0) {
                valkyrie.move(0, -10);// TRY
            }                                                                                               // TRY
                                                                                                            // TRY
            if(event.getCode() == KeyCode.Q && valkyrie.getX()-10 >= 0) {                                     // TRY
                valkyrie.move(-10, 0);                                                              // TRY
            }                                                                                               // TRY
                                                                                                            // TRY
            if(event.getCode() == KeyCode.D && valkyrie.getX()+10 >= 0) {                                     // TRY
                valkyrie.move(+10, 0);                                                              // TRY
            }                                                                                               // TRY
            if(event.getCode() == KeyCode.E){
                valkyrie.pickupItem();
            }
        });                                                                                                 // TRY
        /* Fin d'essaie pourri */

        borderpane.setCenter(scrollPaneCenter);

        // Player One
        VBox playerOne;
        if(Character.warrior) {
            try {
                playerOne = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("playable/player1.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                playerOne = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("notPlayable/player1.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // Player Two
        VBox playerTwo;
        if(Character.elf) {
            try {
                playerTwo = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("playable/player2.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                playerTwo = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("notPlayable/player2.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // Player Three
        VBox playerThree;
        if(Character.valkyrie) {
            try {
                playerThree = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("playable/player3.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                playerThree = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("notPlayable/player3.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // Player Four
        VBox playerFour;
        if(Character.wizard) {
            try {
                playerFour = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("playable/player4.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                playerFour = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("notPlayable/player4.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        leftVbox.getChildren().add(playerOne);
        leftVbox.getChildren().add(playerTwo);
        leftVbox.getChildren().add(playerThree);
        leftVbox.getChildren().add(playerFour);

    }

    public void slideDrag(MouseEvent mouseEvent) {
        carte.setScale(zoomSlider.getValue(), box);
    }
}