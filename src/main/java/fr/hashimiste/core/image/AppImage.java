package fr.hashimiste.core.image;

import fr.hashimiste.impl.image.AppImageImpl;

import java.awt.image.BufferedImage;

/**
 * L'interface AppImage fournit des méthodes pour obtenir différents types d'images.
 * Elle a une INSTANCE qui est une implémentation de l'interface AppImage.
 */
public interface AppImage {
    /**
     * Une instance de l'interface AppImage.
     * C'est une implémentation de l'interface AppImage.
     */
    AppImage INSTANCE = new AppImageImpl();

    /**
     * Renvoie une image d'icône.
     *
     * @param transparent un booléen qui détermine si l'icône doit être transparente ou non.
     * @return une BufferedImage qui représente l'icône.
     */
    BufferedImage getIcon(boolean transparent);

    /**
     * Renvoie une image de logo.
     *
     * @param transparent un booléen qui détermine si le logo doit être transparent ou non.
     * @return une BufferedImage qui représente le logo.
     */
    BufferedImage getLogo(boolean transparent);

    /**
     * Renvoie une image de titre.
     *
     * @param transparent un booléen qui détermine si le titre doit être transparent ou non.
     * @return une BufferedImage qui représente le titre.
     */
    BufferedImage getTitre(boolean transparent);
}