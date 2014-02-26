package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.Laadbaar;
import be.vdab.util.Volume;
import be.vdab.util.mens.Mens;
import be.vdab.voertuigen.div.Nummerplaat;
import java.util.Arrays;

public class Vrachtwagen extends Voertuig implements Laadbaar {

    private int maximaalToegelatenMassa, aantalAssen;
    private Volume laadVolume;

    public Vrachtwagen(String merk, Datum eIGN, int prijs, int zitPl, Volume laadVolume, int maxMassa, int assen, Mens bestuurder, Mens... args) {
        super(merk, eIGN, prijs, zitPl, bestuurder, args);

        maximaalToegelatenMassa = maxMassa;
        aantalAssen = assen;
        this.laadVolume = laadVolume;

        if (zitPl > 3) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Volume getLaadvolume() {
        return laadVolume;
    }

    public int getMaximaalToegelatenMassa() {
        return maximaalToegelatenMassa;
    }

    public void setMaximaalToegelatenMassa(int maximaalToegelatenMassa) {
        this.maximaalToegelatenMassa = maximaalToegelatenMassa;
    }

    public int getAantalAssen() {
        return aantalAssen;
    }

    public void setAantalAssen(int aantalAssen) {
        this.aantalAssen = aantalAssen;
    }

    @Override
    public void setLaadvolume(Volume laadVolume) {
        this.laadVolume = laadVolume;
    }

    @Override
    public int compareTo(Voertuig vt) {
        return super.compareTo(vt);
    }
    
    @Override
    public String toString(){
    return String.format("%s assen:%s, maximaal toegelaten massa:%s, laadvolume:%s", super.toString(), aantalAssen, maximaalToegelatenMassa, laadVolume.toString());
    }

}
