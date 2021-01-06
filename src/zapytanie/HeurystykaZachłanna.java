package zapytanie;

import zbiór.Zbiór;

import java.util.*;

public class HeurystykaZachłanna extends Zapytanie
{
    public HeurystykaZachłanna(int rozmiar)
    {
        super.rozmiar = rozmiar + 1;
        pokrywanyZbiór = new boolean[rozmiar + 1];
    }

    public void wykonaj(ArrayList<Zbiór> zbiory)
    {
        int max = 0;
        int wybranyZbiór = 0;
        boolean[] maksymalnyZbiór = pokrywanyZbiór;
        TreeSet<Integer> output = new TreeSet<>();
        for (int i = 0; i < zbiory.size(); i++)
        {
            for (Zbiór zbiór : zbiory)
            {
                boolean[] tymczasowyZbiór = pokrywanyZbiór.clone();
                int licznik = zaktualizujPokrywanyZbiór(zbiór, tymczasowyZbiór);
                if (licznik > max)
                {
                    max = licznik;
                    wybranyZbiór = zbiór.getNumer();
                    maksymalnyZbiór = tymczasowyZbiór;
                }
            }
            max = 0;
            output.add(wybranyZbiór);
            pokrywanyZbiór = maksymalnyZbiór;
        }
        wypiszWynik(output);
    }
}