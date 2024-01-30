package fr.hashimiste.maps;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IleTest {
    /**
     * Ce test vérifie la fonctionnalité de création d'un pont entre deux îles.
     * Il crée deux îles et un pont entre elles, puis vérifie si le pont a été correctement créé.
     */
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

    /**
     * Ce test vérifie que l'exception IllegalArgumentException est bien levée lorsque l'on crée un pont avec un nombre de ponts négatif.
     */
    @Test
    public void shouldThrowExceptionWhenCreatingBridgeWithNegativeNumberOfBridges() {
        Ile ile1 = new Ile(5, 10, 3);
        Ile ile2 = new Ile(15, 20, 2);

        assertThrows(IllegalArgumentException.class, () -> ile1.creerPont(ile2, -1));
    }

    /**
     * Ce test vérifie que le nombre de ponts est bien égal à celui passé en paramètre lors de la création du pont.
     */
    @Test
    public void shouldReturnCorrectNumberOfBridges() {
        Ile ile = new Ile(5, 10, 3);

        assertEquals(3, ile.getNbPont());
    }

    /**
     * Ce test vérifie que la représentation textuelle d'une île est correcte.
     */
    @Test
    public void shouldReturnCorrectToString() {
        Ile ile = new Ile(5, 10, 3);

        assertEquals("Ile{x=5, y=10, nbPont=3}", ile.toString());
    }

    /**
     * Ce test vérifie que le hashcode d'une île est correct.
     */
    @Test
    public void shouldReturnCorrectHashCode() {
        Ile ile1 = new Ile(5, 10, 3);
        Ile ile2 = new Ile(5, 10, 3);

        assertEquals(ile1.hashCode(), ile2.hashCode());
    }

    /**
     * Ce test vérifie que deux îles sont égales si elles ont les mêmes coordonnées et le même nombre de ponts.
     */
    @Test
    public void shouldReturnFalseWhenComparingDifferentIslands() {
        Ile ile1 = new Ile(5, 10, 3);
        Ile ile2 = new Ile(15, 20, 2);

        assertNotEquals(ile1, ile2);
    }
}