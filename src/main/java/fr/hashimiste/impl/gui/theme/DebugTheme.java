package fr.hashimiste.impl.gui.theme;

import fr.hashimiste.core.gui.Theme;

import java.awt.*;

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
    private final static Color BOUTON = new Color(0, 255, 0);

    /**
     * La couleur du texte des boutons dans le thème de débogage.
     */
    private final static Color TEXTE_BOUTON = new Color(251, 250, 242);

    /**
     * La couleur des boutons désactivés dans le thème de débogage.
     */
    private final static Color BOUTON_DESACTIVE = new Color(0, 0, 255);

    /**
     * La couleur de fond dans le thème de débogage.
     */
    private final static Color FOND = new Color(255, 0, 0);

    /**
     * La couleur transparente dans le thème de débogage.
     */
    private final static Color TRANSPARENT = new Color(0, 0, 0, 0);

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
}
