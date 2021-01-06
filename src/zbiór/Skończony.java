package zbiór;

public class Skończony extends Ciąg
{
    private final int koniec;

    public Skończony(int start, int różnica, int koniec)
    {
        this.start = start;
        this.różnica = różnica;
        this.koniec = koniec;
    }

    public int getKoniec()
    {
        return koniec;
    }
}
