package grid;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import util.ImageData;
import util.ImageEnum;

import java.util.function.BiConsumer;

public class Carte extends Pane {

    final int size;
    int defWidth = 40;
    private final CarteGridCase[][] panes;
    private CarteGridCase player1 = null;
    private CarteGridCase player2 = null;
    private CarteGridCase player3 = null;
    private CarteGridCase player4 = null;
    private BiConsumer<ActionEvent, CarteGridCase> actionEventConsumer = (actionEvent, gcase) -> System.out.println("No action");

    public Carte(int size){
        super();

        this.size = size;
        this.panes = new CarteGridCase[this.size][this.size];
        //this.setLayoutX(defWidth * this.size);
        //this.setLayoutY(defWidth * this.size);
        this.init();
    }

    public CarteGridCase getPlayerCase(){
        return this.player1;
    }

    public int getDefWidth() {
        return this.defWidth;
    }

    public int getSize(){
        return this.size;
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

        ImageEnum imageEnum = ImageEnum.FLOOR;
        Image image = imageEnum.getImage();

        Button button = new Button();

        CarteGridCase carteGridCase = new CarteGridCase(button, null, x, y);

        carteGridCase.setImageData(imageEnum.generateImageData(0, 0));
        button.setPrefSize(defWidth, defWidth);
        button.setMinSize(defWidth, defWidth);

        BorderStroke borderStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(0), BorderWidths.DEFAULT);
        Border border = new Border(borderStroke);

        button.setBorder(border);

        button.setOnAction(action -> {
            if(action != null)
                actionEventConsumer.accept(action, carteGridCase);
        });

        button.setBackground(new Background(new BackgroundImage(image, null, null, null, BackgroundSize.DEFAULT)));
        button.relocate(defWidth * x, defWidth * y);

        panes[x][y] = carteGridCase;

        super.getChildren().add(button);
    }

    public void setCell(int x, int y, ImageData imageData){

        if(x>size || y>size)
            return;

        if(imageData == null){
            return;
        }

        CarteGridCase carteGridCase = panes[x][y];
        CarteGridCase currentPlayerCase1 = this.player1;
        CarteGridCase currentPlayerCase2 = this.player2;
        CarteGridCase currentPlayerCase3 = this.player3;
        CarteGridCase currentPlayerCase4 = this.player4;

        if(imageData.getImageEnum().getId() == ImageEnum.FLOOR.getId()) {
            carteGridCase.getButton().setGraphic(null);
            return;
        }

        //WIZARD
        if(imageData.getImageEnum().getId() == ImageEnum.WIZARD.getId()) {
            this.player1 = carteGridCase;
            if(currentPlayerCase1 != null)
                this.setCell(currentPlayerCase1.getX(), currentPlayerCase1.getY(), ImageEnum.FLOOR.generateImageData(0, 0)); // Remove the old character
        }else if(carteGridCase.getImageData().getImageEnum() == ImageEnum.WIZARD){
            this.player1 = null;
        }

        //ELF
        if(imageData.getImageEnum().getId() == ImageEnum.ELF.getId()) {
            this.player2 = carteGridCase;
            if(currentPlayerCase2 != null)
                this.setCell(currentPlayerCase2.getX(), currentPlayerCase2.getY(), ImageEnum.FLOOR.generateImageData(0, 0)); // Remove the old character
        }else if(carteGridCase.getImageData().getImageEnum() == ImageEnum.ELF){
            this.player2 = null;
        }

        //WARRIOR
        if(imageData.getImageEnum().getId() == ImageEnum.WARRIOR.getId()) {
            this.player3 = carteGridCase;
            if(currentPlayerCase3 != null)
                this.setCell(currentPlayerCase3.getX(), currentPlayerCase3.getY(), ImageEnum.FLOOR.generateImageData(0, 0)); // Remove the old character
        }else if(carteGridCase.getImageData().getImageEnum() == ImageEnum.WARRIOR){
            this.player3 = null;
        }

        //VALKYRIE
        if(imageData.getImageEnum().getId() == ImageEnum.VALKYRIE.getId()) {
            this.player4 = carteGridCase;
            if(currentPlayerCase4 != null)
                this.setCell(currentPlayerCase4.getX(), currentPlayerCase4.getY(), ImageEnum.FLOOR.generateImageData(0, 0)); // Remove the old character
        }else if(carteGridCase.getImageData().getImageEnum() == ImageEnum.VALKYRIE){
            this.player4 = null;
        }

        carteGridCase.setImageData(imageData);

        ImageView image = imageData.generateImageView();
        image.setPreserveRatio(true);
        image.setFitWidth(defWidth);
        carteGridCase.getButton().setGraphic(image);
    }

    public void setScale(double newSize) {
        this.setScaleX(newSize);
        this.setScaleY(newSize);
    }

    public CarteGridCase getCase(int x, int y){
        return this.panes[x][y];
    }

}
