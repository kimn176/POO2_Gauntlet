package grid;

import javafx.scene.control.Button;
import util.ImageData;

public class CarteGridCase {
    Button button;
    ImageData imageData;
    int x, y;

    public CarteGridCase(Button button, ImageData imageData, int x, int y){
        this.button = button;
        this.imageData = imageData;
        this.x = x;
        this.y = y;
    }

    public ImageData getImageData() {
        return imageData;
    }

    public void setImageData(ImageData imageData) {
        this.imageData = imageData;
    }

    public Button getButton(){
        return this.button;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
