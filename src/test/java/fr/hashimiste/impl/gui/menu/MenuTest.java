package fr.hashimiste.impl.gui.menu;

import static org.junit.jupiter.api.Assertions.*;

import fr.hashimiste.core.data.Filter;
import fr.hashimiste.core.data.Join;
import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.impl.joueur.ProfilImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;

import fr.hashimiste.core.joueur.Profil;
import fr.hashimiste.core.gui.JFrameTemplate;

class MenuTest extends TestMenu {

    private Menu menu;

    /**
     * Renvoie le conteneur à tester
     * @return le conteneur à tester
     */
    @Override
    protected Container getTestContainer() {
        return menu;
    }

    /**
     * Teste l'initialisation du menu
     * @throws IOException si une erreur d'entrée/sortie survient
     */
    @BeforeEach
    void testMenuInitialisation() throws IOException {
        ProfilImpl test = new ProfilImpl("test");
        Path tempFile = Files.createTempFile("", "");
        tempFile.toFile().deleteOnExit();
        Properties properties = new Properties();
        properties.setProperty("theme", "default");
        properties.store(Files.newOutputStream(tempFile), "Hashimiste properties");
        Stockage stockage = new Stockage() {
            @Override
            public <T> List<T> charger(Class<T> clazz, String extra) {
                if (clazz == Profil.class) {
                    return (List<T>) Collections.singletonList(test);
                }
                return Collections.emptyList();
            }

            @Override
            public <T> List<T> charger(Class<T> clazz, List<Join> jointures, Filter filtre) {
                return this.charger(clazz, (String) null);
            }

            @Override
            public <T> void sauvegarder(List<T> list) {
            }

            @Override
            public <T> void supprimer(Class<T> clazz, Filter filtre) {
            }
        };
        menu = new Menu(new JFrameTemplate(null, tempFile.toFile(), stockage, new Dimension(0, 0)) {
            @Override
            public void changerFenetre(JFrame window) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }, test);

        assertEquals(test, menu.getProfil(), "Le profil devrait être le profil passé en paramètre");

        System.out.println("L'initialisation du menu réussi");
    }

    /**
     * Teste le menu principal
     */
    @Test
    void testMenu() {

        testerMenu(menu, "Hashimiste", new Dimension(800, 600));

        testThemeMenu(menu, "default");
        testThemeMenu(menu, "candy");

        System.out.println("Le test du menu est réussi");
    }

    /**
     * Teste tous les boutons du menu
     */
    @Test
    void testTousLesBoutons() {
        // Création d'une carte pour mapper le nom du bouton à son état attendu (true pour actif, false pour inactif)
        Map<String, Boolean> boutonsEtEtats = new HashMap<>();
        boutonsEtEtats.put("Paramètre", true);
        boutonsEtEtats.put("Aventure", true);
        boutonsEtEtats.put("Tutoriel", false);
        boutonsEtEtats.put("Mode libre", true);
        boutonsEtEtats.put("Multijoueur", false);
        boutonsEtEtats.put("Technique", true);
        boutonsEtEtats.put("Profils", true);

        // Itération sur chaque entrée de la carte pour vérifier chaque bouton
        boutonsEtEtats.forEach(this::verifierEtatBouton);
    }

}