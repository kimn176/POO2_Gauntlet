package projet_poo2;

public class Pair <R,T>{
    R r;
    T t;
    public Pair(R r, T t){
        this.r = r;
        this.t = t;
    }

    @Override
    public String toString(){
        return "R: "+r+" T: "+t;
    }
}
