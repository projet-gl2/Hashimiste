package fr.hashimiste.impl.jeu;

import fr.hashimiste.core.jeu.Difficulte;
import fr.hashimiste.core.jeu.Ile;
import fr.hashimiste.impl.jeu.GrilleImpl;
import fr.hashimiste.impl.jeu.IleImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import fr.hashimiste.impl.POnts;

/**
 * Tests unitaires pour la méthodE HistoriqueVersPonts
 */

public class HistoriqueTest{
    static GrilleImpl g;
    static ArrayList<IleImpl> listeIle;

    /***
     * Création de la grille utilisée avant le lancement des tests
     */
    @BeforeAll
    public static void initAll(){
        g = new GrilleImpl(new Dimension(6,6), Difficulte.MOYEN,false);
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
    @Test
    public void testHisorique(){
        listeIle.add(new IleImpl(0, 0, 4, g));
        listeIle.add(new IleImpl(0, 3, 6, g));
        listeIle.add(new IleImpl(0, 5, 8, g));
        listeIle.add(new IleImpl(2, 5, 6, g));
        listeIle.add(new IleImpl(2, 4, 6, g));
        for (IleImpl i : listeIle)
            g.poserIle(i);

        Historique h=Historique(g,listeIle.get(0), listeIle.get(1), Action.UN_PONT);
        h.creerSuivant(listeIle.get(1),listeIle.get(2),Action.UN_PONT);
        h.creerSuivant(listeIle.get(2),listeIle.get(3),Action.UN_PONT);
        h.creerSuivant(listeIle.get(3),listeIle.get(4),Action.UN_PONT);
        List<Ponts> res=new ArrayList<POnts>();
        res.add(new Ponts(listeIle.get(0), listeIle.get(1)));
        res.add(new Ponts(listeIle.get(1),listeIle.get(2)));
        res.add(new Ponts(listeIle.get(2),listeIle.get(3)));
        res.add(new Ponts(listeIle.get(3),listeIle.get(4)));

    }
     
} 



