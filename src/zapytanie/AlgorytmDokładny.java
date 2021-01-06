package zapytanie;

import zbiór.Zbiór;

import java.util.*;

public class AlgorytmDokładny extends Zapytanie
{
    private final ArrayList<Zbiór> podzbiór;
    private boolean pierwszeRozwiązanie;
    private final TreeSet<Integer> najlepszeRozwiązanie;

    public AlgorytmDokładny(int rozmiar)
    {
        super.rozmiar = rozmiar + 1;
        pokrywanyZbiór = new boolean[rozmiar + 1];
        podzbiór = new ArrayList<>();
        pierwszeRozwiązanie = true;
        najlepszeRozwiązanie = new TreeSet<>();
    }

    // Porównuje dwa rozwiązania i zwraca to które jest leksykograficznie mniejsze
    private boolean porównajRozwiązania(TreeSet<Integer> a, TreeSet<Integer> b)
    {
        if(a.size() < b.size())
            return true;
        if(b.size() < a.size())
            return false;
        // Rozwiązania mają tą samą długość więc musimy sprawdzać kolejne elementy od początku
        Iterator<Integer> aiter = a.iterator();
        Iterator<Integer> biter = b.iterator();
        while(aiter.hasNext() && biter.hasNext())
        {
            int aInt = aiter.next();
            int bInt = biter.next();
            if (aInt < bInt)
                return true;
            if (bInt < aInt)
                return false;
        }
        return false;
    }

    // Zwraca TreeSet z numerami zbiorów w podzbiorze
    private TreeSet<Integer> podajZbiory()
    {
        TreeSet<Integer> wynik = new TreeSet<>();
        for(Zbiór zbiór : podzbiór)
            wynik.add(zbiór.getNumer());
        return wynik;
    }

    //Czyści obecne najlepsze rozwiązanie i ustawia nowe
    private void noweNajlepszeRozwiązanie(TreeSet<Integer> currentAnswer)
    {
        najlepszeRozwiązanie.clear();
        najlepszeRozwiązanie.addAll(currentAnswer);
    }

    //Tworzy PokrywanyZbiór na podstawie zbiorów znajdujących się obecnie w podzbiorze
    private boolean[] stwórzPokrywanyZbiór()
    {
        boolean[] pokrywanyZbiór = new boolean[rozmiar];
        for(Zbiór zbiór : podzbiór)
            zaktualizujPokrywanyZbiór(zbiór, pokrywanyZbiór);
        return pokrywanyZbiór;
    }

    //Sprawdza czy obecne rozwiązanie jest najlepsze i ustawia je jako najlepsze jeśli tak
    private void przetwórzPodzbiór()
    {
        boolean[] pokrywanyZbiór = stwórzPokrywanyZbiór();
        if(zbiórPokryty(pokrywanyZbiór))
        {
            TreeSet<Integer> rozwiązanie = podajZbiory();
            if(pierwszeRozwiązanie)
            {
                pierwszeRozwiązanie = false;
                noweNajlepszeRozwiązanie(rozwiązanie);
            }
            else
            {
                if(!porównajRozwiązania(najlepszeRozwiązanie, rozwiązanie))
                {
                    noweNajlepszeRozwiązanie(rozwiązanie);
                }
            }
        }
    }

    // Rekurencyjnie przechodzi przez wszystkie możliwe podzbiory obecnej rodziny zbiorów
    public void przejdźPodzbiory(ArrayList<Zbiór> zbiory, int i)
    {
        if(i == zbiory.size()) // Po utworzeniu całego podzbioru przetwarza go
        {
            przetwórzPodzbiór();
        }
        else
        {
            przejdźPodzbiory(zbiory, i + 1);
            podzbiór.add(zbiory.get(i));
            przejdźPodzbiory(zbiory, i + 1);
            podzbiór.remove(podzbiór.size() - 1);
        }
    }

    //Rozpoczyna rekurencję która przechodzi wszystkie możliwe podzbiory i wypisuje najlepsze rozwiązanie
    //jeśli takie istnieje
    public void wykonaj(ArrayList<Zbiór> zbiory)
    {
        przejdźPodzbiory(zbiory, 0);
        if(!najlepszeRozwiązanie.isEmpty())
        {
            Iterator<Integer> i = najlepszeRozwiązanie.iterator();
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
            System.out.println("0");
    }
}
