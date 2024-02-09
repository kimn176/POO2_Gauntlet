package projet_poo2;

/**
 *
 * @author kimngan
 */

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import projet_poo2.grid.Carte;
import projet_poo2.image.ImageData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Editeur2D extends BorderPane {

    private final Projet_POO2 projet_poo2;
    private final ImageManager imageManager = new ImageManager();

    private ImageData selectionReminder = null;

    public Editeur2D(Projet_POO2 projet_poo2) {

        this.projet_poo2 = projet_poo2;

        this.createTopBar();

        Carte carte = new Carte(imageManager, 20);
        carte.setOnClick((actionEvent, gridCase) -> {
            if(selectionReminder != null)
                carte.setCell(gridCase.getX(), gridCase.getY(), selectionReminder.generateView());
            else
                carte.setCell(gridCase.getX(), gridCase.getY(), null);
        });
        this.createLeftPane(carte);

        // Créer la scène

        ScrollPane scrollPane = new ScrollPane(carte);
        this.setCenter(scrollPane);

    }

    public ImageManager getImageManager() {
        return imageManager;
    }

    private void createTopBar(){
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("Fichier");
        MenuItem menuItemNew = new MenuItem("Nouvelle carte");
        MenuItem menuItemLoad = new MenuItem("Charger une carte");
        menuFile.getItems().addAll(menuItemNew, menuItemLoad);

        Menu menuOption = new Menu("Option");
        MenuItem menuItemQuitter = new MenuItem("Quiter");
        menuItemQuitter.setOnAction(action -> projet_poo2.showHome());
        menuOption.getItems().add(menuItemQuitter);

        menuBar.getMenus().addAll(menuFile, menuOption);

        this.setTop(menuBar);
    }

    private Slider createLeftPane(Carte carte){

        Slider zoomSlider = new Slider();
        zoomSlider.setMax(3);
        zoomSlider.setMin(1);
        zoomSlider.setValue(1);

        zoomSlider.setOnMouseDragged(event-> carte.scale(zoomSlider.getValue()));
        zoomSlider.setOnMouseReleased(event-> carte.scale(zoomSlider.getValue()));

        // Créer le bandeau gauche
        VBox leftPane = new VBox();
        leftPane.setAlignment(Pos.CENTER);

        //this.setStyle("-fx-background-color: lightgray");

        List<Button> buttonList = new ArrayList<>();

        for(Map.Entry<Integer, ImageData> imageEntry : imageManager.getMap().entrySet()){

            if(!imageEntry.getValue().canBePlaced())
                continue;

            ImageView imageView = imageEntry.getValue().generateView();
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
                this.selectionReminder = imageEntry.getValue();

                if(imageEntry.getKey() == 0)
                    this.selectionReminder = null;
            });

            buttonList.add(button);
            leftPane.getChildren().add(button);

        }

        Button button = new Button("Reset");
        button.setPrefWidth(150.0);
        button.setAlignment(Pos.CENTER);
        button.setStyle("-fx-background-color: transparent; -fx-border-color: blue");
        button.setOnAction(action->{
            for(int x = 0; x<carte.getSize(); x++){
                for(int y = 0; y<carte.getSize(); y++){
                    carte.setCell(x, y, null);
                }
            }
        });

        leftPane.getChildren().add(button);

        leftPane.getChildren().add(zoomSlider);


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(leftPane);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);
        this.setLeft(scrollPane);

        return zoomSlider;
    }

}
