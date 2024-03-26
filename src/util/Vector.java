package util;

public class Vector {

    double moveX, moveY;

    public Vector(double fromX, double toX, double fromY, double toY){
        this.moveX = toX-fromX;
        this.moveY = toY-fromY;
    }

    public Vector(double moveX, double moveY){
        this.moveX = moveX;
        this.moveY = moveY;
    }

    public double getMoveX() {
        return moveX;
    }

    public double getMoveY() {
        return moveY;
    }

    public Vector turnLeft(){
        return new Vector(moveY*-1,  moveX);
    }

    public Vector turnRight(){
        return new Vector(moveY,  moveX*-1);
    }

    public double getLength(){
        return Math.sqrt((this.moveX * this.moveX) + (this.moveY * this.moveY));
    }

    public Vector normalize(){
        double length = this.getLength();
        return new Vector(this.moveX/length, this.moveY/length);
    }

    public Vector multiply(double mult){
        return new Vector(this.moveX*mult, this.moveY*mult);
    }

    @Override
    public Vector clone(){
        return new Vector(moveX, moveY);
    }

}
