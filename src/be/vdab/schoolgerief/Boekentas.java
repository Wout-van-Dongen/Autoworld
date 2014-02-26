package be.vdab.schoolgerief;

import be.vdab.util.Laadbaar;
import be.vdab.util.Volume;
import java.io.Serializable;
import java.util.Objects;

public class Boekentas implements Laadbaar, Serializable {

    private Volume laadVolume;
    private String kleur;

//Constructor===========================================================
    public Boekentas(String kleur, Volume laadVolume) {
        if (kleur == null || laadVolume == null) {
            throw new IllegalArgumentException();
        }
        this.laadVolume = laadVolume;
        this.kleur = kleur;
    }

//Getters===============================================================
    @Override
    public Volume getLaadvolume() {
        return laadVolume;
    }

    public String getKleur() {
        return kleur;
    }

//Setters===============================================================
    @Override
    public void setLaadvolume(Volume laadVolume) {
        if (laadVolume == null) {
            throw new IllegalArgumentException();
        } else {
            this.laadVolume = laadVolume;
        }
    }

    public void setKleur(String kleur) {
        if (kleur == null) {
            throw new IllegalArgumentException();
        } else {
            this.kleur = kleur;
        }
    }

//Methods===============================================================
    @Override
    public int hashCode() {
        int hc = 5;
        hc = 83 * hc + Objects.hashCode(this.laadVolume);
        hc = 83 * hc + Objects.hashCode(this.kleur);
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
        Boekentas bt = (Boekentas) obj;
        if (!Objects.equals(this.laadVolume, bt.laadVolume) || !Objects.equals(this.kleur, bt.kleur)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
    return String.format("boekentas %s %s",kleur,laadVolume);
    }

}
