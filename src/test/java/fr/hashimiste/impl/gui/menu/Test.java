package fr.hashimiste.impl.gui.menu;

import fr.hashimiste.impl.gui.theme.DefaultTheme;
import org.opentest4j.AssertionFailedError;

import javax.swing.*;

import java.awt.*;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public abstract class Test {

    protected abstract Container getTestContainer();

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