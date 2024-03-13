package scenes.multiPlayer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import util.ImageEnum;
import util.Window;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import util.PlayerProfile;
import javafx.scene.layout.Background;

public class MultiPlayerController implements Initializable {
    public StackPane stack = new StackPane();
    @FXML
    private Button backButton;

    @FXML
    private Button localButton;

    @FXML
    void actionLocal(ActionEvent event) throws Exception{
        System.out.println("Action Local");
        Window poo_projet = Window.app;
        poo_projet.showSinglePlayer();
    }

    @FXML
    public void backAction(ActionEvent event) throws Exception {
        System.out.println("Action Back");
        Window poo_projet = Window.app;
        poo_projet.showHome();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
