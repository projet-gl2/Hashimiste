package fr.hashimiste.core.data.sql;

/**
 * L'interface Identifiable définit les méthodes que doivent implémenter les classes qui peuvent être identifiées par un ID.
 */
public interface Identifiable {
    /**
     * Récupère l'ID de l'objet.
     *
     * @return l'ID de l'objet.
     */
    int getId();

    /**
     * Vérifie si l'objet est enregistré.
     *
     * @return vrai si l'ID de l'objet est différent de -1, faux sinon.
     */
    default boolean estEnregistre() {
        try {
            return getId() != -1;
        } catch (IllegalStateException e) {
            return false;
        }
    }

    /**
     * Récupère le nom de la colonne ID dans la base de données.
     *
     * @return le nom de la colonne ID.
     */
    String getColonneId();

    /**
     * Interface pour les opérations non sécurisées.
     * En l'on peut modifier l'ID de l'objet.
     */
    interface UNSAFE {
        /**
         * Définit l'ID de l'objet.
         *
         * @param id l'ID à définir.
         */
        void setId(int id);
    }
}