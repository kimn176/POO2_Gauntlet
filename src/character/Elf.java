package character;

import grid.Carte;
import util.ImageEnum;

public class Elf extends Character{
    public Elf(Carte carte) {
        super(carte, "Elf", 100, 25, 5f, 1, 35);
    }

    @Override
    public ImageEnum getImageEnum() {
        return ImageEnum.ELF;
    }
}
