package projet_poo2.editor;

import javafx.beans.value.WritableValue;
import javafx.css.StyleableProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projet_poo2.ImageData;
import projet_poo2.ImageEnum;
import projet_poo2.Projet_POO2;
import projet_poo2.grid.Carte;
import projet_poo2.grid.CarteSaver;

import java.net.URL;
import java.util.*;

public class EditorController implements Initializable {

    private final Projet_POO2 projet_poo2 = Projet_POO2.app;
    public ScrollPane scrollPane;
    public BorderPane borderpane;
    public Slider zoomSlider;
    public VBox leftPane;
    private Map<ImageEnum, Button> buttonMap = new HashMap<>();
    public VBox buttonVBox;
    public ScrollPane scrollPaneCenter;
    private Carte carte = new Carte(20);
    private ImageData selectionReminder = null;

    public void updateImageData(ImageData imageData){
        this.selectionReminder = imageData;
        for (Button bc : this.buttonMap.values()) {
            bc.setStyle("-fx-background-color: transparent; -fx-border-style: none");
        }

        Button button = this.buttonMap.get(imageData.getImageEnum());

        button.setStyle("-fx-background-color: red; -fx-border-style: none");
        ImageView imageView = imageData.generateImageView();
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        button.setGraphic(imageView);
    }

    @FXML
    public void loadItemMap(ActionEvent event) {
        Carte carte1 = new CarteSaver().read();
        if(carte1 != null) {
            borderpane.setCenter(new ScrollPane(carte1));
            carte1.setOnClick((actionEvent, gridCase) -> carte1.setCell(gridCase.getX(), gridCase.getY(), selectionReminder));
            this.carte = carte1;
        }
    }

    @FXML
    public void exitMap(ActionEvent event) {
        projet_poo2.showHome();
    }

    public void openSelection(ImageEnum imageEnum){

        Stage stage = new Stage();
        stage.setTitle("Selection");
        Scene scene = new Scene(new SelectionController(this, imageEnum));

        stage.setScene(scene);
        stage.show();

    }

    public void loadButton(ImageData imageData){

        Button button = new Button("");

        button.setPrefWidth(150.0);
        button.setAlignment(Pos.CENTER);

        button.setOnMouseClicked(mouse -> {

            if (mouse.getButton() == MouseButton.SECONDARY){

                ImageEnum imageEnum = imageData.getImageEnum();
                if(imageEnum.getSpriteNumY() == 1 && imageEnum.getSpriteNumX() == 1)
                    return;

                this.openSelection(imageEnum);

            }

            this.updateImageData(imageData);

        });

        buttonMap.put(imageData.getImageEnum(), button);
        buttonVBox.getChildren().add(button);

        this.updateImageData(imageData);
        button.setStyle("-fx-background-color: transparent; -fx-border-style: none");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        carte.setOnClick((actionEvent, gridCase) -> carte.setCell(gridCase.getX(), gridCase.getY(), selectionReminder));
        scrollPane = new ScrollPane(carte);

        zoomSlider.setOnMouseDragged(event-> carte.scale(zoomSlider.getValue()));
        zoomSlider.setOnMouseReleased(event-> carte.scale(zoomSlider.getValue()));

        for (ImageEnum imageEnum : ImageEnum.values()){

            if(!imageEnum.canBePlaced())
                continue;

            this.loadButton(imageEnum.generateImageData(0, 0));

        }

        this.loadButton(ImageEnum.PLAYER.generateImageData(4, 0));

        //Obligé, sinon ça s'affiche pas
        scrollPaneCenter = new ScrollPane(carte);
        borderpane.setCenter(scrollPaneCenter);
    }

    @FXML
    public void resetButtonAction(ActionEvent event) {
        ImageData imageData = ImageEnum.FLOOR.generateImageData(0, 0);
        for(int x = 0; x<carte.getSize(); x++){
            for(int y = 0; y<carte.getSize(); y++){
                carte.setCell(x, y, imageData);
            }
        }
    }

    @FXML
    public void saveButtonAction(ActionEvent event) {
        new CarteSaver().save(carte);
    }

}
