package projet_poo2;

import javafx.scene.image.Image;
import projet_poo2.image.ImageData;

import java.util.HashMap;
import java.util.Map;

public class ImageManager {

    private Map<String, ImageData> map = new HashMap<>();

    public ImageManager(){
        this.loadImage();
    }

    public Map<String, ImageData> getMap() {
        return map;
    }

    private void loadImage(){
        map.put("exit", new ImageData(new Image(getClass().getResource("sprites/exit.png").toExternalForm()), 1,1));
        map.put("key", new ImageData(new Image(getClass().getResource("sprites/key.png").toExternalForm()), 1,1));
        map.put("keyring", new ImageData(new Image(getClass().getResource("sprites/keyring.png").toExternalForm()), 1,1, false));
        map.put("potion_life", new ImageData(new Image(getClass().getResource("sprites/potion_life.png").toExternalForm()), 1,1));
        map.put("potion_defense", new ImageData(new Image(getClass().getResource("sprites/potion_defense.png").toExternalForm()), 1,1));
        map.put("potion_magic", new ImageData(new Image(getClass().getResource("sprites/potion_magic.png").toExternalForm()), 1,1));
        map.put("potion_physical", new ImageData(new Image(getClass().getResource("sprites/potion_physical.png").toExternalForm()), 1,1));
        map.put("potion_poison", new ImageData(new Image(getClass().getResource("sprites/potion_poison.png").toExternalForm()), 1,1));
        map.put("potion_speed", new ImageData(new Image(getClass().getResource("sprites/potion_speed.png").toExternalForm()), 1,1));
        map.put("food", new ImageData(new Image(getClass().getResource("sprites/food.png").toExternalForm()), 1,1));
        map.put("treasure", new ImageData(new Image(getClass().getResource("sprites/treasure.png").toExternalForm()), 1,1));
        map.put("wall", new ImageData(new Image(getClass().getResource("sprites/wall.png").toExternalForm()), 16,1));
        map.put("smart_bomb", new ImageData(new Image(getClass().getResource("sprites/smart_bomb.png").toExternalForm()), 1,1));
        map.put("spawner_ghost", new ImageData(new Image(getClass().getResource("sprites/spawner_ghost.png").toExternalForm()), 3,1));
        map.put("spawner_grunt", new ImageData(new Image(getClass().getResource("sprites/spawner_grunt.png").toExternalForm()), 3,1));
        map.put("floor", new ImageData(new Image(getClass().getResource("sprites/floor.png").toExternalForm()), 1,1));
    }

    public ImageData getImageData(String imageName){
        return this.map.get(imageName);
    }

}
