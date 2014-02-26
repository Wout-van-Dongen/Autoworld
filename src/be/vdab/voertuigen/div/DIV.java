package be.vdab.voertuigen.div;

public class DIV {

    private int num = 0;
    private static final DIV instance = new DIV();

    public static DIV getInstance() {

        return instance;
    }

    public Nummerplaat getNummerplaat() {

        num++;
        if (num > 999) {
            num = 1;
        }
        return new Nummerplaat(String.format("AAA%03d", num));

    }

}
