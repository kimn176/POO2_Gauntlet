package character;

import grid.Carte;
import util.ImageEnum;

public class Warrior extends Character{
    public Warrior(Carte carte) {
        super(carte,"Warrior", 100, 25, 5f, 1, 35);
    }

    @Override
    public ImageEnum getImageEnum() {
        return ImageEnum.WARRIOR;
    }
}
