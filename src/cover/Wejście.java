package cover;

import zapytanie.AlgorytmDokładny;
import zapytanie.HeurystykaNaiwna;
import zapytanie.HeurystykaZachłanna;
import zbiór.Zbiór;

import java.util.*;

public class Wejście
{

    //Wczytuje input i zapisuje go w ArrayLiście którego na koniec zwraca
    public static ArrayList<Integer> wejście()
    {
        ArrayList <Integer> wejście = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextInt())
        {
            wejście.add(scanner.nextInt());
        }

        return wejście;
    }

    //
    public static void parseInput(ArrayList<Integer> input)
    {
        ListIterator<Integer> i = input.listIterator();
        ArrayList<Zbiór> zbiory = new ArrayList<>();
        int licznik = 0;

        while(i.hasNext())
        {
            int a = i.next();
            if(a < 0)
            {
                switch(i.next())
                {
                    case 1:
                        AlgorytmDokładny algo = new AlgorytmDokładny((-1) * a);
                        algo.wykonaj(zbiory);
                        break;
                    case 2:
                        HeurystykaZachłanna Heura = new HeurystykaZachłanna((-1) * a);
                        Heura.wykonaj(zbiory);
                        break;
                    case 3:
                        HeurystykaNaiwna heura = new HeurystykaNaiwna((-1) * a);
                        heura.wykonaj(zbiory);
                        break;
                    default:
                        System.out.println("TODO");
                }
            }
            else
            {
                licznik++;
                Zbiór zbiór = new Zbiór(licznik);
                if(a == 0)
                {
                    zbiory.add(zbiór);
                    continue;
                }
                i.previous();
                while(true)
                {
                    a = i.next();
                    int b = i.next();
                    if(b < 0)
                    {
                        int c = i.next();
                        if(c < 0)
                        {
                            zbiór.dodajSkończony(a,(-1) * b,(-1) * c);
                        }
                        else
                        {
                            zbiór.dodajNieskończony(a, (-1) * b);
                        }
                        if(c == 0)
                            break;
                    }
                    else
                    {
                        zbiór.dodajElement(a);
                        if(b == 0)
                            break;
                    }
                    i.previous();
                }
                zbiory.add(zbiór);
            }
        }
    }
}
