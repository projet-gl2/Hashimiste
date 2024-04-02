import fr.hashimiste.core.jeu.Difficulte;
import fr.hashimiste.core.jeu.Ile;
import fr.hashimiste.impl.jeu.GrilleImpl;
import fr.hashimiste.impl.jeu.IleImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Tests unitaires pour la méthode aide de Grille
 */
public class AideTest {

    static GrilleImpl g;
    static ArrayList<IleImpl> listeIle;

    /***
     * Création de la grille utilisée avant le lancement des tests
     */
    @BeforeAll
    public static void initAll(){
        g = new GrilleImpl(new Dimension(6,6), Difficulte.MOYEN);
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
    public void TestAideBordure1() {

        listeIle.add(new IleImpl(0, 0, 4, g));
        listeIle.add(new IleImpl(0, 3, 6, g));
        listeIle.add(new IleImpl(2, 2, 8, g));
        listeIle.add(new IleImpl(2, 5, 6, g));
        listeIle.add(new IleImpl(2, 4, 6, g));

        for (IleImpl i : listeIle)
            g.poserIle(i);

        Ile ileAide = g.chercherIle().getIleU();

        assertSame(ileAide, listeIle.get(0));
    }

    @Test
    public void TestAideParite1(){

        listeIle.add(new IleImpl(0,0,1,g));
        listeIle.add(new IleImpl(0,2,4,g));
        listeIle.add(new IleImpl(0,4,1,g));
        listeIle.add(new IleImpl(1,3,1,g));
        listeIle.add(new IleImpl(1,4,8,g));
        listeIle.add(new IleImpl(1,5,1,g));
        listeIle.add(new IleImpl(2,2,4,g));
        listeIle.add(new IleImpl(2,4,1,g));
        listeIle.add(new IleImpl(3,3,1,g));
        listeIle.add(new IleImpl(4,1,1,g));
        listeIle.add(new IleImpl(4,3,6,g));
        listeIle.add(new IleImpl(4,5,1,g));

        for(IleImpl i : listeIle)
            g.poserIle(i);

        Ile ileAide = g.chercherIle().getIleU();

        assertSame(ileAide, listeIle.get(4));

        g.aide();
        g.aide();
        g.aide();
    }
}
