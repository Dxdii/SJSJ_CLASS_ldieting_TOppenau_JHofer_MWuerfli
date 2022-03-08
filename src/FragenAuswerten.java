
public class FragenAuswerten {

    private int kennnummer;
    private int type;
    private String frage;
    private int min;
    private int max;

    FragenAuswerten() {

    }

    FragenAuswerten(int kennnummer, int type, String frage, int min, int max) {
        this.kennnummer = kennnummer;
        this.type = type;
        this.frage = frage;
        this.min = min;
        this.max = max;

    }

    public int getIndex() {
        return kennnummer;
    }

    public int getType() {
        return type;
    }

    public String getFrage() {
        return frage;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public void setKennnummer(int index) {
        this.kennnummer = kennnummer;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setFrage(String frage) {
        this.frage = frage;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }


}
