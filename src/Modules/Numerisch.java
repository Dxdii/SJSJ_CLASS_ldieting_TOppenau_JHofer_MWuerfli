package Modules;

import Modules.Frage;

public class Numerisch extends Frage {
    public float d;
    public Numerisch(String s) {
        super(s);
    }
    public Numerisch(String b, float s){
        super(b);
        d =s;
    }
}