package scenes.option;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.input.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.input.KeyCode;
import util.Window;

public class OptionController implements Initializable {

    public ToggleButton tb1;
    public ToggleButton tb2;
    public Label label;
    public Pane root;
    Window poo = Window.app;

    private Label clickedLabel;
    @FXML
    Button btnSave;

    @FXML
    Button btnBack;

    @FXML
    ComboBox<String> resolutionComboBox;
    @FXML
    ComboBox<String> langComboBox;


    @FXML
    public void returnAction(ActionEvent event) {
        poo.showHome();
    }

    @FXML
    public void setDarkMode(ActionEvent event) {
        label.setText("Dark Mode");
        //poo.setDarkMode();
    }

    @FXML
    public void setLightMode(ActionEvent event) {
        label.setText("Light Mode");
    }

    @FXML
    public void clickedChoose(MouseEvent event){
        clickedLabel = (Label) event.getSource();
        clickedLabel.requestFocus();
    }
    @FXML
    public void changeKey(KeyEvent event){

        if(clickedLabel != null){
            String lbClicked = event.getCode().toString();
            clickedLabel.setText(lbClicked);
        }

    }

    @FXML
    public void saveChange(MouseEvent event){
        System.out.println("Save");
        System.out.println("Key and mouse setting saved");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup group = new ToggleGroup();
        tb1.setToggleGroup(group);
        tb2.setToggleGroup(group);

        // Création d'une image pour l'arrière-plan
        Image backgroundImage = new Image(getClass().getResource("/sprites/page_accueil.jpg").toExternalForm());
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        root.setBackground(new Background(background));

        // Add items to the language combobox
        langComboBox.getItems().addAll("en", "fr");
        langComboBox.setValue("fr");

        // Add items to the resolution combobox
        resolutionComboBox.getItems().addAll("800x600", "1024x768", "1280x720", "1920x1080");
        resolutionComboBox.setValue("1024x768");

    }
}
