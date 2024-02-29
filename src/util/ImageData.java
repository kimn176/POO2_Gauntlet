package util;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public class ImageData {

    final ImageEnum image;
    final double xSize, ySize;
    final int spriteX, spriteY;

    ImageData(ImageEnum image, int spriteX, int spriteY, double xSize, double ySize){
        this.image = image;
        this.spriteX = spriteX;
        this.spriteY = spriteY;
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public ImageEnum getImageEnum(){
        return this.image;
    }

    public ImageView generateImageView(){
        ImageView imageView = new ImageView();
        imageView.setImage(this.getImageEnum().getImage());
        imageView.setPreserveRatio(true);

        imageView.setViewport(new Rectangle2D(xSize * spriteX, ySize * spriteY, xSize, ySize));
        return imageView;
    }

    public int getSpriteX() {
        return spriteX;
    }

    public int getSpriteY() {
        return spriteY;
    }

}
