package fr.hashimiste.impl.gui.menu;

import fr.hashimiste.core.data.Filter;
import fr.hashimiste.core.data.Join;
import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.gui.JFrameTemplate;
import fr.hashimiste.core.gui.JFrameTemplateProfil;
import fr.hashimiste.core.joueur.Profil;
import fr.hashimiste.impl.joueur.ProfilImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModeLibreTest extends fr.hashimiste.impl.gui.menu.Test{

    private ModeLibre modeLibre;

    @Override
    protected Container getTestContainer() {
        return modeLibre;
    }

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
        JFrameTemplate frame = new ProfilSelection(tempFile.toFile(), stockage);
        frame = new Menu(frame, test);
        modeLibre = new ModeLibre((JFrameTemplateProfil) frame);

        assertEquals(test, modeLibre.getProfil(), "Le profil devrait être le profil passé en paramètre");

        System.out.println("L'initialisation du menu réussi");
    }

    @Test
    void TestMenuModeLibre(){
        testerMenu(modeLibre, "Hashimiste", new Dimension(800, 600));

        assertEquals("default", modeLibre.getProperties().getProperty("theme"), "Le thème devrait être '" + "default" + "'");

        // Test d'un autre thème
        modeLibre.getProperties().setProperty("theme", "candy");
        assertEquals("candy", modeLibre.getProperties().getProperty("theme"), "Le thème devrait être 'candy'");

        // Test de repassé au thème par défaut
        modeLibre.getProperties().setProperty("theme", "default");
        assertEquals("default", modeLibre.getProperties().getProperty("theme"), "Le thème devrait être '" + "default" + "'");

        System.out.println("Le test du changement de thème réussi");
    }

    @Test
    void testTousLesBoutons() {
        // Création d'une carte pour mapper le nom du bouton à son état attendu (true pour actif, false pour inactif)
        Map<String, Boolean> boutonsEtEtats = new HashMap<>();
        boutonsEtEtats.put("Menu", true);

        // Itération sur chaque entrée de la carte pour vérifier chaque bouton
        boutonsEtEtats.forEach(this::verifierEtatBouton);
    }
}