package zapytanie;

import zbiór.Nieskończony;
import zbiór.Skończony;
import zbiór.Zbiór;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class HeurystykaNaiwna extends Zapytanie
{
    public HeurystykaNaiwna(int rozmiar)
    {
        super.rozmiar = rozmiar + 1;
        pokrywanyZbiór = new boolean[rozmiar + 1];
    }

    public void wykonaj(ArrayList<Zbiór> zbiory)
    {
        TreeSet<Integer> wynik = new TreeSet<>();
        for(Zbiór zbiór : zbiory)
        {
            TreeSet<Integer> elementy = zbiór.getElementy();
            HashSet<Skończony> skończone = zbiór.getSkończoneCiągi();
            HashSet<Nieskończony> nieskończone = zbiór.getNieskończoneCiągi();
            boolean użyjZbioru = false;
            for(int i = 0; i < rozmiar; i++)
            {
                for(int element : elementy)
                    if(!pokrywanyZbiór[i] && i == element)
                    {
                        użyjZbioru = true;
                        break;
                    }
                if(użyjZbioru)
                    break;
            }
            if(!użyjZbioru)
            {
                for (Skończony skończony : skończone)
                {
                    int start = skończony.getStart();
                    int różnica = skończony.getRóżnica();
                    int koniec = skończony.getKoniec();
                    while (start < rozmiar && start <= koniec && !użyjZbioru)
                    {
                        if (!pokrywanyZbiór[start])
                        {
                            użyjZbioru = true;
                            break;
                        }
                        start += różnica;
                    }
                }
            }
            if(!użyjZbioru)
            {
                for (Nieskończony nieskończony : nieskończone)
                {
                    int start = nieskończony.getStart();
                    int różnica = nieskończony.getRóżnica();
                    while (start < rozmiar && !użyjZbioru)
                    {
                        if (!pokrywanyZbiór[start])
                        {
                            użyjZbioru = true;
                            break;
                        }
                        start += różnica;
                    }
                }
            }

            if(użyjZbioru)
                wynik.add(zbiór.getNumer());

            zaktualizujPokrywanyZbiór(zbiór, pokrywanyZbiór);
        }
        wypiszWynik(wynik);
    }
}
