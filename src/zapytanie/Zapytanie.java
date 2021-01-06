package zapytanie;

import zbiór.Nieskończony;
import zbiór.Skończony;
import zbiór.Zbiór;

import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

abstract public class Zapytanie
{
    protected int rozmiar;
    protected boolean[] pokrywanyZbiór;

    protected boolean zbiórPokryty(boolean[] pokrywanyZbiór)
    {
        for(int i = 1; i < pokrywanyZbiór.length; i++)
            if(!pokrywanyZbiór[i])
                return false;
        return true;
    }

    protected void wypiszWynik(TreeSet<Integer> wynik)
    {
        if(zbiórPokryty(pokrywanyZbiór))
        {
            Iterator<Integer> i = wynik.iterator();
            while (i.hasNext())
            {
                System.out.print(i.next());
                if(i.hasNext())
                {
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }
        else
        {
            System.out.println("0");
        }
    }

    protected int zaktualizujPokrywanyZbiór(Zbiór zbiór, boolean[] tymczasowyZbiór)
    {
        int licznik = 0;
        TreeSet<Integer> elementy = zbiór.getElementy();
        HashSet<Skończony> skończone = zbiór.getSkończoneCiągi();
        HashSet<Nieskończony> nieskończone = zbiór.getNieskończoneCiągi();
        for (int j = 0; j < rozmiar; j++)
        {
            if (!tymczasowyZbiór[j] && elementy.contains(j))
            {
                tymczasowyZbiór[j] = true;
                licznik++;
            }
        }
        for (Skończony skończony : skończone)
        {
            int start = skończony.getStart();
            int różnica = skończony.getRóżnica();
            int koniec = skończony.getKoniec();
            while (start < rozmiar && start <= koniec)
            {
                if (!tymczasowyZbiór[start])
                {
                    tymczasowyZbiór[start] = true;
                    licznik++;
                }
                start += różnica;
            }
        }
        for (Nieskończony nieskończony : nieskończone)
        {
            int start = nieskończony.getStart();
            int różnica = nieskończony.getRóżnica();
            while (start < rozmiar)
            {
                if (!tymczasowyZbiór[start])
                {
                    tymczasowyZbiór[start] = true;
                    licznik++;
                }
                start += różnica;
            }
        }
        return licznik;
    }

}
