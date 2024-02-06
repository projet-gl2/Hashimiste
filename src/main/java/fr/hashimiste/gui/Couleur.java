package fr.hashimiste.gui;

public class Couleur {
    private static java.awt.Color couleurBouton = new java.awt.Color(160, 158, 188);
    private static java.awt.Color couleurTextBouton = new java.awt.Color(251, 250, 242);
    private static java.awt.Color couleurBoutonDesactive = new java.awt.Color(197, 179, 179);
    private static java.awt.Color couleurFond = new java.awt.Color(251,250,242);

    public static java.awt.Color getCouleurBouton() {
        return couleurBouton;
    }

    public static java.awt.Color getCouleurTextBouton() {
        return couleurTextBouton;
    }

    public static java.awt.Color getCouleurBoutonDesactive() {
        return couleurBoutonDesactive;
    }

    public static java.awt.Color getCouleurFond() {
        return couleurFond;
    }

    public void setCouleurBouton(java.awt.Color couleurBouton) {
        Couleur.couleurBouton = couleurBouton;
    }

    public void setCouleurTextBouton(java.awt.Color couleurTextBouton) {
        Couleur.couleurTextBouton = couleurTextBouton;
    }

    public void setCouleurBoutonDesactive(java.awt.Color couleurBoutonDesactive) {
        Couleur.couleurBoutonDesactive = couleurBoutonDesactive;
    }

    public void setCouleurFond(java.awt.Color couleurFond) {
        Couleur.couleurFond = couleurFond;
    }
}
