package fr.hashimiste.core.jeu;

import fr.hashimiste.core.joueur.Profil;

import java.sql.Timestamp;

/**
 * L'interface Sauvegarde représente une sauvegarde de jeu.
 * Elle contient des méthodes pour obtenir des informations sur la sauvegarde, le profil du joueur et la grille de jeu.
 */
public interface Sauvegarde {

    /**
     * Récupère la grille de jeu de la sauvegarde.
     *
     * @return la grille de jeu de la sauvegarde.
     */
    default Grille getGrille() {
        return getReference().getGrille();
    }

    /**
     * Récupère le profil du joueur de la sauvegarde.
     *
     * @return le profil du joueur de la sauvegarde.
     */
    Profil getProfil();

    /**
     * Récupère le nom de la sauvegarde.
     *
     * @return le nom de la sauvegarde.
     */
    String getNom();

    /**
     * Récupère l'historique de référence de la sauvegarde.
     *
     * @return l'historique de référence de la sauvegarde.
     */
    Historique getReference();

    /**
     * Récupère le moment où la sauvegarde a été créée.
     *
     * @return le moment où la sauvegarde a été créée.
     */
    default Timestamp getSauvegardeTimestamp() {
        return getReference().getTimestamp();
    }

}