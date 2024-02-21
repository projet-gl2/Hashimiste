package fr.hashimiste;

public abstract class Case {
    private final int x;
    private final int y;
    Case(int x, int y) {
        this.x = x;
        this.y = y;
    }
    int getX() {
        return this.x;
    }
    int getY() {
        return this.y;
    }
}
