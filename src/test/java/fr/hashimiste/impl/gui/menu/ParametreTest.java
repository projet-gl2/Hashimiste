package fr.hashimiste.impl.gui.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.util.*;

class ParametreTest extends TestMenu{

    private Parametre parametre;

    /**
     * Renvoie le conteneur à tester
     * @return le conteneur à tester
     */
    @Override
    protected Container getTestContainer() {
        return parametre;
    }

    /**
     * Teste l'initialisation du menu
     * @throws IOException si une erreur d'entrée/sortie survient
     */
    @BeforeEach
    void testMenuInitialisation() throws IOException {
        parametre = (Parametre) testMenuInitialisations(parametre, "Parametre");

        System.out.println("L'initialisation du menu réussi");
    }

    /**
     * Teste le menu Paramètre
     */
    @Test
    void testMenuParametre(){
        testerMenu(parametre, "Hashimiste", new Dimension(800, 600));

        testThemeMenu(parametre, "default");
        testThemeMenu(parametre, "candy");

        System.out.println("Le test du changement de thème réussi");
    }

    /**
     * Teste tous les boutons du menu
     */
    @Test
    void testTousLesBoutons() {
        // Création d'une carte pour mapper le nom du bouton à son état attendu (true pour actif, false pour inactif)
        Map<String, Boolean> boutonsEtEtats = new HashMap<>();
        boutonsEtEtats.put("Sauvegarder", true);
        boutonsEtEtats.put("Menu", true);

        // Itération sur chaque entrée de la carte pour vérifier chaque bouton
        boutonsEtEtats.forEach(this::verifierEtatBouton);
    }


}