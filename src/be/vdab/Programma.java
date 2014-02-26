package be.vdab;

import be.vdab.util.Datum;
import be.vdab.util.DatumException;
import be.vdab.util.Maat;
import be.vdab.util.Volume;
import be.vdab.util.VolumeException;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.Rijbewijs;
import be.vdab.voertuigen.*;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

public class Programma implements Serializable {

    private SortedSet voertuigen1 = new TreeSet();
    private SortedSet voertuigen2;
    private SortedSet voertuigen3;
    private SortedSet voertuigen4;

    public static void main(String[] args) {
        new Programma();
    }

    public Programma() {
        try {
            voertuigen1.add(new Personenwagen("Liverna", new Datum(13, 7, 1999), 1536, 6, Color.BLACK, new Mens("Joske", Rijbewijs.BE)));
            voertuigen1.add(new Personenwagen("Ionno", new Datum(9, 2, 1862), 1302, 4, Color.BLUE, new Mens("Charles", Rijbewijs.B)));
            voertuigen1.add(new Pickup("Ceverre", new Datum(7, 2, 2012), 3566, 4, Color.BLACK, new Volume(15, 15, 15, Maat.decimeter), new Mens("Joris", Rijbewijs.BE)));
            voertuigen1.add(new Pickup("Piculjr", new Datum(14, 10, 2001), 2999, 2, Color.BLACK, new Volume(200, 155, 200, Maat.centimeter), new Mens("Salvatore", Rijbewijs.B)));
            voertuigen1.add(new Vrachtwagen("Liverna", new Datum(10, 1, 1987), 6999, 3, new Volume(3, 3, 7, Maat.meter), 2000, 6, new Mens("Rogier", Rijbewijs.A, Rijbewijs.B, Rijbewijs.CE)));
            voertuigen1.add(new Vrachtwagen("BonAppart", new Datum(11, 3, 2018), 12000, 2, new Volume(4, 4, 10, Maat.meter), 3000, 8, new Mens("Napoleon", Rijbewijs.BE, Rijbewijs.CE)));
        } catch (DatumException ex) {
            System.err.println(ex.getMessage());
        } catch (VolumeException ex) {
            System.err.println(ex.getMessage());
        }

        printLijst(voertuigen1);

        voertuigen2 = new TreeSet<Voertuig>(Voertuig.getAankoopprijsComparator());
        voertuigen2.addAll(voertuigen1);

        printLijst(voertuigen2);

        voertuigen3 = new TreeSet<Voertuig>(Voertuig.getMerkComparator());
        voertuigen3.addAll(voertuigen1);

        printLijst(voertuigen3);

        try {
            schrijfWeg(voertuigen3);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        voertuigen4 = new TreeSet();
        try {
            voertuigen4.addAll(leesIn());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }

        printLijst(voertuigen4);

    }

    private void printLijst(SortedSet<Voertuig> lijst) {
        System.out.print("===================\nVoertijgenlijst:\n===================\n");
        for (Voertuig vt : lijst) {
            System.out.print(vt.toString() + "\n");
        }
        System.out.print("===================\n");
    }

    private void schrijfWeg(SortedSet lijst) throws FileNotFoundException, IOException {
        File file = new File("wagenpark.ser");
        FileOutputStream out = new FileOutputStream(file);
        ObjectOutputStream output = new ObjectOutputStream(out);
        output.writeObject(lijst);

    }

    private SortedSet<Voertuig> leesIn() throws FileNotFoundException, IOException, ClassNotFoundException {

        File file = new File("wagenpark.ser");
        FileInputStream in = new FileInputStream(file);
        ObjectInputStream input = new ObjectInputStream(in);
        return (SortedSet<Voertuig>) input.readObject();

    }

}
