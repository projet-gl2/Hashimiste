package fr.hashimiste.maps;

public class CaseIle extends Case {
    private final int n;
    CaseIle(int x, int y, int n) {
        super(x, y);
        this.n = n;
    }
    public int getN() {
        return this.n;
    }
}
