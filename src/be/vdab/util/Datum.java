package be.vdab.util;

import java.io.Serializable;

public class Datum implements Comparable<Datum>, Serializable {

    private final int dag, maand, jaar;
    private final int[] dagenInEenMaand = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

//Constructor===========================================================
    public Datum(int dag, int maand, int jaar) throws DatumException {
        if (jaar < 1583 || jaar > 4099) { //Checking if the dates do not exceed the predefined borders.
            throw new DatumException();
        }
        if (dag <= 0 || maand <= 0) { //Checking if negative days or months occure.
            throw new DatumException();
        }
        if (maand > 12) { //Checking if the month does not exceed the number of months in a year.
            throw new DatumException();
        }
        if (dag > getAantalDagen(maand, jaar)) { //Checking if the day does not exceed the number of days in that month.
            throw new DatumException();
        }

        this.dag = dag;
        this.maand = maand;
        this.jaar = jaar;
    }

//Getters================================================================
    public int getDag() {
        return dag;
    }

    public int getMaand() {
        return maand;
    }

    public int getJaar() {
        return jaar;
    }

//Methods=================================================================
    private boolean isSchrikkelJaar(int jaar) {
        if (jaar % 4 == 0 && (jaar % 100 != 0 || jaar % 400 == 0)) {
            return true;
        }
        return false;
    }

    private int getAantalDagen(int maand, int jaar) {
        int aantal = dagenInEenMaand[(maand - 1)];
        if (maand == 2 && isSchrikkelJaar(jaar)) {
            return aantal + 1;
        }
        return aantal;
    }

    @Override
    public int compareTo(Datum datum) {
        if (jaar < datum.getJaar()) {
            return -1;
        } else if (jaar > datum.getJaar()) {
            return 1;
        } else {
            if (maand < datum.getMaand()) {
                return -1;
            } else if (maand > datum.getMaand()) {
                return 1;
            } else {
                if (dag < datum.getDag()) {
                    return -1;
                } else if (dag > datum.getDag()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }

    @Override
    public int hashCode() {
        int hc = 5;
        hc = 23 * hc + this.dag;
        hc = 23 * hc + this.maand;
        hc = 23 * hc + this.jaar;
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
        Datum dt = (Datum) obj;
        if (dag != dt.dag || maand != dt.maand || jaar != dt.jaar) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%d", dag, maand, jaar);
    }

}
