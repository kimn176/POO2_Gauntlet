package projet_poo2.editor;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import projet_poo2.ImageData;
import projet_poo2.ImageEnum;

import java.util.ArrayList;
import java.util.List;

public class SelectionController extends BorderPane {


    public List<Button> buttonList = new ArrayList<>();
    public EditorController editorController;

    public SelectionController(EditorController editorController, ImageEnum imageEnum){

        this.editorController = editorController;
        this.getChildren().add(this.loadList(imageEnum));

    }

    public HBox loadList(ImageEnum imageEnum){

        HBox vBox = new HBox();

        for (int x = 0; x < imageEnum.getSpriteNumX(); x++) {
            for (int y = 0; y < imageEnum.getSpriteNumY(); y++) {

                ImageData imageData = imageEnum.generateImageData(x, y);

                ImageView imageView = imageData.generateImageView();
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);

                Button button = new Button("", imageView);

                button.setPrefWidth(150.0);
                button.setAlignment(Pos.CENTER);
                button.setStyle("-fx-background-color: transparent; -fx-border-style: none");

                button.setOnMouseClicked(mouse -> {

                    for (Button bc : buttonList) {
                        bc.setStyle("-fx-background-color: transparent; -fx-border-style: none");
                    }
                    button.setStyle("-fx-background-color: red; -fx-border-style: none");
                    editorController.updateImageData(imageData);

                });

                buttonList.add(button);
                vBox.getChildren().add(button);

            }

        }

        return vBox;

    }

}
