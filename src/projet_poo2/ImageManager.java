package projet_poo2;

import javafx.scene.image.Image;
import projet_poo2.image.ImageData;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ImageManager {

    private final Map<Integer, ImageData> map = new HashMap<>();
    private int id = 0;

    public ImageManager(){
        this.loadImage();
    }

    public Map<Integer, ImageData> getMap() {
        return map;
    }

    private void loadImage(){
        registerData("sprites/floor.png", 1, 1, true);
        registerData("sprites/exit.png", 1, 1, true);
        registerData("sprites/key.png", 1, 1, true);
        registerData("sprites/keyring.png", 1, 1, false);
        registerData("sprites/treasure.png", 1, 1, true);
        registerData("sprites/smart_bomb.png", 1, 1, true);
        registerData("sprites/potion_life.png", 1, 1, true);
        registerData("sprites/potion_defense.png", 1, 1, true);
        registerData("sprites/potion_magic.png", 1, 1, true);
        registerData("sprites/potion_physical.png", 1, 1, true);
        registerData("sprites/potion_poison.png", 1, 1, true);
        registerData("sprites/potion_speed.png", 1, 1, true);
        registerData("sprites/food.png", 1, 1, true);
        registerData("sprites/wall.png", 16, 1, true);
        registerData("sprites/spawner_ghost.png", 3, 1, true);
        registerData("sprites/spawner_grunt.png", 3, 1, true);
    }

    public ImageData getImageData(int i){
        return this.map.get(i);
    }

    private void registerData(String imageLink, int spriteNumX, int spriteNumY, boolean canBePlaced){

        InputStream inputStream = getClass().getResourceAsStream(imageLink);
        if(inputStream == null)
            return;

        Image image = new Image(inputStream);

        double ySize = image.getHeight()/spriteNumY;
        double xSize = image.getWidth()/spriteNumX;

        for(int x = 0; x<spriteNumX; x++){
            for (int y = 0; y<spriteNumY; y++){
                map.put(id, new ImageData(image, xSize * x, ySize * y, xSize, ySize, canBePlaced, id));
                id++;
            }
        }
    }

}
