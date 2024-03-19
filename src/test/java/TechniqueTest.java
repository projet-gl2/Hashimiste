import fr.hashimiste.core.jeu.Difficulte;
import fr.hashimiste.core.jeu.Technique;
import fr.hashimiste.impl.jeu.GrilleImpl;
import fr.hashimiste.impl.jeu.IleImpl;

import org.junit.jupiter.api.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour les techniques
 */
public class TechniqueTest {

    static GrilleImpl g;

    /***
     * Création de la grille utilisée avant le lancement des tests
     */
    @BeforeAll
    public static void initAll(){
        g = new GrilleImpl(new Dimension(6,6),Difficulte.MOYEN);
    }

    /**
     * Vidage des îles de la grille après chaque test.
     */
    @AfterEach
    public void vide(){
        g.viderGrille();
    }

    /**
     * Après chaque test, affichage du nom du test réussi.
     * @param testInfo Infos sur le test réussi.
     */
    @AfterEach
    public void afficheOK(TestInfo testInfo){
        System.out.println("OK : "+testInfo.getDisplayName());
    }

    /**
     * Test de la technique "Technique 1 bordure"
     */
    @Test
    public void TestTechniqueBordure1(){

        IleImpl i1 = new IleImpl(0,0,4,g);
        IleImpl i2 = new IleImpl(2,2,8,g);

        g.poserIle(i1);
        g.poserIle(i2);

        assertTrue(Technique.TECH_DEP_1.test(i1));
        assertFalse(Technique.TECH_DEP_1.test(i2));
    }

}
