package character;

import util.ImageEnum;

public class Valkyrie extends Character{
    public Valkyrie() {
        super("Valkyrie", 100, 25, 5f, 1, 35);
    }

    @Override
    public ImageEnum getImageEnum() {
        return ImageEnum.VALKYRIE;
    }
}
