package character;

import util.ImageEnum;

public abstract class Character {
    protected String name;
    protected float pv, damage, speed;
    protected int defense; // Percent
    protected float damageBoost, defenseBoost, speedBoost;

    public Character(String name, float pv, float damage, float speed, int defense) {
        this.name = name;
        if(pv > 0)
            this.pv = pv;
        else this.pv = 1;
        this.damage = damage;
        this.defense = defense;
        this.speed = speed;
    }

    public float getDamage() {
        return damage;
    }

    public float getPv() {
        return pv;
    }

    public float getDefense() {
        return defense;
    }

    public String getName() {
        return name;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getSpeed() {
        return speed;
    }

    public float getDamageBoost() {
        return damageBoost;
    }

    public float getDefenseBoost() {
        return defenseBoost;
    }

    public float getSpeedBoost() {
        return speedBoost;
    }

    public void addPV(float pv) {
        if(pv >= 0)
            this.pv += pv;
    }

    public void setDamageBoost(float damageBoost) {
        this.damageBoost = damageBoost;
    }

    public void setDefenseBoost(float defenseBoost) {
        this.defenseBoost = defenseBoost;
    }

    public void setSpeedBoost(float speedBoost) {
        this.speedBoost = speedBoost;
    }

    public float calculateDamage(Character against){
        float damage = this.damage * (1f+this.damageBoost);
        float percentBlocked = against.defense * (1+against.defenseBoost);
        return damage * (100-percentBlocked) / 100;
    }

    public boolean priorityOver(Character character){
        return this.speed * (1f+this.speedBoost) >= (character.speed * (1f+character.speedBoost));
    }

    public abstract ImageEnum getImageEnum();

}