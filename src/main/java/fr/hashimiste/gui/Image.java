package fr.hashimiste.gui;

/**
 * Classe pour les images
 * @author Arthur Dureau
 */
public class Image {
    private final static String cheminIcone = "src/main/resources/images/icon.png";
    private final static String cheminIconeTransparent = "src/main/resources/images/iconTransparent.png";
    private final static String logo = "src/main/resources/images/logo.png";
    private final static String logoOriginal = "src/main/resources/images/logoOriginal.png";
    private final static String logoTitre = "src/main/resources/images/logoTitre.png";
    private final static String logoTitreTransparent = "src/main/resources/images/logoTitreTransparent.png";

    /**
     * Retourne le chemin de l'icone
     * @return String
     */
    public static String getCheminIcone() {
        return cheminIcone;
    }

    /**
     * Retourne le chemin de l'icone transparent
     * @return String
     */
    public static String getCheminIconeTransparent() {
        return cheminIconeTransparent;
    }

    /**
     * Retourne le chemin du logo
     * @return String
     */
    public static String getLogo() {
        return logo;
    }

    /**
     * Retourne le chemin du logo original
     * @return String
     */
    public static String getLogoOriginal() {
        return logoOriginal;
    }

    /**
     * Retourne le chemin du logo titre
     * @return String
     */
    public static String getLogoTitre() {
        return logoTitre;
    }

    /**
     * Retourne le chemin du logo titre transparent
     * @return String
     */
    public static String getLogoTitreTransparent() {
        return logoTitreTransparent;
    }

}
