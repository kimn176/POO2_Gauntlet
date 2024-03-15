package scenes.game;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import util.ImageEnum;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    // Smart Bomb Image
    public Label smartBomb1;
    public Label smartBomb2;
    public Label smartBomb3;
    public Label smartBomb4;
    public Label smartBombNumberPL1;
    public Label keysNumberPL1;

    // Keys Image
    public Label keys1;
    public Label keys2;
    public Label keys3;
    public Label keys4;
    public GridPane warriorSL;
    public Label keysNumberPL2;
    public Label smartBombNumberPL2;

    // Zone Players
    public VBox playerOne;
    public VBox playerTwo;
    public VBox playerThree;
    public VBox playerFour;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Smart Bomb image
        ImageView smartBombView1 = ImageEnum.SMART_BOMB.generateImageData(0, 0).generateImageView();
        ImageView smartBombView2 = ImageEnum.SMART_BOMB.generateImageData(0, 0).generateImageView();
        ImageView smartBombView3 = ImageEnum.SMART_BOMB.generateImageData(0, 0).generateImageView();
        ImageView smartBombView4 = ImageEnum.SMART_BOMB.generateImageData(0, 0).generateImageView();
        smartBombView1.setFitWidth(30);
        smartBombView2.setFitWidth(30);
        smartBombView3.setFitWidth(30);
        smartBombView4.setFitWidth(30);
        smartBomb1.setGraphic(smartBombView1);
        smartBomb2.setGraphic(smartBombView2);
        smartBomb3.setGraphic(smartBombView3);
        smartBomb4.setGraphic(smartBombView4);

        //Keys image
        ImageView keyView1 = ImageEnum.KEY.generateImageData(0,0).generateImageView();
        ImageView keyView2 = ImageEnum.KEY.generateImageData(0,0).generateImageView();
        ImageView keyView3 = ImageEnum.KEY.generateImageData(0,0).generateImageView();
        ImageView keyView4 = ImageEnum.KEY.generateImageData(0,0).generateImageView();
        keyView1.setFitWidth(30);
        keyView2.setFitWidth(30);
        keyView3.setFitWidth(30);
        keyView4.setFitWidth(30);
        keys1.setGraphic(keyView1);
        keys2.setGraphic(keyView2);
        keys3.setGraphic(keyView3);
        keys4.setGraphic(keyView3);
    }
}