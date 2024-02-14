package projet_poo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import projet_poo2.grid.Carte;
import projet_poo2.grid.CarteSaver;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EditorController implements Initializable {

    private final Projet_POO2 projet_poo2 = Projet_POO2.app;
    public ScrollPane scrollPane;
    public BorderPane borderpane;
    public Slider zoomSlider;
    public VBox leftPane;
    public List<Button> buttonList;
    public VBox buttonVBox;
    public ScrollPane scrollPaneCenter;
    Carte carte = new Carte(20);
    private ImageData selectionReminder = null;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        carte.setOnClick((actionEvent, gridCase) -> carte.setCell(gridCase.getX(), gridCase.getY(), selectionReminder));
        scrollPane = new ScrollPane(carte);

        zoomSlider.setOnMouseDragged(event-> carte.scale(zoomSlider.getValue()));
        zoomSlider.setOnMouseReleased(event-> carte.scale(zoomSlider.getValue()));

        buttonList = new ArrayList<>();

        for (ImageEnum imageEnum : ImageEnum.values()){

            if(!imageEnum.canBePlaced())
                continue;

            for(int spriteX = 0; spriteX < imageEnum.getSpriteNumX(); spriteX++){
                for(int spriteY = 0; spriteY < imageEnum.getSpriteNumY(); spriteY++){

                    ImageData imageData = imageEnum.generateImageData(spriteX, spriteY);
                    ImageView imageView = imageData.getImageView();
                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);

                    Button button = new Button("", imageView);

                    button.setPrefWidth(150.0);
                    button.setAlignment(Pos.CENTER);
                    button.setStyle("-fx-background-color: transparent; -fx-border-style: none");

                    button.setOnAction(action -> {
                        for (Button bc : buttonList){
                            bc.setStyle("-fx-background-color: transparent; -fx-border-style: none");
                        }
                        button.setStyle("-fx-background-color: red; -fx-border-style: none");
                        this.selectionReminder = imageData;
                    });

                    buttonList.add(button);
                    buttonVBox.getChildren().add(button);

                }
            }

        }

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
