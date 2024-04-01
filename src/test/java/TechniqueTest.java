import fr.hashimiste.core.jeu.Difficulte;
import fr.hashimiste.core.jeu.Technique;
import fr.hashimiste.impl.jeu.GrilleImpl;
import fr.hashimiste.impl.jeu.IleImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour les techniques
 */
public class TechniqueTest {

    static GrilleImpl g;
    static ArrayList<IleImpl> listeIle;

    /***
     * Création de la grille utilisée avant le lancement des tests
     */
    @BeforeAll
    public static void initAll(){
        g = new GrilleImpl(new Dimension(8,8),Difficulte.MOYEN);
        listeIle = new ArrayList<>();
    }

    /**
     * Vidage des îles de la grille après chaque test.
     */
    @AfterEach
    public void vide(){
        g.viderGrille();
        while(!listeIle.isEmpty()) listeIle.remove(0);
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
     * Test de la technique "Technique Bordure" avec des nombres paires
     */
    @Test
    public void TestTechniqueBordure1(){

        listeIle.add(new IleImpl(0,0,4,g));
        listeIle.add(new IleImpl(0,3,6,g));
        listeIle.add(new IleImpl(2,2,8,g));
        listeIle.add(new IleImpl(2,5,6,g));
        listeIle.add(new IleImpl(2,4,6,g));

        for(IleImpl i : listeIle)
            g.poserIle(i);

        assertTrue(Technique.TECH_DEP_1.test(listeIle.get(0)));
        assertTrue(Technique.TECH_DEP_1.test(listeIle.get(1)));
        assertFalse(Technique.TECH_DEP_1.test(listeIle.get(2)));
        assertTrue(Technique.TECH_DEP_1.test(listeIle.get(3)));
        assertFalse(Technique.TECH_DEP_1.test(listeIle.get(4)));
    }

    /**
     * Test de la technique "Technique Bordure" avec des nombres impaires
     */
    @Test
    public void TestTechniqueBordure2(){

        listeIle.add(new IleImpl(0,0,3,g));
        listeIle.add(new IleImpl(0,3,5,g));
        listeIle.add(new IleImpl(2,2,7,g));
        listeIle.add(new IleImpl(2,5,4,g));
        listeIle.add(new IleImpl(2,4,3,g));

        for(IleImpl i : listeIle)
            g.poserIle(i);

        assertFalse(Technique.TECH_DEP_1.test(listeIle.get(0)));
        assertFalse(Technique.TECH_DEP_1.test(listeIle.get(1)));
        assertFalse(Technique.TECH_DEP_1.test(listeIle.get(2)));
        assertFalse(Technique.TECH_DEP_1.test(listeIle.get(3)));
        assertFalse(Technique.TECH_DEP_1.test(listeIle.get(4)));
    }

    /**
     * Test de la technique "Technique Parité"
     */
    @Test
    public void TestTechniqueParite1(){

        listeIle.add(new IleImpl(0,0,1,g));
        listeIle.add(new IleImpl(0,2,4,g));

        assertFalse(Technique.TECH_DEP_2.test(listeIle.get(1)));
        assertTrue(Technique.TECH_DEP_2.test(listeIle.get(4)));
        assertTrue(Technique.TECH_DEP_2.test(listeIle.get(6)));
        assertTrue(Technique.TECH_DEP_2.test(listeIle.get(10)));
    }

    /**
     * Test de la technique "Technique Imparité" TODO (manque le test de vérification de ponts)
     */
    @Test
    public void TestTechniqueImparite1(){

        listeIle.add(new IleImpl(0,0,2,g));
        listeIle.add(new IleImpl(0,2,3,g));
        listeIle.add(new IleImpl(2,0,3,g));
        listeIle.add(new IleImpl(2,2,7,g));
        listeIle.add(new IleImpl(2,4,3,g));
        listeIle.add(new IleImpl(4,0,1,g));
        listeIle.add(new IleImpl(4,2,5,g));
        listeIle.add(new IleImpl(4,4,5,g));
        listeIle.add(new IleImpl(5,2,1,g));
        listeIle.add(new IleImpl(6,4,2,g));

        for(IleImpl i : listeIle)
            g.poserIle(i);

        assertFalse(Technique.TECH_BAS_1.test(listeIle.get(0)));
        assertFalse(Technique.TECH_BAS_1.test(listeIle.get(2)));
        assertTrue(Technique.TECH_BAS_1.test(listeIle.get(3)));
        assertTrue(Technique.TECH_BAS_1.test(listeIle.get(4)));
        assertFalse(Technique.TECH_BAS_1.test(listeIle.get(6)));
        assertTrue(Technique.TECH_BAS_1.test(listeIle.get(7)));

    }

    /**
     * Test de la technique "Technique Imparité + Unité"
     */
    @Test
    public void TestTechniqueImpariteUnite1(){

        listeIle.add(new IleImpl(0,0,2,g));
        listeIle.add(new IleImpl(0,2,3,g));
        listeIle.add(new IleImpl(2,0,3,g));
        listeIle.add(new IleImpl(2,2,5,g));
        listeIle.add(new IleImpl(2,4,1,g));
        listeIle.add(new IleImpl(4,0,1,g));
        listeIle.add(new IleImpl(4,2,5,g));
        listeIle.add(new IleImpl(4,4,5,g));
        listeIle.add(new IleImpl(5,2,1,g));
        listeIle.add(new IleImpl(6,4,3,g));
        listeIle.add(new IleImpl(6,6,1,g));

        for(IleImpl i : listeIle)
            g.poserIle(i);

        assertFalse(Technique.TECH_BAS_2.test(listeIle.get(0)));
        assertFalse(Technique.TECH_BAS_2.test(listeIle.get(2)));
        assertFalse(Technique.TECH_BAS_2.test(listeIle.get(3)));
        assertFalse(Technique.TECH_BAS_2.test(listeIle.get(4)));
        assertFalse(Technique.TECH_BAS_2.test(listeIle.get(6)));
        IleImpl ileTest = listeIle.get(7);
        assertEquals(5,ileTest.getN());
        assertEquals(3,ileTest.getNbVoisin());
        assertEquals(1,ileTest.getNbVoisinFiltre(i -> i.getN() == 1));
        assertTrue(Technique.TECH_BAS_2.test(listeIle.get(7)));
        assertTrue(Technique.TECH_BAS_2.test(listeIle.get(9)));

    }

    /**
     * Test de la technique "Technique Unité (valeur 1 et 2)"
     */
    @Test
    public void TestTechniqueUniteUnDeux1(){

        listeIle.add(new IleImpl(0,0,2,g));
        listeIle.add(new IleImpl(0,2,3,g));
        listeIle.add(new IleImpl(1,6,1,g));
        listeIle.add(new IleImpl(2,2,3,g));
        listeIle.add(new IleImpl(2,4,3,g));
        listeIle.add(new IleImpl(2,6,2,g));
        listeIle.add(new IleImpl(4,1,3,g));
        listeIle.add(new IleImpl(4,2,3,g));
        listeIle.add(new IleImpl(4,4,4,g));
        listeIle.add(new IleImpl(6,1,2,g));
        listeIle.add(new IleImpl(6,4,3,g));
        listeIle.add(new IleImpl(6,6,1,g));

        for(IleImpl i : listeIle)
            g.poserIle(i);

        assertTrue(Technique.TECH_BAS_3.test(listeIle.get(0)));
        assertTrue(Technique.TECH_BAS_3.test(listeIle.get(2)));
        assertFalse(Technique.TECH_BAS_3.test(listeIle.get(3)));
        assertFalse(Technique.TECH_BAS_3.test(listeIle.get(4)));
        assertFalse(Technique.TECH_BAS_3.test(listeIle.get(6)));
        assertFalse(Technique.TECH_BAS_3.test(listeIle.get(9)));
        assertFalse(Technique.TECH_BAS_3.test(listeIle.get(11)));

    }

}
