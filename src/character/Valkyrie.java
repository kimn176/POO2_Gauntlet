package character;

import grid.Carte;
import util.ImageEnum;

public class Valkyrie extends Character{
    public Valkyrie(Carte carte) {
        super(carte, "Valkyrie", 100, 25, 5f, 1, 35);
    }

    @Override
    public ImageEnum getImageEnum() {
        return ImageEnum.VALKYRIE;
    }
}
