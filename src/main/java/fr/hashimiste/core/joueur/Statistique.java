package fr.hashimiste.core.joueur;

import fr.hashimiste.core.data.sql.Identifiable;

/**
 * L'interface Statistique représente les statistiques d'un joueur.
 * Elle est identifiable et contient des méthodes pour obtenir et modifier les valeurs des statistiques.
 */
public interface Statistique extends Identifiable {
    /**
     * Récupère la clé de la statistique.
     *
     * @return la clé de la statistique.
     */
    StatistiqueKey getKey();

    /**
     * Récupère le profil du joueur associé à la statistique.
     *
     * @return le profil du joueur.
     */
    Profil getProfil();

    /**
     * Récupère le nom de la statistique.
     *
     * @return le nom de la statistique.
     */
    default String getNom() {
        return getKey().getNom();
    }

    /**
     * Récupère la valeur de la statistique.
     *
     * @return la valeur de la statistique.
     */
    int getValeur();

    /**
     * Définit la valeur de la statistique.
     *
     * @param valeur la nouvelle valeur de la statistique.
     */
    void setValeur(int valeur);

    /**
     * Incrémente la valeur de la statistique.
     */
    void incrementer();

    /**
     * Décrémente la valeur de la statistique.
     */
    void decrementer();

    /**
     * Réinitialise la valeur de la statistique.
     */
    void reset();

    /**
     * Récupère l'identifiant de l'entité associée à la statistique.
     *
     * @return l'identifiant de l'entité.
     */
    int getEntityId();
}