package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.MensException;
import be.vdab.util.mens.Rijbewijs;
import be.vdab.voertuigen.div.DIV;
import be.vdab.voertuigen.div.Nummerplaat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public abstract class Voertuig implements Serializable, Comparable<Voertuig> {

    private final Nummerplaat nrPlaat;
    private Datum eersteIGN;
    private String merk;
    private int aankoopprijs;
    private final int zitPlaatsen;
    private ArrayList<Mens> inzittenden;
    private ArrayList<Rijbewijs> vereistRijbewijzen = new ArrayList<Rijbewijs>();

//Constructor==================================================================
    public Voertuig(String merk, Datum eIGN, int prijs, int zitPl, Mens bestuurder, Mens... args) {
        vereistRijbewijzen.add(Rijbewijs.B);
        vereistRijbewijzen.add(Rijbewijs.BE);
        this.merk = merk;
        this.eersteIGN = eIGN;
        this.aankoopprijs = prijs;
        this.nrPlaat = DIV.getInstance().getNummerplaat();
        this.zitPlaatsen = zitPl;
        inzittenden = new ArrayList<Mens>();
        if (zitPl < 1) {
            throw new IllegalArgumentException();
        }
        if (checkCorrectRijbewijs(bestuurder)) {
            inzittenden.add(bestuurder);
        } else {
            throw new MensException();
        }
        for (Mens pers : args) {
            if (!isIngezetene(pers)) {
                inzittenden.add(pers);
            }
            if (inzittenden.size() > zitPlaatsen) {
                throw new MensException();
            }

        }
    }

//Getters======================================================================
    public Datum getDatumEersteIngebruikname() {
        return eersteIGN;
    }

    public void setDatumEersteIngebruikname(Datum eersteIGN) {
        this.eersteIGN = eersteIGN;
    }

    public String getMerk() {
        return merk;
    }

    public int getAankoopprijs() {
        return aankoopprijs;
    }

    public Nummerplaat getNummerplaat() {
        return nrPlaat;
    }

    protected Rijbewijs[] getToegestaneRijbewijzen() {
        return new Rijbewijs[]{};
    }

    protected int getMAX_ZITPLAATSEN() {
        return zitPlaatsen;
    }

    public Mens getBestuurder() {
        return inzittenden.get(0);
    }

    public ArrayList<Mens> getIngezetenen() {

        ArrayList<Mens> tempClone = (ArrayList< Mens>) inzittenden.clone();

        return sortMensByNaam(tempClone);
    }

    public ArrayList<Mens> getIngezeteneExclusiefBestuurder() {
        ArrayList<Mens> tempClone = (ArrayList< Mens>) inzittenden.clone();

        tempClone.remove(0);
        return sortMensByNaam(tempClone);
    }

    public boolean isIngezetene(Mens pers) {
        return inzittenden.contains(pers);
    }

//Setters=====================================================================
    public void setMerk(String merk) {
        this.merk = merk;
    }

    public void setAankoopprijs(int aankoopprijs) {
        this.aankoopprijs = aankoopprijs;
    }

    public void setBestuurder(Mens mens) {
        if (checkCorrectRijbewijs(mens)) { //Kijkt na of de bestuurder het juiste rijbewijs heeft.
            if (isIngezetene(mens)) { //Kijkt na of de persoon al in de auto zit.
                inzittenden.set(inzittenden.indexOf(mens), inzittenden.get(0)); //Wisselt de nieuwe bestuurder van plaats met de oude
                inzittenden.set(0, mens);
            } else if (inzittenden.size() < zitPlaatsen) { //Kijkt na of er nog plaats is in de auto voor iemand nieuw
                inzittenden.add(0, mens); //Zet de bestuurder in de auto en laat de andere een plaats opschuiven.
            } else if (inzittenden.size() >= zitPlaatsen) {
                throw new MensException();
            }
        } else {
            throw new MensException();
        }

    }

    public void addIngezetene(Mens pers) {
        if (!isIngezetene(pers) && (inzittenden.size()) < zitPlaatsen) {
            inzittenden.add(pers);
        } else if (isIngezetene(pers)) {

        } else if (inzittenden.size() >= zitPlaatsen) {
            throw new MensException();
        }
    }

//Methods=====================================================================
//===Comperators==============================================================
    @Override
    /* public int compareTo(Nummerplaat np) {
     return nrPlaat.compareTo(np);
     }
     */
    public int compareTo(Voertuig vt) {
        return nrPlaat.compareTo(vt.getNummerplaat());
    }

    public int compareTo(String merk) {
        return this.merk.compareTo(merk);
    }

    public static MerkComperator getMerkComparator() {
        return new MerkComperator();
    }

    public static AankoopprijsComperator getAankoopprijsComparator() {
        return new AankoopprijsComperator();
    }

//===================
    @Override
    public int hashCode() {
        int hc = 3;
        hc = 37 * hc + Objects.hashCode(this.nrPlaat);
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
        Voertuig vt = (Voertuig) obj;
        if (!Objects.equals(nrPlaat, vt.nrPlaat)) {
            return false;
        }
        return true;
    }

    public ArrayList<Mens> sortMensByNaam(ArrayList<Mens> arraylist) {
        Collections.sort(arraylist, new Comparator<Mens>() {
            public int compare(Mens m1, Mens m2) {
                return m1.getNaam().compareToIgnoreCase(m2.getNaam());
            }
        });
        return arraylist;
    }

    private boolean checkCorrectRijbewijs(Mens bestuurder) {
        for (Rijbewijs rb : bestuurder.getRijbewijs()) {
            if (vereistRijbewijzen.contains(rb)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String pers = "";

        for (int i = 1; i < inzittenden.size(); i++) {
            pers += inzittenden.get(i).getNaam() + ", ";
        }
        if (!pers.isEmpty()) {
            pers = pers.substring(0, (pers.length() - 2));
            pers = String.format(" [%s]", pers);
        }
        return String.format("%s %s %s %s %s%s", nrPlaat, merk, eersteIGN, aankoopprijs, inzittenden.get(0), pers);

    }

//Nested Classes===============================================================
    static class MerkComperator implements Comparator<Voertuig>, Serializable {

        @Override
        public int compare(Voertuig vt1, Voertuig vt2) {
            return vt1.getMerk().compareTo(vt2.getMerk());
        }
    }

    static class AankoopprijsComperator implements Comparator<Voertuig>, Serializable {

        @Override
        public int compare(Voertuig vt1, Voertuig vt2) {
            if (vt1.getAankoopprijs() == vt2.getAankoopprijs()) {
                return 0;
            } else if (vt1.getAankoopprijs() > vt2.getAankoopprijs()) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
