package fr.hashimiste.maps;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IleTest {
    @Test
    public void shouldCreateBridgeWithMultipleBridges() {
        Ile ile1 = new Ile(5, 10, 3);
        Ile ile2 = new Ile(15, 20, 2);

        Pont pont = ile1.creerPont(ile2, 2);

        assertNotNull(pont);
        assertEquals(ile1, pont.getIle1());
        assertEquals(ile2, pont.getIle2());
        assertEquals(2, pont.getN());
    }

    @Test
    public void shouldThrowExceptionWhenCreatingBridgeWithNegativeNumberOfBridges() {
        Ile ile1 = new Ile(5, 10, 3);
        Ile ile2 = new Ile(15, 20, 2);

        assertThrows(IllegalArgumentException.class, () -> ile1.creerPont(ile2, -1));
    }

    @Test
    public void shouldReturnCorrectNumberOfBridges() {
        Ile ile = new Ile(5, 10, 3);

        assertEquals(3, ile.getNbPont());
    }

    @Test
    public void shouldReturnCorrectToString() {
        Ile ile = new Ile(5, 10, 3);

        assertEquals("Ile{x=5, y=10, nbPont=3}", ile.toString());
    }

    @Test
    public void shouldReturnCorrectHashCode() {
        Ile ile1 = new Ile(5, 10, 3);
        Ile ile2 = new Ile(5, 10, 3);

        assertEquals(ile1.hashCode(), ile2.hashCode());
    }

    @Test
    public void shouldReturnFalseWhenComparingDifferentIslands() {
        Ile ile1 = new Ile(5, 10, 3);
        Ile ile2 = new Ile(15, 20, 2);

        assertNotEquals(ile1, ile2);
    }
}