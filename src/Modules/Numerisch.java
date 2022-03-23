package Modules;

import Modules.Frage;

public class Numerisch extends Frage {
    public float value;
    public Numerisch(String s, int knnmmr) {
        super(s, knnmmr);
    }
    public Numerisch(String b, int knnmmr, float s){
        super(b, knnmmr);
        value =s;
    }
}
