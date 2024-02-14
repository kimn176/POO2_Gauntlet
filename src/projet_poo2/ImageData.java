package projet_poo2;

import javafx.scene.image.ImageView;
import projet_poo2.ImageEnum;

public class ImageData {

    final ImageEnum image;
    final ImageView imageView;
    final int spriteX, spriteY;

    ImageData(ImageEnum image, int spriteX, int spriteY, ImageView imageView){
        this.image = image;
        this.spriteX = spriteX;
        this.spriteY = spriteY;
        this.imageView = imageView;
    }

    public ImageEnum getImageEnum(){
        return this.image;
    }

    public ImageView getImageView(){
        return this.imageView;
    }

    public int getSpriteX() {
        return spriteX;
    }

    public int getSpriteY() {
        return spriteY;
    }

}
