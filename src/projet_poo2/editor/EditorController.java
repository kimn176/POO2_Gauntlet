package projet_poo2.editor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
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
    private final Map<ImageEnum, Button> buttonMap = new HashMap<>();
    private final ArrayList<SplitMenuButton> choiceBoxMap = new ArrayList<>();
    public VBox buttonVBox;
    public ScrollPane scrollPaneCenter;
    private Carte carte = new Carte(20);
    private ImageData selectionReminder = null;

    public void updateImageData(ImageData imageData){
        this.selectionReminder = imageData;
        for (Button bc : this.buttonMap.values()) {
            bc.setStyle("-fx-background-color: transparent; -fx-border-style: none");
        }
        for (SplitMenuButton sp : this.choiceBoxMap) {
            sp.setStyle("-fx-background-color: transparent; -fx-border-style: none");
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

    public void loadButton(ImageData imageData){

        Button button = new Button("");

        button.setPrefWidth(150.0);
        button.setAlignment(Pos.CENTER);

        button.setOnAction(e -> {
            this.updateImageData(imageData);
        });

        buttonMap.put(imageData.getImageEnum(), button);
        buttonVBox.getChildren().add(button);

        this.updateImageData(imageData);
        button.setStyle("-fx-background-color: transparent; -fx-border-style: none");

    }

    public void loadBox(ImageEnum imageEnum) {
        SplitMenuButton choiceBox = new SplitMenuButton();
        choiceBox.setPrefWidth(150.0);
        choiceBox.setAlignment(Pos.CENTER);
        choiceBox.setStyle("-fx-background-color: transparent; -fx-border-style: none");


        //choiceBox.setText(imageEnum.toString());
        for(int i = 0; i < imageEnum.getSpriteNumX(); i++) {
            MenuItem button = new MenuItem("");
            ImageData imageData = imageEnum.generateImageData(i, 0);
            ImageView view = imageEnum.generateImageData(i, 0).generateImageView();
            view.setFitHeight(50);
            view.setFitWidth(50);
            button.setGraphic(view);
            //button.setPrefWidth(150.0);
            //button.setAlignment(Pos.CENTER);

            button.setOnAction(e -> {
                this.onSelection(imageData, choiceBox);
            });

            choiceBox.getItems().add(button);

            //this.updateImageData(imageData);
            button.setStyle("-fx-background-color: transparent; -fx-border-style: none");
        }
        ImageView viewButton = imageEnum.generateImageData(0, 0).generateImageView();
        viewButton.setFitHeight(50);
        viewButton.setFitWidth(50);
        choiceBox.setGraphic(viewButton);
        buttonVBox.getChildren().add(choiceBox);
        choiceBox.setOnAction(e -> {
            this.onSelection(imageEnum.generateImageData(0, 0), choiceBox);
        });
        choiceBoxMap.add(choiceBox);
    }

    public void onSelection(ImageData imageData, SplitMenuButton sp) {

        this.selectionReminder = imageData;

        for (Button bc : this.buttonMap.values()) {
            bc.setStyle("-fx-background-color: transparent; -fx-border-style: none");
        }
        for (SplitMenuButton sp2 : this.choiceBoxMap) {
            sp2.setStyle("-fx-background-color: transparent; -fx-border-style: none");
        }

        sp.setStyle("-fx-background-color: red; -fx-border-style: none;");

        ImageView viewButton = imageData.generateImageView();
        viewButton.setFitHeight(50);
        viewButton.setFitWidth(50);
        sp.setGraphic(viewButton);
        sp.setOnAction(e -> {
            this.onSelection(imageData, sp);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        carte.setOnClick((actionEvent, gridCase) -> carte.setCell(gridCase.getX(), gridCase.getY(), selectionReminder));
        scrollPane = new ScrollPane(carte);

        zoomSlider.setOnMouseDragged(event-> carte.scale(zoomSlider.getValue()));
        zoomSlider.setOnMouseReleased(event-> carte.scale(zoomSlider.getValue()));

        /*
        Dans cette section, on navigue dans les images déjà importées de ImageEnum
        Si l'imagie en question ne peut pas être placée dans la VBox, on passe
        Sinon on créé un nouveau bouton avec comme image d'arrière plan notre sprite
         */
        for (ImageEnum imageEnum : ImageEnum.values()){

            if(!imageEnum.canBePlaced())
                continue;

            if(imageEnum.getSpriteNumX() == 1)
            this.loadButton(imageEnum.generateImageData(0, 0)); //Charge l'image et génère un bouton

            else {
                this.loadBox(imageEnum);
            }

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

    public void exitApp(ActionEvent actionEvent) throws Exception {
        projet_poo2.stop();
        System.exit(0);
    }
}
