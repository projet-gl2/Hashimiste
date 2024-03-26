package fr.hashimiste.impl.gui.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.util.*;

class TechniqueTest extends fr.hashimiste.impl.gui.menu.Test{
    private Technique technique;

    /**
     * Renvoie le conteneur à tester
     * @return le conteneur à tester
     */
    @Override
    protected Container getTestContainer() {
        return technique;
    }

    /**
     * Teste l'initialisation du menu
     * @throws IOException si une erreur d'entrée/sortie survient
     */
    @BeforeEach
    void testMenuInitialisation() throws IOException {
        technique = (Technique) testMenuInitialisations(technique, "Technique");

        System.out.println("L'initialisation du menu réussi");
    }

    /**
     * Teste le menu Paramètre
     */
    @Test
    void TestMenuParametre(){
        testerMenu(technique, "Hashimiste", new Dimension(800, 600));

        testThemeMenu(technique, "default");
        testThemeMenu(technique, "candy");

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

        boutonsEtEtats.forEach(this::verifierEtatBouton);
    }
}