package Modules;

import Modules.Frage;

public class vonBis extends Frage {
    public int min;
    public int max;
    public int value;
    public vonBis(String s, int knnmmr, int val){
        super(s, knnmmr);
        value = val;
    }
}
