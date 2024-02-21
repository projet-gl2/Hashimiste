package fr.hashimiste;

public class CaseIle extends Case {
    private final int n;
    public CaseIle(int x, int y, int n) {
        super(x, y);
        this.n = n;
    }
    public int getN() {
        return this.n;
    }
}
