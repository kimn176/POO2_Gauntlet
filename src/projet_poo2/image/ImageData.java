package projet_poo2.image;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageData {

    final int numbX, numbY;
    final double ySize, xSize;
    final Image image;
    boolean canBePlaced = true;

    public ImageData(Image image, int spriteNumX, int spriteNumY){
        this.image = image;
        this.ySize = image.getHeight()/spriteNumY;
        this.xSize = image.getWidth()/spriteNumX;
        this.numbX = spriteNumX;
        this.numbY = spriteNumY;
    }

    public ImageData(Image image, int spriteNumX, int spriteNumY, boolean canBePlaced){
        this(image, spriteNumX, spriteNumY);
        this.canBePlaced = canBePlaced;
    }

    public int getNumbX() {
        return numbX;
    }

    public int getNumbY() {
        return numbY;
    }

    public boolean canBePlaced(){
        return this.canBePlaced;
    }

    public ImageView generateView(int x, int y){
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setPreserveRatio(true);

        imageView.setViewport(new Rectangle2D(xSize*x,ySize*y, xSize, ySize));
        return imageView;
    }

}
