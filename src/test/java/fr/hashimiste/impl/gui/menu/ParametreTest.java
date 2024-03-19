package fr.hashimiste.impl.gui.menu;

import fr.hashimiste.core.data.Filter;
import fr.hashimiste.core.data.Join;
import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.gui.JFrameTemplate;
import fr.hashimiste.core.gui.JFrameTemplateProfil;
import fr.hashimiste.core.joueur.Profil;
import fr.hashimiste.impl.gui.theme.DefaultTheme;
import fr.hashimiste.impl.joueur.ProfilImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class ParametreTest {

    private Parametre parametre;

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
        parametre = new Parametre((JFrameTemplateProfil) frame);

        assertEquals(test, parametre.getProfil(), "Le profil devrait être le profil passé en paramètre");

        System.out.println("L'initialisation du menu réussi");
    }

    @Test
    void TestMenuParametre(){
        // Teste si les composants ne sont pas null
        assertNotNull(parametre, "Le menu ne devrait pas être null");

        // Teste si le menu est activé
        assertTrue(parametre.isEnabled(), "Le menu devrait être activé");

        // Teste si la dimension par défaut est appliquée
        assertEquals(new Dimension(800, 600), parametre.getSize(), "La dimension par défaut devrait être 800x600");

        // Teste si le titre est appliqué
        assertEquals("Hashimiste", parametre.getTitle(), "Le titre devrait être 'Hashimiste'");

        // Teste si le thème est appliqué
        assertEquals("default", parametre.getProperties().getProperty("theme"), "Le thème devrait être 'default'");

        // Teste d'un autre thème
        parametre.getProperties().setProperty("theme", "candy");
        assertEquals("candy", parametre.getProperties().getProperty("theme"), "Le thème devrait être 'candy'");

        // Test de repassé au thème par défaut
        parametre.getProperties().setProperty("theme", "default");
        assertEquals("default", parametre.getProperties().getProperty("theme"), "Le thème devrait être 'default'");

        System.out.println("Le test des composants du menu paramètre réussi");
    }

    @Test
    void TestBoutonParametreSauvegarde(){
        JButton butSave = getComponentByName(JButton.class, c -> "Sauvegarder".equals(c.getText()))
                .orElseThrow(() -> new AssertionFailedError("Le bouton sauvegarder n'a pas été trouvé"));
        assertNotNull(butSave, "Le bouton sauvegarder ne devrait pas être null");
        assertTrue(butSave.isEnabled(), "Le bouton sauvegarder devrait être activé");
        assertEquals("Sauvegarder", butSave.getText(), "Le texte du bouton sauvegarder devrait être 'Paramètre'");
        // Vérifie si le text du bouton ainsi que tu text du bouton est bien de la bonne couleur selon le thème actuel ( getButtonColor() et getButtonTextColor() )
        DefaultTheme theme = (DefaultTheme) DefaultTheme.INSTANCE;
        assertEquals(theme.getButtonColor(), butSave.getBackground(), "La couleur du bouton sauvegarder devrait être celle du thème par défaut");
        assertEquals(theme.getButtonTextColor(), butSave.getForeground(), "La couleur du texte du bouton sauvegarder devrait être celle du thème par défaut");

        System.out.println("Le test du bouton sauvegarder réussi");
    }

    @Test
    void TestBoutonParametreMenu(){
        JButton butMenu = getComponentByName(JButton.class, c -> "Menu".equals(c.getText()))
                .orElseThrow(() -> new AssertionFailedError("Le bouton menu n'a pas été trouvé"));
        assertNotNull(butMenu, "Le bouton menu ne devrait pas être null");
        assertTrue(butMenu.isEnabled(), "Le bouton menu devrait être activé");
        assertEquals("Menu", butMenu.getText(), "Le texte du bouton menu devrait être 'Menu'");
        // Vérifie si le text du bouton ainsi que tu text du bouton est bien de la bonne couleur selon le thème actuel ( getButtonColor() et getButtonTextColor() )
        DefaultTheme theme = (DefaultTheme) DefaultTheme.INSTANCE;
        assertEquals(theme.getButtonColor(), butMenu.getBackground(), "La couleur du bouton menu devrait être celle du thème par défaut");
        assertEquals(theme.getButtonTextColor(), butMenu.getForeground(), "La couleur du texte du bouton menu devrait être celle du thème par défaut");

        System.out.println("Le test du bouton menu réussi");
    }


    private <T extends Component> Optional<T> getComponentByName(Class<T> clazz, Predicate<T> filtre) {
        return getComponentByName(clazz, parametre, filtre);
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