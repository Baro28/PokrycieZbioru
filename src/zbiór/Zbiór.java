package zbiór;

import java.util.HashSet;
import java.util.TreeSet;

public class Zbiór
{
    private final TreeSet<Integer> elementy;
    private final HashSet<Skończony> skończoneCiągi;
    private final HashSet<Nieskończony> nieskończoneCiągi;
    private final int numer;


    public Zbiór(int numer)
    {
        elementy = new TreeSet<>();
        skończoneCiągi = new HashSet<>();
        nieskończoneCiągi = new HashSet<>();
        this.numer = numer;
    }

    public void dodajElement(int element)
    {
        elementy.add(element);
    }

    public void dodajSkończony(int start, int różnica, int koniec)
    {
        Skończony nowySkończony = new Skończony(start, różnica, koniec);
        skończoneCiągi.add(nowySkończony);
    }

    public void dodajNieskończony(int start, int różnica)
    {
        Nieskończony nowyNieskończony = new Nieskończony(start, różnica);
        nieskończoneCiągi.add(nowyNieskończony);
    }

    public TreeSet<Integer> getElementy()
    {
        return elementy;
    }

    public HashSet<Skończony> getSkończoneCiągi()
    {
        return skończoneCiągi;
    }

    public HashSet<Nieskończony> getNieskończoneCiągi()
    {
        return nieskończoneCiągi;
    }

    public int getNumer()
    {
        return numer;
    }

    @Override
    public String toString()
    {
        return "Zbiór{" +
                "elementy=" + elementy +
                ", skończone=" + skończoneCiągi +
                ", nieskończone=" + nieskończoneCiągi +
                ", numer=" + numer +
                '}';
    }
}
