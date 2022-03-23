package Modules;

import Modules.Frage;

public class JaNein extends Frage {
    public boolean value;

    public JaNein(String s, int knnmmr, boolean a) {
        super(s, knnmmr);
        value = a;
    }
}
