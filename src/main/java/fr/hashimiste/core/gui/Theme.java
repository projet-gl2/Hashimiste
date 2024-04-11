package fr.hashimiste.core.gui;

import java.awt.*;

/**
 * L'interface Theme définit les couleurs utilisées dans l'interface utilisateur.
 */
public interface Theme {
    /**
     * Renvoie la couleur des boutons.
     *
     * @return une instance de Color représentant la couleur des boutons.
     */
    Color getButtonColor();

    /**
     * Renvoie la couleur du texte des boutons.
     *
     * @return une instance de Color représentant la couleur du texte des boutons.
     */
    Color getButtonTextColor();

    /**
     * Renvoie la couleur des boutons désactivés.
     *
     * @return une instance de Color représentant la couleur des boutons désactivés.
     */
    Color getDisabledButtonColor();

    /**
     * Renvoie la couleur de fond de l'interface utilisateur.
     *
     * @return une instance de Color représentant la couleur de fond.
     */
    Color getBackgroundColor();

    /**
     * Renvoie la couleur transparente du thème.
     *
     * @return une instance de Color représentant la couleur transparente.
     */
    Color getTransparentColor();

    /**
     * Renvoie la couleur du texte.
     *
     * @return une instance de Color représentant la couleur du texte.
     */
    Color getTextColor();

    /**
     * Renvoie la couleur des ponts potentiels.
     *
     * @return une instance de Color représentant la couleur des ponts potentiels.
     */
    Color getPotentialBridgeColor();
}