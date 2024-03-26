package character;

import grid.Carte;
import util.ImageEnum;

public class Wizard extends Character{
    public Wizard(Carte carte) {
        super(carte, "Wizard", 100, 25, 5f, 1, 35);
    }

    @Override
    public ImageEnum getImageEnum() {
        return ImageEnum.WIZARD;
    }
}
