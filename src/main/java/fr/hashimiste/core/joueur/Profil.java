package fr.hashimiste.core.joueur;

import fr.hashimiste.core.data.sql.Identifiable;

/**
 * L'interface Profil représente le profil d'un joueur.
 * Elle est identifiable et contient des méthodes pour obtenir le nom du joueur et ses statistiques.
 */
public interface Profil extends Identifiable {
    /**
     * Récupère le nom du joueur.
     *
     * @return le nom du joueur.
     */
    String getNom();

    /**
     * Récupère les statistiques du joueur par nom.
     *
     * @param nom le nom des statistiques.
     * @return les statistiques du joueur.
     */
    default Statistique getStatistique(String nom) {
        return getStatistique(StatistiqueKey.fromNom(nom));
    }

    /**
     * Récupère les statistiques du joueur par clé de statistique.
     *
     * @param nom la clé de statistique.
     * @return les statistiques du joueur.
     */
    default Statistique getStatistique(StatistiqueKey nom) {
        return getStatistique(nom, -1);
    }

    /**
     * Récupère les statistiques du joueur par nom et identifiant d'entité.
     *
     * @param nom      le nom des statistiques.
     * @param entityId l'identifiant de l'entité.
     * @return les statistiques du joueur.
     */
    default Statistique getStatistique(String nom, int entityId) {
        return getStatistique(StatistiqueKey.fromNom(nom), entityId);
    }

    /**
     * Récupère les statistiques du joueur par clé de statistique et identifiant d'entité.
     *
     * @param nom      la clé de statistique.
     * @param entityId l'identifiant de l'entité.
     * @return les statistiques du joueur.
     */
    Statistique getStatistique(StatistiqueKey nom, int entityId);

    /**
     * Sauvegarde le profil du joueur.
     */
    void sauvegarder();
}