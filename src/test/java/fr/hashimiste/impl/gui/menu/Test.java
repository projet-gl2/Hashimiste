package fr.hashimiste.impl.gui.menu;

import fr.hashimiste.core.data.Filter;
import fr.hashimiste.core.data.Join;
import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.gui.JFrameTemplateProfil;
import fr.hashimiste.core.joueur.Profil;
import fr.hashimiste.impl.gui.theme.DefaultTheme;
import fr.hashimiste.impl.joueur.ProfilImpl;
import org.opentest4j.AssertionFailedError;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import fr.hashimiste.core.gui.JFrameTemplate;

public abstract class Test {

    protected abstract Container getTestContainer();

    /**
     * Teste le menu
     *
     * @param menu le menu à tester
     * @param titreAttendu le titre attendu
     * @param dimensionAttendue la dimension attendue
     */
    protected void testerMenu(JFrame menu, String titreAttendu, Dimension dimensionAttendue) {
        // Test si les composants ne sont pas null
        assertNotNull(menu, "Le menu ne devrait pas être null");

        // Test si le menu est activé
        assertTrue(menu.isEnabled(), "Le menu devrait être activé");

        // Test si la dimension par défaut est appliquée
        assertEquals(dimensionAttendue, menu.getSize(), "La dimension par défaut devrait être " + dimensionAttendue);

        // Test si le titre est appliqué
        assertEquals(titreAttendu, menu.getTitle(), "Le titre devrait être '" + titreAttendu + "'");

        System.out.println("Le test des composants du menu réussi");
    }

    /**
     * Teste le thème du menu
     *
     * @param menu          le menu à tester
     * @param themeAttendu  le thème attendu
     */
    protected void testThemeMenu(JFrameTemplate menu, String themeAttendu) {

        menu.getProperties().setProperty("theme", themeAttendu);
        assertEquals(themeAttendu, menu.getProperties().getProperty("theme"), "Le thème devrait être " + themeAttendu);

        System.out.println("Le test du thème " + themeAttendu + " du menu réussi");
    }

    /**
     * Teste l'état des boutons
     *
     * @param nomBouton    le nom du bouton
     * @param attenduActif l'état attendu du bouton
     */
    protected void verifierEtatBouton(String nomBouton, boolean attenduActif) {
        JButton bouton = getComponentByName(JButton.class, getTestContainer(), c -> nomBouton.equals(c.getText()))
                .orElseThrow(() -> new AssertionFailedError("Le bouton " + nomBouton + " n'a pas été trouvé"));
        assertNotNull(bouton, "Le bouton " + nomBouton + " ne devrait pas être null");
        assertEquals(attenduActif, bouton.isEnabled(), "L'état activé du bouton " + nomBouton + " est incorrect");
        assertEquals(nomBouton, bouton.getText(), "Le texte du bouton ne correspond pas à " + nomBouton);

        DefaultTheme theme = (DefaultTheme) DefaultTheme.INSTANCE;
        assertEquals(attenduActif ? theme.getButtonColor() : theme.getDisabledButtonColor(), bouton.getBackground(), "La couleur du bouton devrait correspondre au thème");
        assertEquals(theme.getButtonTextColor(), bouton.getForeground(), "La couleur du texte du bouton devrait correspondre au thème");

        System.out.println("Le test du bouton " + nomBouton + " réussi");
    }

    /**
     * Initialise le menu et vérifie que le profil passé en paramètre est bien le profil du menu
     *
     * @param menu le menu à initialiser
     * @param nomMenu le nom du menu
     * @return le menu initialisé
     */
    protected JFrameTemplateProfil testMenuInitialisations(JFrameTemplateProfil menu, String nomMenu) throws IOException {
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
        JFrameTemplate frame = new ProfilSelection(tempFile.toFile(), stockage);
        frame = new Menu(frame, test);
            switch (nomMenu) {
                case "Aventure":
                    menu = new Aventure((JFrameTemplateProfil) frame);
                    break;
                case "ModeLibre":
                    menu = new ModeLibre((JFrameTemplateProfil) frame);
                    break;
                case "Parametre":
                    menu = new Parametre((JFrameTemplateProfil) frame);
                    break;
                case "Technique":
                    menu = new Technique((JFrameTemplateProfil) frame);
                    break;
                default:
                    break;


            }

            assertEquals(test, menu.getProfil(), "Le profil devrait être le profil passé en paramètre");

            System.out.println("L'initialisation du menu réussi");

            return menu;
    }

    /**
     * Récupère un composant par son nom
     *
     * @param clazz  la classe du composant
     * @param parent le conteneur parent
     * @param filter le filtre
     * @param <T>    le type du composant
     * @return le composant
     */
    protected <T extends Component> Optional<T> getComponentByName(Class<T> clazz, Container parent, Predicate<T> filter) {
        Optional<T> t = Arrays.stream(parent.getComponents())
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .filter(filter)
                .findFirst();
        if (t.isPresent()) {
            return t;
        }
        for (Component c : parent.getComponents()) {
            if (c instanceof Container) {
                t = getComponentByName(clazz, (Container) c, filter);
                if (t.isPresent()) {
                    return t;
                }
            }
        }
        return Optional.empty();
    }
}