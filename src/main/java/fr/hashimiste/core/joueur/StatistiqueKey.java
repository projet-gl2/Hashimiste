package fr.hashimiste.core.joueur;

/**
 * L'énumération StatistiqueKey représente les différentes clés de statistiques pour un joueur.
 * Chaque clé a un nom et une méthode pour obtenir une clé à partir de son nom.
 */
public enum StatistiqueKey {
    /**
     * Clé pour le nombre de parties gagnées.
     */
    PARTIE_GAGNEE("Partie gagnée"),
    /**
     * Clé pour le nombre de parties abandonnées.
     */
    PARTIE_ABANDONNEE("Partie abandonnée"),
    /**
     * Clé pour le nombre de parties jouées.
     */
    PARTIE_JOUEE("Partie jouée"),
    /**
     * Clé pour le nombre total de coups.
     */
    COUPS_TOTAL("Coups total"),
    /**
     * Clé pour le nombre moyen de coups.
     */
    COUPS_MOYEN("Coups moyen"),
    /**
     * Clé pour le nombre maximum de coups.
     */
    COUPS_MAX("Coups max"),
    /**
     * Clé pour le nombre minimum de coups.
     */
    COUPS_MIN("Coups min"),
    /**
     * Clé pour le temps total de jeu.
     */
    TEMPS_TOTAL("Temps total"),
    /**
     * Clé pour le temps moyen de jeu.
     */
    TEMPS_MOYEN("Temps moyen"),
    /**
     * Clé pour le temps maximum de jeu.
     */
    TEMPS_MAX("Temps max"),
    /**
     * Clé pour le temps minimum de jeu.
     */
    TEMPS_MIN("Temps min");

    private final String key;

    /**
     * Constructeur de StatistiqueKey.
     *
     * @param key le nom de la clé.
     */
    StatistiqueKey(String key) {
        this.key = key;
    }

    /**
     * Récupère une clé de statistique à partir de son nom.
     *
     * @param nom le nom de la clé.
     * @return la clé de statistique correspondante, ou null si aucune clé ne correspond.
     */
    public static StatistiqueKey fromNom(String nom) {
        for (StatistiqueKey key : StatistiqueKey.values()) {
            if (key.getNom().equals(nom)) {
                return key;
            }
        }
        return null;
    }

    /**
     * Récupère le nom de la clé.
     *
     * @return le nom de la clé.
     */
    public String getNom() {
        return this.key;
    }
}