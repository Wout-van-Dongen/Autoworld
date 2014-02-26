package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.MensException;
import be.vdab.voertuigen.div.Nummerplaat;
import java.awt.Color;

public class Personenwagen extends Voertuig {

    private Color kleur;

    public Personenwagen(String merk, Datum eIGN, int prijs, int zitPl, Color kleur, Mens bestuurder) {

        super(merk, eIGN, prijs, zitPl, bestuurder, new Mens[0]);

        this.kleur = kleur;
        if (zitPl > 8) {
            throw new IllegalArgumentException();
        }
    }

    public Color getKleur() {
        return kleur;
    }

    public void setKleur(Color kleur) {
        this.kleur = kleur;
    }

    public int getZitplaatsen() {

        return super.getMAX_ZITPLAATSEN();

    }

    @Override
    public int compareTo(Voertuig vt) {
        return super.compareTo(vt);
    }

    @Override
    public String toString() {
        return String.format("%s %d", super.toString(), 4);
    }
}
