package fr.hashimiste.maps;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PontTest {
    /**
     * Ce test vérifie la fonctionnalité de création d'un pont entre deux îles.
     */
    @Test
    public void shouldCreateBridgeWithValidParameters() {
        Ile ile1 = new Ile(null, 5, 10, 3);
        Ile ile2 = new Ile(null, 15, 20, 2);

        Pont pont = new Pont(ile1, ile2, 2);

        assertNotNull(pont);
        assertEquals(ile1, pont.getIle1());
        assertEquals(ile2, pont.getIle2());
        assertEquals(2, pont.getN());
    }

    /**
     * Ce test vérifie que l'exception IllegalArgumentException est bien levée lorsque l'on crée un pont avec un nombre de ponts négatif.
     */
    @Test
    public void shouldThrowExceptionWhenCreatingBridgeWithNegativeBridges() {
        Ile ile1 = new Ile(null, 5, 10, 3);
        Ile ile2 = new Ile(null, 15, 20, 2);

        assertThrows(IllegalArgumentException.class, () -> new Pont(ile1, ile2, -1));
    }

    /**
     * Ce test vérifie que l'exception IllegalArgumentException est bien levée lorsque l'on crée un pont avec une île nulle.
     */
    @Test
    public void shouldThrowExceptionWhenCreatingBridgeWithNullIslands() {
        Ile ile1 = new Ile(null, 5, 10, 3);

        assertThrows(IllegalArgumentException.class, () -> new Pont(ile1, null, 2));
        assertThrows(IllegalArgumentException.class, () -> new Pont(null, ile1, 2));
        assertThrows(IllegalArgumentException.class, () -> new Pont(null, null, 2));
    }

    /**
     * Ce test vérifie que l'exception IllegalArgumentException est bien levée lorsque l'on crée un pont avec les mêmes îles.
     */
    @Test
    public void shouldThrowExceptionWhenCreatingBridgeWithSameIslands() {
        Ile ile1 = new Ile(null, 5, 10, 3);

        assertThrows(IllegalArgumentException.class, () -> new Pont(ile1, ile1, 2));
    }

    /**
     * Ce test vérifie que la représentation textuelle d'un pont est correcte.
     */
    @Test
    public void shouldReturnCorrectToString() {
        Ile ile1 = new Ile(null, 5, 10, 3);
        Ile ile2 = new Ile(null, 15, 20, 2);
        Pont pont = new Pont(ile1, ile2, 2);

        assertEquals("Pont{ile1=" + ile1 + ", ile2=" + ile2 + ", n=2}", pont.toString());
    }

    /**
     * Ce test vérifie que le nombre de ponts est bien mis à jour.
     */
    @Test
    public void shouldUpdateNumberOfBridges() {
        Ile ile1 = new Ile(null, 5, 10, 3);
        Ile ile2 = new Ile(null, 15, 20, 2);
        Pont pont = new Pont(ile1, ile2, 2);

        pont.setN(3);

        assertEquals(3, pont.getN());
    }
}