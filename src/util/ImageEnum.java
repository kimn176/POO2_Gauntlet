package util;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

public enum ImageEnum {

    FLOOR("../sprites/floor.png", 1, 1, true, false, 1),
    EXIT("../sprites/exit.png", 1, 1, true, true, 2),
    KEY("../sprites/key.png", 1, 1, true, false, 3),
    KEYRING("../sprites/keyring.png", 1, 1, false, false, 4),
    TREASURE("../sprites/treasure.png", 1, 1, true, true, 5),
    SMART_BOMB("../sprites/smart_bomb.png", 1, 1, true, false, 6),
    POTION_LIFE("../sprites/potion_life.png", 1, 1, true, false, 7),
    POTION_DEFENSE("../sprites/potion_defense.png", 1, 1, true, false, 8),
    POTION_MAGIC("../sprites/potion_magic.png", 1, 1, true, false, 9),
    POTION_PHYSICAL("../sprites/potion_physical.png", 1, 1, true, false, 10),
    POTION_POISON("../sprites/potion_poison.png", 1, 1, true, false, 11),
    POTION_SPEED("../sprites/potion_speed.png", 1, 1, true, false, 12),
    FOOD("../sprites/food.png", 1, 1, true, false, 13),
    WALL("../sprites/wall.png", 16, 1, true, true, 14),
    SPAWNER_GHOST("../sprites/spawner_ghost.png", 3, 1, true, true, 15),
    SPAWNER_GRUNT("../sprites/spawner_grunt.png", 3, 1, true, true, 16),
    SPAWNER("../sprites/spawner.png", 3, 2, true, true, 26),
    DOOR("../sprites/door.png", 15, 1, true, true, 32),

    //Playable
    WARRIOR("../sprites/warrior.png", 8, 2, false, true, 18),
    VALKYRIE("../sprites/valkyrie.png", 8, 2, false, true, 19),
    ELF("../sprites/elf.png", 8, 2, false, true, 20),
    WIZARD("../sprites/wizard.png", 8, 2, false, true, 21),

    //Monsters
    GRUNT("../sprites/grunt.png", 8, 2, true, true, 17),
    GHOST("../sprites/ghost.png", 8, 2, true, true, 22),
    LOBBER("../sprites/lobber.png", 8, 2, true, true, 23),
    DEATH("../sprites/death.png", 8, 2, true, true, 24),
    DAEMON("../sprites/daemon.png", 8, 2, true, true, 25),

    //PROJECTILE
    PROJECTILE_DAEMON("../sprites/projectile_daemon.png", 8, 1, false, false, 26),
    PROJECTILE_ELF("../sprites/projectile_elf.png", 8, 1, false, false, 27),
    PROJECTILE_LOBBER("../sprites/projectile_lobber.png", 8, 1, false, false, 28),
    PROJECTILE_VALKYRIE("../sprites/projectile_valkyrie.png", 8, 1, false, false, 29),
    PROJECTILE_WARRIOR("../sprites/projectile_warrior.png", 8, 1, false, false, 30),
    PROJECTILE_WIZARD("../sprites/projectile_wizard.png", 8, 1, false, false, 31);



    final String file;
    final int spriteNumX, spriteNumY, id;
    double xSize, ySize;
    final boolean canBePlaced, reversed, colision;
    final Image image;


    ImageEnum(String file, int spriteNumX, int spriteNumY, boolean canBePlaced, boolean colision, int id){
        this.file = file;
        this.spriteNumX = spriteNumX;
        this.spriteNumY = spriteNumY;
        this.canBePlaced = canBePlaced;
        this.colision = colision;
        this.id = id;
        this.reversed = spriteNumY>spriteNumX;

        InputStream inputStream = getClass().getResourceAsStream(this.file);
        if(inputStream == null) {
            System.out.print(file + " not found\n");
            this.image = null;
            return;
        }

        this.image = new Image(inputStream);

        ySize = image.getHeight() / spriteNumY;
        xSize = image.getWidth() / spriteNumX;
    }

    public int getSubSpriteId(int spriteX, int spriteY){
        if(this.spriteNumX > spriteX && this.spriteNumY > spriteY) {
            if(!reversed)
                return (spriteX * this.spriteNumY) + spriteY;
            else return (spriteY * this.spriteNumX) + spriteX;
        }else return -1;
    }

    public int[] reverseSubSpriteId(int subSpriteId) {
        int spriteX, spriteY;
        if(!reversed) {
            spriteX = subSpriteId / this.spriteNumY;
            spriteY = subSpriteId % this.spriteNumY;
        }else{
            spriteX = subSpriteId / this.spriteNumX;
            spriteY = subSpriteId % this.spriteNumX;
        }
        return new int[]{spriteX, spriteY};
    }

    public void updateImageView(ImageView imageView, int x, int y){
        imageView.setViewport(new Rectangle2D(xSize * x, ySize * y, xSize, ySize));
    }

    public ImageData generateImageData(int spriteX, int spriteY){
        return new ImageData(this, spriteX, spriteY, xSize, ySize);
    }

    public Image getImage(){
        return this.image;
    }

    public int getId() {
        return id;
    }

    public boolean canBePlaced() {
        return canBePlaced;
    }

    public int getSpriteNumX() {
        return spriteNumX;
    }

    public int getSpriteNumY(){
        return this.spriteNumY;
    }

    public static ImageEnum getByID(int id){
        for (ImageEnum imageEnum : ImageEnum.values())
            if(imageEnum.getId() == id)
                return imageEnum;
        return null;
    }

    public boolean isColision(){
        return this.colision;
    }
}
