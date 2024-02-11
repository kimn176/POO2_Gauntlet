package projet_poo2.grid;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import projet_poo2.ImageManager;
import projet_poo2.image.ImageData;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.function.BiConsumer;

public class Carte extends GridPane {

    final int size;
    double scale = 1;
    private final ImageManager imageManager;
    private final CarteGridCase[][] panes;
    private BiConsumer<ActionEvent, CarteGridCase> actionEventConsumer = (actionEvent, gcase) -> System.out.println("No action");

    public Carte(ImageManager imageManager, int size){
        super();
        /*
        super.setVgap(10);
        super.setHgap(10);
        */
        this.imageManager = imageManager;
        this.size = size;
        this.panes = new CarteGridCase[this.size][this.size];
        this.init();
    }

    public int getSize(){
        return this.size;
    }

    public void scale(double scale){
        this.scale = scale;
        for(int x = 0; x<size; x++){
            for (int y = 0; y<size; y++){
                Button button = this.panes[x][y].getButton();
                button.setPrefSize(50 * scale, 50 * scale);
                button.setMinSize(50 * scale,50 * scale);
                if(this.panes[x][y].getButton().getGraphic() != null){
                ImageView imageView = (ImageView) this.panes[x][y].getButton().getGraphic();
                if(imageView != null)
                    imageView.setFitWidth(50*scale);
                }
            }
        }
    }

    public void setOnClick(BiConsumer<ActionEvent, CarteGridCase> actionEventConsumer){
        this.actionEventConsumer = actionEventConsumer;
    }

    public void init(){

        for(int x = 0; x<size; x++){
            for(int y = 0; y<size; y++){
                generatePane(x, y);
            }
        }

    }

    public void generatePane(int x, int y){

        ImageData imageData = this.imageManager.getImageData(0);
        Image image = imageData.getImage();

        Button button = new Button();

        CarteGridCase carteGridCase = new CarteGridCase(button, null, x, y);

        carteGridCase.setImageData(imageData);
        button.setPrefSize(50 * scale, 50 * scale);
        button.setMinSize(50 * scale,50 * scale);

        BorderStroke borderStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(0), BorderWidths.DEFAULT);
        Border border = new Border(borderStroke);

        button.setBorder(border);

        button.setOnAction(action -> {
            if(action != null)
                actionEventConsumer.accept(action, carteGridCase);
        });

        button.setBackground(new Background(new BackgroundImage(image, null, null, null, BackgroundSize.DEFAULT)));

        panes[x][y] = carteGridCase;

        super.add(button, x, y);
    }

    public void setCell(int x, int y, ImageData imageData){

        if(x>size || y>size)
            return;

        if(imageData == null){
            return;
        }

        CarteGridCase carteGridCase = panes[x][y];

        carteGridCase.setImageData(imageData);
        if(imageData.getId() == 0){
            carteGridCase.getButton().setGraphic(null);
            return;
        }
        ImageView image = imageData.generateView();
        image.setPreserveRatio(true);
        image.setFitWidth(50 * scale);
        carteGridCase.getButton().setGraphic(image);
    }

    public CarteGridCase getCase(int x, int y){
        return this.panes[x][y];
    }

}
