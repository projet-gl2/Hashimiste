package fr.hashimiste.impl.gui.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.util.*;

class AventureTest extends fr.hashimiste.impl.gui.menu.Test{
    private Aventure aventure;

    /**
     * Renvoie le conteneur à tester
     * @return le conteneur à tester
     */
    @Override
    protected Container getTestContainer() {
        return aventure;
    }

    /**
     * Teste l'initialisation du menu
     * @throws IOException si une erreur d'entrée/sortie survient
     */
    @BeforeEach
    void testMenuInitialisation() throws IOException {
        aventure = (Aventure) testMenuInitialisations(aventure, "Aventure");

        System.out.println("L'initialisation du menu réussi");
    }

    /**
     * Teste le menu Paramètre
     */
    @Test
    void TestMenuParametre(){
        testerMenu(aventure, "Hashimiste", new Dimension(800, 600));

        testThemeMenu(aventure, "default");
        testThemeMenu(aventure, "candy");

        System.out.println("Le test du changement de thème réussi");
    }

    /**
     * Teste tous les boutons du menu
     */
    @Test
    void testTousLesBoutons() {
        // Création d'une carte pour mapper le nom du bouton à son état attendu (true pour actif, false pour inactif)
        Map<String, Boolean> boutonsEtEtats = new HashMap<>();
        boutonsEtEtats.put("Menu", true);
        boutonsEtEtats.put("Niveau 1", false);
        boutonsEtEtats.put("Niveau 2", false);
        boutonsEtEtats.put("Niveau 3", false);
        boutonsEtEtats.put("Niveau 4", false);
        boutonsEtEtats.put("Niveau 5", false);
        boutonsEtEtats.put("Niveau 6", false);
        boutonsEtEtats.put("Niveau 7", false);
        boutonsEtEtats.put("Niveau 8", false);
        boutonsEtEtats.put("Niveau 9", false);
        boutonsEtEtats.put("Niveau 10", false);

        // Itération sur chaque entrée de la carte pour vérifier chaque bouton
        boutonsEtEtats.forEach(this::verifierEtatBouton);
    }
}