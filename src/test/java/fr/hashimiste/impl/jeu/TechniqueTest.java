package fr.hashimiste.impl.jeu;

import fr.hashimiste.core.jeu.Difficulte;
import fr.hashimiste.core.jeu.Direction;
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
        g = new GrilleImpl(new Dimension(8,8),Difficulte.MOYEN,false);
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
    public void testTechniqueBordure1(){

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
    public void testTechniqueBordure2(){

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
    public void testTechniqueParite1(){

        listeIle.add(new IleImpl(0,0,1,g));
        listeIle.add(new IleImpl(0,2,4,g));

        assertFalse(Technique.TECH_DEP_2.test(listeIle.get(1)));
        assertTrue(Technique.TECH_DEP_2.test(listeIle.get(4)));
        assertTrue(Technique.TECH_DEP_2.test(listeIle.get(6)));
        assertTrue(Technique.TECH_DEP_2.test(listeIle.get(10)));
    }

    /**
     * Test de la technique "Technique Imparité"
     */
    @Test
    public void testTechniqueImparite1(){

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
     * Test de la technique "Technique Imparité" avec les ponts
     */
    @Test
    public void testTechniqueImparite2(){

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

        g.poserPont(listeIle.get(4), listeIle.get(7), 1);
        g.poserPont(listeIle.get(6), listeIle.get(7), 1);
        g.poserPont(listeIle.get(9), listeIle.get(7), 1);

        assertFalse(Technique.TECH_BAS_1.test(listeIle.get(0)));
        assertFalse(Technique.TECH_BAS_1.test(listeIle.get(2)));
        assertTrue(Technique.TECH_BAS_1.test(listeIle.get(3)));
        assertTrue(Technique.TECH_BAS_1.test(listeIle.get(4)));
        assertFalse(Technique.TECH_BAS_1.test(listeIle.get(6)));
        assertInstanceOf(PontImpl.class, listeIle.get(7).getVoisinCase(Direction.NORD));
        assertEquals(3,listeIle.get(7).getNbPont());
        assertFalse(Technique.TECH_BAS_1.test(listeIle.get(7)));

    }

    /**
     * Test de la technique "Technique Imparité + Unité"
     */
    @Test
    public void testTechniqueImpariteUnite1(){

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
    public void testTechniqueUniteUnDeux1(){

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

    /**
     * Test de la technique "Technique Unité (valeur 4 et 5)"
     */
    @Test
    public void testTechniqueUniteQuatreCinq1(){

        listeIle.add(new IleImpl(0,0,1,g));
        listeIle.add(new IleImpl(0,2,4,g));
        listeIle.add(new IleImpl(0,4,1,g));
        listeIle.add(new IleImpl(1,5,3,g));
        listeIle.add(new IleImpl(1,6,2,g));
        listeIle.add(new IleImpl(2,2,5,g));
        listeIle.add(new IleImpl(2,5,4,g));
        listeIle.add(new IleImpl(2,7,1,g));
        listeIle.add(new IleImpl(4,0,1,g));
        listeIle.add(new IleImpl(4,2,5,g));
        listeIle.add(new IleImpl(4,4,1,g));
        listeIle.add(new IleImpl(6,2,1,g));

        for(IleImpl i : listeIle)
            g.poserIle(i);

        assertTrue(Technique.TECH_BAS_4.test(listeIle.get(1)));
        assertTrue(Technique.TECH_BAS_4.test(listeIle.get(9)));
        assertFalse(Technique.TECH_BAS_4.test(listeIle.get(3)));
        assertFalse(Technique.TECH_BAS_4.test(listeIle.get(5)));
        assertFalse(Technique.TECH_BAS_4.test(listeIle.get(6)));
    }

    /**
     * Test de la technique "Technique Unité (valeur 6)" TODO Test avec les ponts
     */
    @Test
    public void testTechniqueUniteSix1(){

        listeIle.add(new IleImpl(0,0,1,g));
        listeIle.add(new IleImpl(0,2,1,g));
        listeIle.add(new IleImpl(2,0,3,g));
        listeIle.add(new IleImpl(2,2,6,g));
        listeIle.add(new IleImpl(2,4,4,g));
        listeIle.add(new IleImpl(4,0,2,g));
        listeIle.add(new IleImpl(4,2,6,g));
        listeIle.add(new IleImpl(4,4,4,g));
        listeIle.add(new IleImpl(6,0,1,g));
        listeIle.add(new IleImpl(6,2,2,g));

        for(IleImpl i : listeIle)
            g.poserIle(i);

        assertTrue(Technique.TECH_BAS_5.test(listeIle.get(3)));
        assertFalse(Technique.TECH_BAS_5.test(listeIle.get(4)));
        assertFalse(Technique.TECH_BAS_5.test(listeIle.get(5)));
        assertFalse(Technique.TECH_BAS_5.test(listeIle.get(6)));
    }

    /**
     * Test des techniques "Technique Isolation (valeur 1)" et  "Technique Isolation (valeur 2)"
     */
    @Test
    public void testTechniqueIsolation1(){

        listeIle.add(new IleImpl(0,0,1,g));
        listeIle.add(new IleImpl(0,2,1,g));
        listeIle.add(new IleImpl(0,4,1,g));
        listeIle.add(new IleImpl(0,6,2,g));
        listeIle.add(new IleImpl(2,0,3,g));
        listeIle.add(new IleImpl(2,2,6,g));
        listeIle.add(new IleImpl(2,4,6,g));
        listeIle.add(new IleImpl(2,6,3,g));
        listeIle.add(new IleImpl(4,0,2,g));
        listeIle.add(new IleImpl(4,2,6,g));
        listeIle.add(new IleImpl(4,4,4,g));
        listeIle.add(new IleImpl(6,0,1,g));
        listeIle.add(new IleImpl(6,2,2,g));
        listeIle.add(new IleImpl(6,4,2,g));

        for(IleImpl i : listeIle)
            g.poserIle(i);

        assertTrue(Technique.TECH_ISO_1.test(listeIle.get(0)));
        assertTrue(Technique.TECH_ISO_1.test(listeIle.get(1)));
        assertFalse(Technique.TECH_ISO_1.test(listeIle.get(2)));
        assertFalse(Technique.TECH_ISO_1.test(listeIle.get(11)));
        assertTrue(Technique.TECH_ISO_2.test(listeIle.get(13)));
        assertFalse(Technique.TECH_ISO_2.test(listeIle.get(8)));
        assertFalse(Technique.TECH_ISO_2.test(listeIle.get(12)));
    }

}
