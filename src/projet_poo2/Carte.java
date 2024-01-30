/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_poo2;

import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author kimngan
 */

public class Carte  {

    private static final String FLOOR_IMAGE_PATH = "sprites/floor.png";
    private static final int GRID_ROWS = 30;
    private static final int GRID_COLS = 30;
    private static final double INITIAL_TILE_SIZE = 20.0;

    private GridPane cartePane;
    private Slider zoomSlider;
    
    
    public Carte(Slider zoomSlider) {
        this.zoomSlider = zoomSlider;
        initializeCarte();
    }

    public void initializeCarte() {
        cartePane = new GridPane();
        updateSize();

        // Remplissez la carte avec des images de sol
        for (int i = 0; i < GRID_ROWS; i++) {
            for (int j = 0; j < GRID_COLS; j++) {
                Image floorImage = new Image(getClass().getResource(FLOOR_IMAGE_PATH).toExternalForm());
                ImageView imageView = new ImageView(floorImage);
                imageView.setFitWidth(20);
                imageView.setFitHeight(20);

                cartePane.add(imageView, j, i);
            }
        }
    }
    

    public GridPane getCartePane() {
        return cartePane;
    }
    
    void updateSize() {
        double tileSize = INITIAL_TILE_SIZE * zoomSlider.getValue();
        for (Node node : cartePane.getChildren()) {
            if (node instanceof ImageView) {
                ImageView imageView = (ImageView) node;
                imageView.setFitWidth(tileSize);
                imageView.setFitHeight(tileSize);
            }
        }
    }
    

}
