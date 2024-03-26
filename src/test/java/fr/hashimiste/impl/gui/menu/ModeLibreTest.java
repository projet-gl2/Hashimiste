package fr.hashimiste.impl.gui.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.util.*;

class ModeLibreTest extends fr.hashimiste.impl.gui.menu.Test{

    private ModeLibre modeLibre;

    /**
     * Renvoie le conteneur à tester
     * @return le conteneur à tester
     */
    @Override
    protected Container getTestContainer() {
        return modeLibre;
    }

    /**
     * Teste l'initialisation du menu
     * @throws IOException si une erreur d'entrée/sortie survient
     */
    @BeforeEach
    void testMenuInitialisation() throws IOException {
        modeLibre = (ModeLibre) testMenuInitialisations(modeLibre, "ModeLibre");

        System.out.println("L'initialisation du menu réussi");
    }

    /**
     * Teste le menu Paramètre
     */
    @Test
    void TestMenuModeLibre(){
        testerMenu(modeLibre, "Hashimiste", new Dimension(800, 600));

        testThemeMenu(modeLibre, "default");
        testThemeMenu(modeLibre, "candy");

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

        // Itération sur chaque entrée de la carte pour vérifier chaque bouton
        boutonsEtEtats.forEach(this::verifierEtatBouton);
    }
}