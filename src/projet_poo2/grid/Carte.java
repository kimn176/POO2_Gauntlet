package projet_poo2.grid;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import projet_poo2.ImageData;
import projet_poo2.ImageEnum;

import java.util.function.BiConsumer;

public class Carte extends GridPane {

    final int size;
    double scale = 1;
    private final CarteGridCase[][] panes;
    private CarteGridCase player = null;
    private BiConsumer<ActionEvent, CarteGridCase> actionEventConsumer = (actionEvent, gcase) -> System.out.println("No action");

    public Carte(int size){
        super();

        this.size = size;
        this.panes = new CarteGridCase[this.size][this.size];
        this.init();
    }

    public CarteGridCase getPlayerCase(){
        return this.player;
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

        ImageEnum imageEnum = ImageEnum.FLOOR;
        Image image = imageEnum.getImage();

        Button button = new Button();

        CarteGridCase carteGridCase = new CarteGridCase(button, null, x, y);

        carteGridCase.setImageData(imageEnum.generateImageData(0, 0));
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
        CarteGridCase currentPlayerCase = this.player;

        if(imageData.getImageEnum().getId() == ImageEnum.FLOOR.getId()) {
            carteGridCase.getButton().setGraphic(null);
            return;
        }

        if(imageData.getImageEnum().getId() == ImageEnum.PLAYER.getId()) {
            this.player = carteGridCase;

            if(currentPlayerCase != null)
                this.setCell(currentPlayerCase.getX(), currentPlayerCase.getY(), ImageEnum.FLOOR.generateImageData(0, 0)); // Remove the old character
        }else if(carteGridCase.getImageData().getImageEnum() == ImageEnum.PLAYER){
            this.player = null;
        }

        carteGridCase.setImageData(imageData);

        ImageView image = imageData.generateImageView();
        image.setPreserveRatio(true);
        image.setFitWidth(50 * scale);
        carteGridCase.getButton().setGraphic(image);
    }

    public CarteGridCase getCase(int x, int y){
        return this.panes[x][y];
    }

}
