package Modules;

import Modules.Frage;

public class Numerisch extends Frage {
    public float d;
    public Numerisch(String s, int knnmmr) {
        super(s, knnmmr);
    }
    public Numerisch(String b, int knnmmr, float s){
        super(b, knnmmr);
        d =s;
    }
}
