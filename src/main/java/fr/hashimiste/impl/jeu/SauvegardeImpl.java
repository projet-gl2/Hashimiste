package fr.hashimiste.impl.jeu;

import fr.hashimiste.core.jeu.Historique;
import fr.hashimiste.core.jeu.Sauvegarde;
import fr.hashimiste.core.joueur.Profil;

import java.sql.Timestamp;

/**
 * Cette classe représente une sauvegarde de jeu.
 * Elle implémente l'interface Sauvegarde.
 */
public class SauvegardeImpl implements Sauvegarde {

    private final Profil profil;
    private final String nom;
    private final Historique reference;

    /**
     * Constructeur de la classe SauvegardeImpl.
     *
     * @param profil    le profil du joueur.
     * @param nom       le nom de la sauvegarde.
     * @param reference l'historique de la sauvegarde.
     */
    public SauvegardeImpl(Profil profil, String nom, Historique reference) {
        this.profil = profil;
        this.nom = nom;
        this.reference = reference;
    }

    /**
     * Cette méthode est utilisée pour obtenir le profil du joueur.
     *
     * @return le profil du joueur.
     */
    @Override
    public Profil getProfil() {
        return profil;
    }

    /**
     * Cette méthode est utilisée pour obtenir le nom de la sauvegarde.
     *
     * @return le nom de la sauvegarde.
     */
    @Override
    public String getNom() {
        return nom;
    }

    /**
     * Cette méthode est utilisée pour obtenir l'historique de la sauvegarde.
     *
     * @return l'historique de la sauvegarde.
     */
    @Override
    public Historique getReference() {
        return reference;
    }

    /**
     * Cette méthode est utilisée pour obtenir le timestamp de la sauvegarde.
     *
     * @return le timestamp de la sauvegarde.
     */
    @Override
    public Timestamp getSauvegardeTimestamp() {
        return reference.getTimestamp();
    }
}