package be.vdab.voertuigen.div;

import java.io.Serializable;
import java.util.Objects;

public class Nummerplaat implements Comparable<Nummerplaat>, Serializable {

    private final String nrPlaat;

//Contructor============================================================
    Nummerplaat(String nrPlaat) {
        this.nrPlaat = nrPlaat;
    }

//Getters===============================================================
    public String getPlaat() {
        return nrPlaat;
    }

//Methods===============================================================
    @Override
    public int compareTo(Nummerplaat t) {
        return nrPlaat.compareToIgnoreCase(t.getPlaat());
    }

    @Override
    public String toString() {
        return getPlaat();
    }

    @Override
    public int hashCode() {
        int hc = 7;
        hc = 89 * hc + Objects.hashCode(this.nrPlaat);
        return hc;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Nummerplaat np = (Nummerplaat) obj;
        if (!Objects.equals(this.nrPlaat, np.nrPlaat)) {
            return false;
        }
        return true;
    }

}
