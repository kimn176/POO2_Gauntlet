package util;

import java.util.*;

public class PlayerProfile {
    private String name;
    private int defense;
    private int damage;
    private int speed;
    private int rangeAttack;

    // Constructeur
    public PlayerProfile(String name, int defense, int damage, int speed, int rangeAttack) {
        this.name = name;
        this.defense = defense;
        this.damage = damage;
        this.speed = speed;
        this.rangeAttack = rangeAttack;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getDefense() {
        return defense;
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }

    public int getRangeAttack() {
        return rangeAttack;
    }
}
