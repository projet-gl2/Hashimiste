package fr.hashimiste.impl.gui.menu;

import fr.hashimiste.core.data.Filter;
import fr.hashimiste.core.data.Join;
import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.joueur.Profil;
import fr.hashimiste.impl.joueur.ProfilImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

class ProfilSelectionTest extends TestMenu {
    private ProfilSelection profilSelection;

    @Override
    protected Container getTestContainer() {
        return profilSelection;
    }

    /**
     * Teste l'initialisation du menu
     *
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
            public <T> java.util.List<T> charger(Class<T> clazz, String extra) {
                if (clazz == Profil.class) {
                    return (java.util.List<T>) Collections.singletonList(test);
                }
                return Collections.emptyList();
            }

            @Override
            public <T> java.util.List<T> charger(Class<T> clazz, java.util.List<Join> jointures, Filter filtre) {
                return this.charger(clazz, (String) null);
            }

            @Override
            public <T> void sauvegarder(List<T> list) {
            }

            @Override
            public <T> void supprimer(Class<T> clazz, Filter filtre) {
            }
        };
        profilSelection = new ProfilSelection(tempFile.toFile(), stockage);

        System.out.println("L'initialisation du menu réussi");
    }


    /**
     * Teste le menu de selection de profil
     */
    @Test
    void testMenuProfilSelection() {
        testerMenu(profilSelection, "Hashimiste", new Dimension(500, 300));

        testThemeMenu(profilSelection, "default");
        testThemeMenu(profilSelection, "candy");

        System.out.println("Le test du changement de thème réussi");
    }
}