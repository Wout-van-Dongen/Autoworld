package be.vdab.util.mens;

public enum Rijbewijs {

    A("A"), B("B"), BE("B+E"), C("C"), CE("C+E"), D("D"), DE("D+E");

    String value;

    Rijbewijs(String bewijs) {
        value = bewijs;
    }

    @Override
    public String toString() {

        return value;
    }

}
