package fr.hashimiste.maps;

public class CasePont extends Case {
    private final int n;
    CasePont(int x, int y, int n) {
        super(x, y);
        this.n = n;
    }

    public int getN() {
        return this.n;
    }
}
