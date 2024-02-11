package projet_poo2.image;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageData {

    final double xSize, ySize, xSize2, ySize2;
    final Image image;
    final boolean canBePlaced;
    final int id;

    public ImageData(Image image, double xSize, double ySize, double xSize2, double ySize2, boolean canBePlaced, int id){
        this.image = image;
        this.xSize = xSize;
        this.xSize2 = xSize2;
        this.ySize = ySize;
        this.ySize2 = ySize2;
        this.canBePlaced = canBePlaced;
        this.id = id;
    }

    public Image getImage(){
        return this.image;
    }

    public int getId(){
        return this.id;
    }

    public boolean canBePlaced(){
        return this.canBePlaced;
    }

    public ImageView generateView(){
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setPreserveRatio(true);

        imageView.setViewport(new Rectangle2D(xSize,ySize, xSize2, ySize2));
        return imageView;
    }

}
