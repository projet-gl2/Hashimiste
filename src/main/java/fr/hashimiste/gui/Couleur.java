package fr.hashimiste.gui;
import java.awt.Color;
/**
 * Classe pour les couleurs
 * @author Arthur Dureau
 */
public class Couleur {
    /**
     * Couleurs boutons
     */
    public final static Color COULEUR_BOUTON = new Color(160, 158, 188);
    /**
     * Couleurs texte boutons
     */
    public final static Color COULEUR_TEXTE_BOUTON = new Color(251, 250, 242);
    /**
     * Couleurs boutons désactivés
     */
    public final static Color COULEUR_BOUTON_DESACTIVE = new Color(197, 179, 179);
    /**
     * Couleurs fond
     */
    public final static Color COULEUR_FOND = new Color(251,250,242);
    /**
     * Couleurs transparent
     */
    public final static Color COULEUR_TRANSPARENT = new Color(0x00000000, true);

    /**
     * Constructeur privée pour éviter l'instanciation
     **/
    private Couleur() {}
}
