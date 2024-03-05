package util;

public abstract class Character {
    protected String name;
    protected int pv;
    protected  int damage;
    protected int defense;
    protected int speed;
    protected int id;

    public Character(String name, int pv, int damage, int defense, int speed, int id) {
        this.name = name;
        if(pv > 0)
            this.pv = pv;
        else this.pv = 1;
        this.damage = damage;
        this.defense = defense;
        this.speed = speed;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getDamage() {
        return damage;
    }

    public int getPv() {
        return pv;
    }

    public int getDefense() {
        return defense;
    }

    public String getName() {
        return name;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }

    public void addPV(int pv) {
        if(pv >= 0)
            this.pv += pv;
    }
}