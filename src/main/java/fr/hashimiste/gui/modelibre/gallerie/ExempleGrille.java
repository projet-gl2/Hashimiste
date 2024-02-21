package fr.hashimiste.gui.modelibre.gallerie;

import fr.hashimiste.Difficulte;
import fr.hashimiste.maps.Grille;
import fr.hashimiste.maps.Ile;

import java.awt.*;
import java.util.Arrays;

/**
 * Classe de temporaire pour stocker des exemples de grille
 * @author elie
 */
public class ExempleGrille {

    // Grille facile en 7x7
    public static Grille grille_facile = new Grille(new Dimension(7,7), Difficulte.FACILE, Arrays.asList(
            new Ile(null, 0,0,3),
            new Ile(null, 3,0,4),
            new Ile(null, 6,0, 3),


            new Ile(null, 1,1, 2),

            new Ile(null,3,2,2),
            new Ile(null, 5,2,1),

            new Ile(null,1,3,5),
            new Ile(null, 4,3,4),

            new Ile(null, 0,5,2),

            new Ile(null,1,6,3),
            new Ile(null, 4,6,5),
            new Ile(null, 6,6,2)

    ));


    // Grille Moyen en 10x10
    public static Grille grille_moyen = new Grille(new Dimension(10,10), Difficulte.MOYEN, Arrays.asList(
            new Ile(null, 0,0,1),
            new Ile(null, 9,0,2),

            new Ile(null, 0, 9, 3),
            new Ile(null, 9,9, 4)

    ));
}
