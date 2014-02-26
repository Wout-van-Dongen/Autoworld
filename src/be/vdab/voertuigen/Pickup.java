package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.Laadbaar;
import be.vdab.util.Volume;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.MensException;
import java.awt.Color;

public class Pickup extends Personenwagen implements Laadbaar {

    private Volume laadVolume;

    public Pickup(String merk, Datum eIGN, int prijs, int zitPl, Color kleur,Volume laadVolume, Mens bestuurder) {
        super(merk, eIGN, prijs, zitPl, kleur, bestuurder);
        this.laadVolume = laadVolume;

    }

    @Override
    public Volume getLaadvolume() {
        return laadVolume;

    }

    @Override
    public void setLaadvolume(Volume laadVolume) {
        this.laadVolume = laadVolume;
    }
    
    @Override
    public String toString(){
        return String.format("%s %s", super.toString(), laadVolume.toString());
    }

}
