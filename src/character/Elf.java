package character;

import util.ImageEnum;

public class Elf extends Character{
    public Elf() {
        super("Elf", 100, 25, 5f, 1, 35);
    }

    @Override
    public ImageEnum getImageEnum() {
        return ImageEnum.ELF;
    }
}
