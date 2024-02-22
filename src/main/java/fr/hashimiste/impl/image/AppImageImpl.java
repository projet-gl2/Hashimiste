package fr.hashimiste.impl.image;

import fr.hashimiste.core.image.AppImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * La classe AppImageImpl implémente l'interface AppImage.
 * Elle fournit des méthodes pour obtenir différents types d'images.
 */
public class AppImageImpl implements AppImage {

    /**
     * La couleur de fond par défaut pour les images.
     */
    public static final Color BACKGROUND_COLOR = new Color(0xFFFCF2);
    /**
     * La position X du titre dans l'atlas.
     */
    public static final int TITRE_X = 0;
    /**
     * La position Y du titre dans l'atlas.
     */
    public static final int TITRE_Y = 0;
    /**
     * La largeur du titre dans l'atlas.
     */
    public static final int TITRE_WIDTH = 590;
    /**
     * La hauteur du titre dans l'atlas.
     */
    public static final int TITRE_HEIGHT = 92;
    /**
     * La position X de l'icône dans l'atlas.
     */
    public static final int ICON_X = 0;
    /**
     * La position Y de l'icône dans l'atlas.
     */
    public static final int ICON_Y = TITRE_HEIGHT + 5;
    /**
     * La largeur de l'icône dans l'atlas.
     */
    public static final int ICON_WIDTH = 371;
    /**
     * La hauteur de l'icône dans l'atlas.
     */
    public static final int ICON_HEIGHT = 469;
    /**
     * La largeur du logo complet.
     */
    public static final int LOGO_WIDTH = 1024;
    /**
     * La hauteur du logo complet.
     */
    public static final int LOGO_HEIGHT = 859;
    /**
     * La position X de l'icône dans le logo.
     */
    public static final int LOGO_ICON_X = 326;
    /**
     * La position Y de l'icône dans le logo.
     */
    public static final int LOGO_ICON_Y = 213;
    /**
     * La position X du titre dans le logo.
     */
    public static final int LOGO_TITRE_X = 237;
    /**
     * La position Y du titre dans le logo.
     */
    public static final int LOGO_TITRE_Y = 702;
    /**
     * L'atlas contenant les images.
     */
    private final BufferedImage logoAtlas;
    /**
     * L'icon reconstruit à partir de l'atlas.
     */
    private final BufferedImage icon;
    /**
     * Le titre reconstruit à partir de l'atlas.
     */
    private final BufferedImage titre;
    /**
     * Le logo complet reconstruit à partir de l'icon et du titre.
     */
    private final BufferedImage logo;

    /**
     * Le constructeur de la classe AppImageImpl.
     * Il initialise les images en les chargeant à partir des ressources.
     */
    public AppImageImpl() {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("images/logo_atlas.png");
            this.logoAtlas = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load image from resources", e);
        }
        titre = logoAtlas.getSubimage(TITRE_X, TITRE_Y, TITRE_WIDTH, TITRE_HEIGHT);
        icon = logoAtlas.getSubimage(ICON_X, ICON_Y, ICON_WIDTH, ICON_HEIGHT);
        logo = new BufferedImage(LOGO_WIDTH, LOGO_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = logo.createGraphics();
        g.drawImage(icon, LOGO_ICON_X, LOGO_ICON_Y, null);
        g.drawImage(titre, LOGO_TITRE_X, LOGO_TITRE_Y, null);
    }

    @Override
    public BufferedImage getIcon(boolean transparent) {
        if (!transparent) {
            BufferedImage icon = new BufferedImage(ICON_WIDTH, ICON_HEIGHT, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = icon.createGraphics();
            g.setColor(BACKGROUND_COLOR);
            g.fillRect(0, 0, ICON_WIDTH, ICON_HEIGHT);
            g.drawImage(this.icon, 0, 0, null);
            return icon;
        }
        return icon;
    }

    @Override
    public BufferedImage getLogo(boolean transparent) {
        if (!transparent) {
            BufferedImage logo = new BufferedImage(LOGO_WIDTH, LOGO_HEIGHT, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = logo.createGraphics();
            g.setColor(BACKGROUND_COLOR);
            g.fillRect(0, 0, LOGO_WIDTH, LOGO_HEIGHT);
            g.drawImage(this.logo, 0, 0, null);
            return logo;
        }
        return logo;
    }

    @Override
    public BufferedImage getTitre(boolean transparent) {
        if (!transparent) {
            BufferedImage titre = new BufferedImage(TITRE_WIDTH, TITRE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = titre.createGraphics();
            g.setColor(BACKGROUND_COLOR);
            g.fillRect(0, 0, TITRE_WIDTH, TITRE_HEIGHT);
            g.drawImage(this.titre, 0, 0, null);
            return titre;
        }
        return titre;
    }
}
