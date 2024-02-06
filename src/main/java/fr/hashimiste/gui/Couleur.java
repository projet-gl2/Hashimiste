package fr.hashimiste.gui;

/**
 * Classe pour les couleurs
 * @author Arthur Dureau
 */
public class Couleur {
    private static java.awt.Color couleurBouton = new java.awt.Color(160, 158, 188);
    private static java.awt.Color couleurTextBouton = new java.awt.Color(251, 250, 242);
    private static java.awt.Color couleurBoutonDesactive = new java.awt.Color(197, 179, 179);
    private static java.awt.Color couleurFond = new java.awt.Color(251,250,242);

    /**
     * Retourne la couleur du bouton
     * @return Color
     */
    public static java.awt.Color getCouleurBouton() {
        return couleurBouton;
    }

    /**
     * Retourne la couleur du texte du bouton
     * @return Color
     */
    public static java.awt.Color getCouleurTextBouton() {
        return couleurTextBouton;
    }

    /**
     * Retourne la couleur du bouton désactivé
     * @return Color
     */
    public static java.awt.Color getCouleurBoutonDesactive() {
        return couleurBoutonDesactive;
    }

    /**
     * Retourne la couleur du fond
     * @return Color
     */
    public static java.awt.Color getCouleurFond() {
        return couleurFond;
    }

    /**
     * Modifie la couleur du bouton
     * @param couleurBouton
     */
    public void setCouleurBouton(java.awt.Color couleurBouton) {
        Couleur.couleurBouton = couleurBouton;
    }

    /**
     * Modifie la couleur du texte du bouton
     * @param couleurTextBouton
     */
    public void setCouleurTextBouton(java.awt.Color couleurTextBouton) {
        Couleur.couleurTextBouton = couleurTextBouton;
    }

    /**
     * Modifie la couleur du bouton désactivé
     * @param couleurBoutonDesactive
     */
    public void setCouleurBoutonDesactive(java.awt.Color couleurBoutonDesactive) {
        Couleur.couleurBoutonDesactive = couleurBoutonDesactive;
    }

    /**
     * Modifie la couleur du fond
     * @param couleurFond
     */
    public void setCouleurFond(java.awt.Color couleurFond) {
        Couleur.couleurFond = couleurFond;
    }
}
