package be.vdab.util.mens;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Mens implements Comparable<Mens>, Serializable {

    String naam;
    ArrayList<Rijbewijs> rijbewijs;

//Constructor===========================================================
    public Mens(String naam, Rijbewijs... args) {
        this.naam = naam;
        rijbewijs = new ArrayList<Rijbewijs>();
        for (Rijbewijs bewijs : args) {
            if (!rijbewijs.contains(bewijs)) {
                rijbewijs.add(bewijs);
            }
        }
    }

//Getters
    public String getNaam() {
        return naam;
    }

    public Rijbewijs[] getRijbewijs() {
        Rijbewijs[] rbn = new Rijbewijs[rijbewijs.size()];
        for (int i = 0; i < rijbewijs.size(); i++) {
            rbn[i] = rijbewijs.get(i);
        }
        return rbn;

    }

//Methods===============================================================
    @Override
    public int compareTo(Mens mens) {
        return naam.compareTo(mens.getNaam());
    }

    @Override
    public String toString() {
        String output = naam;
        if (!rijbewijs.isEmpty()) {
            output += "(";
            for (Rijbewijs rb : rijbewijs) {
                output += rb.toString() + ", ";
            }
            output = output.substring(0, (output.length() - 2));
            output += ")";
        }
        return output;
    }

    @Override
    public int hashCode() {
        int hc = 5;
        hc = 97 * hc + Objects.hashCode(naam);
        hc = 97 * hc + Objects.hashCode(rijbewijs);
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
        Mens mns = (Mens) obj;
        if (!Objects.equals(naam, mns.naam) || !Objects.equals(rijbewijs, mns.rijbewijs)) {
            return false;
        }
        return true;
    }

}
