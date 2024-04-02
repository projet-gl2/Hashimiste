package fr.hashimiste.impl.gui.theme;

import fr.hashimiste.core.gui.Theme;

import java.awt.*;

/**
 * La classe DefaultTheme implémente l'interface Theme.
 * Elle définit un thème par défaut avec des couleurs spécifiques pour les différents éléments de l'interface utilisateur.
 */
public class CandyTheme implements Theme {
    /**
     * L'instance unique de la classe DefaultTheme.
     */
    public static final Theme INSTANCE = new CandyTheme();

    /**
     * La couleur des boutons dans le thème par défaut.
     */
    private static final Color BOUTON = new Color(255, 10, 84);

    /**
     * La couleur du texte des boutons dans le thème par défaut.
     */
    private static final Color TEXTE_BOUTON;

    static {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("mac")) {
            // Mac
            TEXTE_BOUTON = Color.BLACK;
        } else {
            // Autres systèmes (Linux, etc.)
            TEXTE_BOUTON = new Color(251, 250, 242); // Couleur par défaut
        }
    }

    /**
     * La couleur des boutons désactivés dans le thème par défaut.
     */
    private static final Color BOUTON_DESACTIVE = new Color(255, 92, 138);

    /**
     * La couleur de fond dans le thème par défaut.
     */
    private static final Color FOND = new Color(255, 153, 172);

    /**
     * La couleur transparente dans le thème par défaut.
     */
    private static final Color TRANSPARENT = new Color(0, 0, 0, 0);

    /**
     * Le constructeur privé de la classe DefaultTheme.
     * Il est privé pour empêcher l'instanciation directe de cette classe.
     */
    private CandyTheme() {
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
        return FOND;
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
        return null;
    }
}
