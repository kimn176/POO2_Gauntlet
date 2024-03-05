package scenes.editor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import util.*;
import grid.Carte;
import grid.CarteSaver;

import java.net.URL;
import java.util.*;

public class EditorController implements Initializable {

    private final Window Window = util.Window.getApp();
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
        Window.showHome();
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
        if(imageEnum.getSpriteNumY() == 1)
            for(int i = 0; i < imageEnum.getSpriteNumX(); i++) {
                MenuItem button = new MenuItem("");
                ImageData imageData = imageEnum.generateImageData(i, 0);
                ImageView view = imageEnum.generateImageData(i, 0).generateImageView();
                view.setFitHeight(50);
                view.setFitWidth(50);
                button.setGraphic(view);

                button.setOnAction(e -> {
                    this.onSelection(imageData, choiceBox);
                });

                choiceBox.getItems().add(button);
                button.setStyle("-fx-background-color: transparent; -fx-border-style: none");
            }
        else if(imageEnum.getSpriteNumY() == 2)
            for(int i = 0; i < imageEnum.getSpriteNumX(); i++) {
                for(int j = 0; j < imageEnum.getSpriteNumY(); j++) {
                    MenuItem button = new MenuItem("");
                    ImageData imageData = imageEnum.generateImageData(i, j);
                    ImageView view = imageEnum.generateImageData(i, j).generateImageView();
                    view.setFitHeight(50);
                    view.setFitWidth(50);
                    button.setGraphic(view);

                    button.setOnAction(e -> {
                        this.onSelection(imageData, choiceBox);
                    });

                    choiceBox.getItems().add(button);
                    button.setStyle("-fx-background-color: transparent; -fx-border-style: none");
                }
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

        carte.setOnClick((actionEvent, gridCase) -> carte.setCell(gridCase.getX(), gridCase.getY(), this.selectionReminder));
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

            if(imageEnum.getSpriteNumX() == 1) {
                this.loadButton(imageEnum.generateImageData(0, 0)); //Charge l'image et génère un bouton
            }

            else {

                if(imageEnum.canBePlaced() && imageEnum.getSpriteNumY() == 1) {
                    this.loadBox(imageEnum);
                }
                else if (imageEnum.getSpriteNumX() >= 4 && imageEnum.getSpriteNumY() == 2)
                {
                    this.loadButton(imageEnum.generateImageData(4, 1));
                }
            }

        }
        this.buttonVBox.getChildren().add(new Label("Players"));
        this.loadButton(ImageEnum.WARRIOR.generateImageData(4, 1));
        this.loadButton(ImageEnum.ELF.generateImageData(4, 1));
        this.loadButton(ImageEnum.VALKYRIE.generateImageData(4, 1));
        this.loadButton(ImageEnum.WIZARD.generateImageData(4, 1));

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
        Window.stop();
        System.exit(0);
    }
}
