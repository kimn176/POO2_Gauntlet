package scenes.game.playable;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayersDisplay implements Initializable {

    // Smart Bomb and keys number
    public Label smartBombNumberPL1;
    public Label smartBombNumberPL2;
    public Label smartBombNumberPL3;
    public Label smartBombNumberPL4;
    public Label keysNumberPL1;
    public Label keysNumberPL2;
    public Label keysNumberPL3;
    public Label keysNumberPL4;
    public VBox playerOne;
    public VBox playerTwo;
    public VBox playerThree;
    public VBox playerFour;

    static public PlayersDisplay playersDisplay;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PlayersDisplay.playersDisplay = this;
        Thread th = new Thread(new Run());
        th.start();
    }

    public void addKey(Label keysNumber) {
        int num = Integer.parseInt(keysNumber.getText());
        num++;
        keysNumber.setText(String.valueOf(num));
    }

    public void minusKey(Label keysNumber) {
        int num = Integer.parseInt(keysNumber.getText());
        num--;
        keysNumber.setText(String.valueOf(num));
    }

    public void addSmartBomb(Label smartBombNumber) {
        int num = Integer.parseInt(smartBombNumber.getText());
        num++;
        smartBombNumber.setText(String.valueOf(num));
    }

    public void minusSmartBombe(Label smartBombNumber) {
        int num = Integer.parseInt(smartBombNumber.getText());
        num--;
        smartBombNumber.setText(String.valueOf(num));
    }

    public void player1NotPlayable() {
        playerOne = new VBox();
    }
    public void player2NotPlayable() {

    }
    public void player3NotPlayable() {

    }
    public void player4NotPlayable() {

    }

    class Run implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}