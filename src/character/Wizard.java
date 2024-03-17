package character;

import util.ImageEnum;

public class Wizard extends Character{
    public Wizard() {
        super("Wizard", 100, 25, 5f, 1, 35);
    }

    @Override
    public ImageEnum getImageEnum() {
        return ImageEnum.WIZARD;
    }
}
