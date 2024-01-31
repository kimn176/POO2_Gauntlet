package projet_poo2.grid;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class CarteGridCase {
    Button button;
    ImageView imageView;
    int x, y;

    public CarteGridCase(Button button, ImageView imageView, int x, int y){
        this.button = button;
        this.imageView = imageView;
        this.x = x;
        this.y = y;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
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
