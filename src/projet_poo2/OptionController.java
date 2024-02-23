package projet_poo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class OptionController implements Initializable {

    public ToggleButton tb1;
    public ToggleButton tb2;
    public Label label;
    Projet_POO2 poo = Projet_POO2.app;
    @FXML
    public void returnAction(ActionEvent event) {
        poo.showHome();
    }

    @FXML
    public void setDarkMode(ActionEvent event) {
        label.setText("Dark Mode");
        poo.setDarkMode();
    }

    @FXML
    public void setLightMode(ActionEvent event) {
        label.setText("Light Mode");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup group = new ToggleGroup();
        tb1.setToggleGroup(group);
        tb2.setToggleGroup(group);
    }
}
