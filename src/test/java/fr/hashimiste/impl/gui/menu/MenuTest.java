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
import java.util.function.Predicate;

import fr.hashimiste.core.joueur.Profil;
import fr.hashimiste.core.gui.JFrameTemplate;
import org.opentest4j.AssertionFailedError;


class MenuTest {
    // TODO: write tests for Menu class

    private Menu menu;

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

    @Test
    void testMenu() {
        // Teste si les composants ne sont pas null
        assertNotNull(menu, "Le menu ne devrait pas être null");

        // Teste si le menu est activé
        assertTrue(menu.isEnabled(), "Le menu devrait être activé");

        // Teste si la dimension par défaut est appliquée
        assertEquals(new Dimension(800, 600), menu.getSize(), "La dimension par défaut devrait être 800x600");

        // Teste si le titre est appliqué
        assertEquals("Hashimiste", menu.getTitle(), "Le titre devrait être 'Hashimiste'");

        // Teste si le thème est appliqué
        assertEquals("default", menu.getProperties().getProperty("theme"), "Le thème devrait être 'default'");

        // Teste d'un autre thème
        menu.getProperties().setProperty("theme", "candy");
        assertEquals("candy", menu.getProperties().getProperty("theme"), "Le thème devrait être 'candy'");

        System.out.println("Le test des composants du menu réussi");
    }

    @Test
    void testBoutonParametre() {
        JButton boutonParametre = getComponentByName(JButton.class, c -> "Paramètre".equals(c.getText()))
                .orElseThrow(() -> new AssertionFailedError("Le bouton paramètre n'a pas été trouvé"));
        assertNotNull(boutonParametre, "Le bouton paramètre ne devrait pas être null");
        assertTrue(boutonParametre.isEnabled(), "Le bouton paramètre devrait être activé");
        assertEquals("Paramètre", boutonParametre.getText(), "Le texte du bouton paramètre devrait être 'Paramètre'");
        System.out.println("Le test du bouton paramètre réussi");
    }

    @Test
    void testBoutonAventure(){
        JButton boutonAventure = getComponentByName(JButton.class, c -> "Aventure".equals(c.getText()))
                .orElseThrow(() -> new AssertionFailedError("Le bouton aventure n'a pas été trouvé"));
        assertNotNull(boutonAventure, "Le bouton aventure ne devrait pas être null");
        assertTrue(boutonAventure.isEnabled(), "Le bouton aventure devrait être activé");
        assertEquals("Aventure", boutonAventure.getText(), "Le texte du bouton aventure devrait être 'Aventure'");
        System.out.println("Le test du bouton aventure réussi");
    }

    @Test
    void testBoutonTutoriel(){
        JButton boutonTutoriel = getComponentByName(JButton.class, c -> "Tutoriel".equals(c.getText()))
                .orElseThrow(() -> new AssertionFailedError("Le bouton tutoriel n'a pas été trouvé"));
        assertNotNull(boutonTutoriel, "Le bouton tutoriel ne devrait pas être null");
        assertFalse(boutonTutoriel.isEnabled(), "Le bouton tutoriel devrait être désactivé");
        assertEquals("Tutoriel", boutonTutoriel.getText(), "Le texte du bouton tutoriel devrait être 'Tutoriel'");
        System.out.println("Le test du bouton tutoriel réussi");
    }

    @Test
    void testBoutonModeLibre(){
        JButton boutonModeLibre = getComponentByName(JButton.class, c -> "Mode libre".equals(c.getText()))
                .orElseThrow(() -> new AssertionFailedError("Le bouton mode libre n'a pas été trouvé"));
        assertNotNull(boutonModeLibre, "Le bouton mode libre ne devrait pas être null");
        assertTrue(boutonModeLibre.isEnabled(), "Le bouton mode libre devrait être activé");
        assertEquals("Mode libre", boutonModeLibre.getText(), "Le texte du bouton mode libre devrait être 'Mode libre'");
        System.out.println("Le test du bouton mode libre réussi");
    }

    @Test
    void testBoutonMulti(){
        JButton boutonMulti = getComponentByName(JButton.class, c -> "Multijoueur".equals(c.getText()))
                .orElseThrow(() -> new AssertionFailedError("Le bouton multijoueur n'a pas été trouvé"));
        assertNotNull(boutonMulti, "Le bouton multijoueur ne devrait pas être null");
        assertFalse(boutonMulti.isEnabled(), "Le bouton multijoueur devrait être désactivé");
        assertEquals("Multijoueur", boutonMulti.getText(), "Le texte du bouton multijoueur devrait être 'Multijoueur'");
        System.out.println("Le test du bouton multijoueur réussi");
    }

    @Test
    void testBoutonTechnique(){
        JButton boutonTechnique = getComponentByName(JButton.class, c -> "Technique".equals(c.getText()))
                .orElseThrow(() -> new AssertionFailedError("Le bouton technique n'a pas été trouvé"));
        assertNotNull(boutonTechnique, "Le bouton technique ne devrait pas être null");
        assertTrue(boutonTechnique.isEnabled(), "Le bouton technique devrait être activé");
        assertEquals("Technique", boutonTechnique.getText(), "Le texte du bouton technique devrait être 'Technique'");
        System.out.println("Le test du bouton technique réussi");
    }

    @Test
    void testBoutonProfils(){
        JButton boutonProfils = getComponentByName(JButton.class, c -> "Profils".equals(c.getText()))
                .orElseThrow(() -> new AssertionFailedError("Le bouton profils n'a pas été trouvé"));
        assertNotNull(boutonProfils, "Le bouton profils ne devrait pas être null");
        assertTrue(boutonProfils.isEnabled(), "Le bouton profils devrait être activé");
        assertEquals("Profils", boutonProfils.getText(), "Le texte du bouton profils devrait être 'Profils'");
        System.out.println("Le test du bouton profils réussi");
    }

    private <T extends Component> Optional<T> getComponentByName(Class<T> clazz, Predicate<T> filtre) {
        return getComponentByName(clazz, menu, filtre);
    }

    private <T extends Component> Optional<T> getComponentByName(Class<T> clazz, Container parent, Predicate<T> filtre) {
        Optional<T> t = Arrays.stream(parent.getComponents())
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .filter(filtre)
                .findFirst();
        if (t.isPresent()) {
            return t;
        }
        for (Component c : parent.getComponents()) {
            if (!(c instanceof Container)) {
                continue;
            }
            t = getComponentByName(clazz, (Container) c, filtre);
            if (t.isPresent()) {
                return t;
            }
        }
        return Optional.empty();
    }

}