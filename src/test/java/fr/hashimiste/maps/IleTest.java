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
        Ile ile1 = new Ile(null, 5, 10, 3);
        Ile ile2 = new Ile(null, 15, 20, 2);

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
        Ile ile1 = new Ile(null, 5, 10, 3);
        Ile ile2 = new Ile(null, 15, 20, 2);

        assertThrows(IllegalArgumentException.class, () -> ile1.creerPont(ile2, -1));
    }

    /**
     * Ce test vérifie que le nombre de ponts est bien égal à celui passé en paramètre lors de la création du pont.
     */
    @Test
    public void shouldReturnCorrectNumberOfBridges() {
        Ile ile = new Ile(null, 5, 10, 3);

        assertEquals(3, ile.getNbPontPossible());
    }

    /**
     * Ce test vérifie que la représentation textuelle d'une île est correcte.
     */
    @Test
    public void shouldReturnCorrectToString() {
        Ile ile = new Ile(null, 5, 10, 3);

        assertEquals("Ile{x=5, y=10, nbPont=3}", ile.toString());
    }

    /**
     * Ce test vérifie que le hashcode d'une île est correct.
     */
    @Test
    public void shouldReturnCorrectHashCode() {
        Ile ile1 = new Ile(null, 5, 10, 3);
        Ile ile2 = new Ile(null, 5, 10, 3);

        assertEquals(ile1.hashCode(), ile2.hashCode());
    }

    /**
     * Ce test vérifie que deux îles sont égales si elles ont les mêmes coordonnées et le même nombre de ponts.
     */
    @Test
    public void shouldReturnFalseWhenComparingDifferentIslands() {
        Ile ile1 = new Ile(null, 5, 10, 3);
        Ile ile2 = new Ile(null, 15, 20, 2);

        assertNotEquals(ile1, ile2);
    }

    /**
     * Ce test vérifie que le nombre de ponts liés à une île est bien mis à jour.
     */
    @Test
    public void shouldUpdateNumberOfBridgesForIsland() {
        Ile ile1 = new Ile(null, 5, 10, 3);
        Ile ile2 = new Ile(null, 15, 20, 2);
        Pont pont = new Pont(ile1, ile2, 2);
        assertEquals(2, ile1.getNbPont());
        assertEquals(2, ile2.getNbPont());

        pont.setN(3);

        assertEquals(3, ile1.getNbPont());
        assertEquals(3, ile2.getNbPont());
    }

    /**
     * Ce test vérifie que le nombre d'ile liées à un pont est bien mis à jour.
     */
    @Test
    public void shouldUpdateNumberOfIslandsForBridge() {
        Ile ile1 = new Ile(null, 5, 10, 3);
        Ile ile2 = new Ile(null, 15, 20, 2);
        ile1.creerPont(ile2, 2);
        assertEquals(2, ile1.getNbPont());

        Ile ile3 = new Ile(null, 25, 30, 1);
        ile3.creerPont(ile1, 1);

        assertEquals(3, ile1.getNbPont());
    }

    /**
     * Ce test vérifie que le nombre d'ile liées à un pont est bien mis à jour et que le nombre est bien filtré.
     */
    @Test
    public void shouldUpdateNumberOfIslandsForBridgeAndFilter() {
        Ile ile1 = new Ile(null, 5, 10, 3);
        assertEquals(0, ile1.getNbPont(ile -> ile.getNbPontPossible() == 1));

        Ile ile2 = new Ile(null, 15, 20, 2);
        ile1.creerPont(ile2, 1);
        assertEquals(0, ile1.getNbPont(ile -> ile.getNbPontPossible() == 1));

        Ile ile3 = new Ile(null, 25, 30, 1);
        ile1.creerPont(ile3, 1);
        assertEquals(1, ile1.getNbPont(ile -> ile.getNbPontPossible() == 1));
    }

    /**
     * Ce test vérifie la complétion d'une île.
     */
    @Test
    public void shouldCompleteIsland() {
        Ile ile1 = new Ile(null, 5, 10, 3);
        Ile ile2 = new Ile(null, 15, 20, 1);
        ile1.creerPont(ile2, 1);
        assertFalse(ile1.isComplete());

        Ile ile3 = new Ile(null, 25, 30, 2);
        Pont pont = ile3.creerPont(ile1, 1);
        assertFalse(ile1.isComplete());

        pont.setN(2);
        assertTrue(ile1.isComplete());
    }
}