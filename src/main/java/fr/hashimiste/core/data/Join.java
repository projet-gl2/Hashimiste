package fr.hashimiste.core.data;

/**
 * L'interface Join définit les méthodes que doivent implémenter les classes qui peuvent joindre des données.
 */
public interface Join {
    /**
     * Récupère la jointure sous forme de chaîne de caractères.
     *
     * @return la jointure.
     */
    String getJointure();
}