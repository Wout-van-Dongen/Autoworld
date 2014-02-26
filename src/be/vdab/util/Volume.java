package be.vdab.util;

import java.io.Serializable;
import java.util.Objects;

public final class Volume implements Comparable<Volume>, Serializable {

    private final int hoogte, breedte, diepte;
    private final Maat maat;

//Constructor===========================================================
    public Volume(int hoogte, int breedte, int diepte, Maat maat) throws VolumeException {
        if(hoogte < 0 || breedte <0 || diepte < 0){ //Checking for negative values
        throw new VolumeException();
        }
        this.hoogte = hoogte;
        this.breedte = breedte;
        this.diepte = diepte;
        this.maat = maat;
    }

//Getters===============================================================
    public int getHoogte() {
        return hoogte;
    }

    public int getBreedte() {
        return breedte;
    }

    public int getDiepte() {
        return diepte;
    }

    public Maat getMaat() {
        return maat;
    }

    public long getVolume() {
        int volume = hoogte * breedte * diepte;
        switch (maat) {
            case centimeter:

                break;
            case decimeter:
                volume *= 1000;
                break;
            case meter:
                volume *= 1000000;
                break;
        }
        return volume;
    }

//Methods===============================================================
    
    @Override
    public int compareTo(Volume vol) {
        if (this.getVolume() == vol.getVolume()) {
            return 0;
        } else if (getVolume() < vol.getVolume()) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.hoogte;
        hash = 83 * hash + this.breedte;
        hash = 83 * hash + this.diepte;
        hash = 83 * hash + Objects.hashCode(this.maat);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Volume other = (Volume) obj;
        if (this.hoogte != other.hoogte) {
            return false;
        }
        if (this.breedte != other.breedte) {
            return false;
        }
        if (this.diepte != other.diepte) {
            return false;
        }
        if (this.maat != other.maat) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
    return String.format("%d(h)x%d(b)x%d(d) %s", hoogte,breedte,diepte,maat);
    }

}
