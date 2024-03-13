package fr.hashimiste.core.jeu;

import fr.hashimiste.core.joueur.Profil;
import fr.hashimiste.impl.jeu.SauvegardeImpl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

/**
 * La classe Historique représente un enregistrement d'une action effectuée dans le jeu.
 * Elle contient des informations sur l'action, les îles impliquées et la grille de jeu.
 */
public class Historique {
    private final Timestamp timestamp;
    private final Historique avant;
    private final Grille grille;
    private final Ile ile1;
    private final Ile ile2;
    private final Action action;

    /**
     * Constructeur de Historique.
     *
     * @param timestamp le moment où l'action a été effectuée.
     * @param avant     l'historique précédent.
     * @param grille    la grille de jeu.
     * @param ile1      la première île impliquée dans l'action.
     * @param ile2      la deuxième île impliquée dans l'action.
     * @param action    l'action effectuée.
     */
    public Historique(Timestamp timestamp, Historique avant, Grille grille, Ile ile1, Ile ile2, Action action) {
        this.timestamp = timestamp;
        this.avant = avant;
        this.grille = grille;
        this.ile1 = ile1;
        this.ile2 = ile2;
        this.action = action;
    }

    /**
     * Constructeur de Historique.
     *
     * @param grille la grille de jeu.
     * @param ile1   la première île impliquée dans l'action.
     * @param ile2   la deuxième île impliquée dans l'action.
     * @param action l'action effectuée.
     */
    public Historique(Grille grille, Ile ile1, Ile ile2, Action action) {
        this(Timestamp.from(Instant.now()), null, grille, ile1, ile2, action);
    }

    /**
     * Récupère l'historique précédent.
     *
     * @return l'historique précédent.
     */
    public Historique getAvant() {
        return avant;
    }

    /**
     * Récupère le moment où l'action a été effectuée.
     *
     * @return le moment où l'action a été effectuée.
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * Récupère la grille de jeu.
     *
     * @return la grille de jeu.
     */
    public Grille getGrille() {
        return grille;
    }

    /**
     * Récupère la première île impliquée dans l'action.
     *
     * @return la première île impliquée dans l'action.
     */
    public Ile getIle1() {
        return ile1;
    }

    /**
     * Récupère la deuxième île impliquée dans l'action.
     *
     * @return la deuxième île impliquée dans l'action.
     */
    public Ile getIle2() {
        return ile2;
    }

    /**
     * Récupère l'action effectuée.
     *
     * @return l'action effectuée.
     */
    public Action getAction() {
        return action;
    }

    /**
     * Crée un nouvel historique suivant l'historique actuel.
     *
     * @param ile1   la première île impliquée dans l'action.
     * @param ile2   la deuxième île impliquée dans l'action.
     * @param action l'action effectuée.
     * @return le nouvel historique.
     */
    public Historique creerSuivant(Ile ile1, Ile ile2, Action action) {
        return new Historique(Timestamp.from(Instant.now()), this, grille, ile1, ile2, action);
    }

    /**
     * Crée une sauvegarde à partir de cet historique.
     *
     * @param profil le profil du joueur.
     * @param nom    le nom de la sauvegarde.
     * @return la sauvegarde créée.
     */
    public Sauvegarde creerSauvegarde(Profil profil, String nom) {
        if (nom == null || nom.isEmpty()) {
            nom = "Sauvegarde du " + new Date(timestamp.getTime());
        }
        return new SauvegardeImpl(profil, nom, this);
    }

    /**
     * Retourne une représentation sous forme de chaîne de cet historique.
     *
     * @return une représentation sous forme de chaîne de cet historique.
     */
    @Override
    public String toString() {
        return "Historique{" +
                "timestamp=" + timestamp +
                ", avant(timestamp)=" + (avant != null ? avant.getTimestamp() : "null") +
                ", grille(id)=" + grille.getId() +
                ", ile1(id)=" + (ile1 != null ? ile1.getId() : "null") +
                ", ile2(id)=" + (ile2 != null ? ile2.getId() : "null") +
                ", action=" + action +
                '}';
    }

    /**
     * L'énumération Action définit les différentes actions possibles dans le jeu.
     */
    public enum Action {
        NOUVELLE_GRILLE, AUCUN_PONT, UN_PONT, DEUX_PONTS
    }
}