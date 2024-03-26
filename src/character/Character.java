package character;

import grid.Carte;
import grid.CarteGridCase;
import javafx.scene.image.ImageView;
import util.ImageEnum;
import util.Vector;

import java.util.ArrayList;

public abstract class Character {
    protected String name;
    protected float pv, damage, speed, rangeAttack;
    protected int defense; // Percent
    protected float damageBoost, defenseBoost, speedBoost;
    protected ImageView imageView;
    protected final Carte carte;

    static private final ArrayList<Character> characters = new ArrayList<>();

    static public boolean warrior, wizard, elf, valkyrie = false;

    public Character(String name, float pv, float damage, float speed, int rangeAttack, int defense) {
        this.name = name;
        if(pv > 0)
            this.pv = pv;
        else this.pv = 1;
        this.damage = damage;
        this.defense = defense;
        this.speed = speed;
        this.rangeAttack = 0;
        this.imageView = getImageEnum().generateImageData(0,0).generateImageView();
        this.imageView.setPreserveRatio(true);
        this.imageView.setFitWidth(carte.getDefWidth());
        this.carte.getChildren().add(this.imageView);
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

    public float getRangeAttack() {
        return rangeAttack;
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

    public ImageView getImageView(){
        return this.imageView;
    }

    public boolean move(double x, double y){
        double nextX = this.imageView.getX()+x;
        double nextY = this.imageView.getY()+y;

        CarteGridCase carteGridCase = carte.getPixelCase(nextX, nextY);
        if(carteGridCase == null || carteGridCase.getImageData().getImageEnum().isColision()) {
            System.out.println("Collision");
            return false;
        }

        Vector normal = new Vector(this.imageView.getX(), nextX, this.imageView.getY(), nextY).normalize().multiply(20);
        Vector left = normal.turnLeft();

        CarteGridCase leftCase = carte.getPixelCase(nextX+left.getMoveX(), nextY+left.getMoveY());
        if(leftCase == null || leftCase.getImageData().getImageEnum().isColision()) {
            System.out.println("Collision");
            return false;
        }

        Vector right = normal.turnRight();
        CarteGridCase rightCase = carte.getPixelCase(nextX+right.getMoveX(), nextY+right.getMoveY());
        if(rightCase == null || rightCase.getImageData().getImageEnum().isColision()){
            System.out.println("Collision");
            return false;
            }

        this.imageView.setX(nextX);
        this.imageView.setY(nextY);

        return true;
    }

    public CarteGridCase getCharacterCase(){
        return carte.getPixelCase(this.getX()+(carte.getDefWidth() /2), this.getY()+(carte.getDefWidth() /2));
    }

    public double getX(){
        return this.imageView.getX();
    }

    public double getY(){
        return this.imageView.getY();
    }

    public void pickupItem(){
        CarteGridCase carteGridCase = this.getCharacterCase();
        ImageEnum imageEnum = carteGridCase.getImageData().getImageEnum();
        this.carte.setCell(carteGridCase.getX(), carteGridCase.getY(), ImageEnum.FLOOR.generateImageData(0, 0));
    }

}