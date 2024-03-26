package fr.hashimiste.impl.gui.theme;

import fr.hashimiste.core.gui.Theme;

import java.awt.*;

/**
 * La classe DefaultTheme implémente l'interface Theme.
 * Elle définit un thème par défaut avec des couleurs spécifiques pour les différents éléments de l'interface utilisateur.
 */
public class DefaultTheme implements Theme {
    /**
     * L'instance unique de la classe DefaultTheme.
     */
    public static final Theme INSTANCE = new DefaultTheme();

    /**
     * La couleur des boutons dans le thème par défaut.
     */
    private static final Color BOUTON = new Color(160, 158, 188);

    /**
     * La couleur du texte des boutons dans le thème par défaut.
     */
    private static final Color TEXTE_BOUTON = new Color(251, 250, 242);

    /**
     * La couleur des boutons désactivés dans le thème par défaut.
     */
    private static final Color BOUTON_DESACTIVE = new Color(197, 179, 179);

    /**
     * La couleur de fond dans le thème par défaut.
     */
    private static final Color FOND = new Color(251, 250, 242);

    /**
     * La couleur transparente dans le thème par défaut.
     */
    private static final Color TRANSPARENT = new Color(0, 0, 0, 0);

    /**
     * Le constructeur privé de la classe DefaultTheme.
     * Il est privé pour empêcher l'instanciation directe de cette classe.
     */
    private DefaultTheme() {
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
        return BOUTON;
    }
}
