package fr.hashimiste.impl.gui.theme;

import fr.hashimiste.core.gui.Theme;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * La classe DebugTheme implémente l'interface Theme.
 * Elle définit un thème de débogage avec des couleurs spécifiques pour les différents éléments de l'interface utilisateur.
 */
public class DebugTheme implements Theme {
    /**
     * L'instance unique de la classe DebugTheme.
     */
    public static final Theme INSTANCE = new DebugTheme();

    /**
     * La couleur des boutons dans le thème de débogage.
     */
    private static final Color BOUTON = new Color(0, 255, 0);

    /**
     * La couleur du texte des boutons dans le thème de débogage.
     */
    private static final Color TEXTE_BOUTON;
    /**
     * La couleur des boutons désactivés dans le thème de débogage.
     */
    private static final Color BOUTON_DESACTIVE = new Color(0, 0, 255);
    /**
     * La couleur transparente dans le thème de débogage.
     */
    private static final Color TRANSPARENT = new Color(0, 0, 0, 0);

    static {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("mac")) {
            // Mac
            TEXTE_BOUTON = Color.BLACK; // Pour Mac
        } else {
            // Autres systèmes (Linux, etc.)
            TEXTE_BOUTON = new Color(251, 250, 242); // Couleur par défaut
        }
    }

    /**
     * Le constructeur privé de la classe DebugTheme.
     * Il est privé pour empêcher l'instanciation directe de cette classe.
     */
    private DebugTheme() {
    }

    @Override
    public Color getButtonColor() {
        return BOUTON;
    }

    @Override
    public Color getButtonTextColor() {
        return TEXTE_BOUTON;
    }

    @Override
    public Color getDisabledButtonColor() {
        return BOUTON_DESACTIVE;
    }

    @Override
    public Color getBackgroundColor() {
        return new Color(ThreadLocalRandom.current().nextInt(0x1000000));
    }

    @Override
    public Color getTransparentColor() {
        return TRANSPARENT;
    }

    @Override
    public Color getTextColor() {
        return Color.BLACK;
    }

    @Override
    public Color getPotentialBridgeColor() {
        return Color.BLUE;
    }
}
