package fr.hashimiste.core.data;

/**
 * L'interface Filter définit les méthodes que doivent implémenter les classes qui peuvent filtrer des données.
 */
public interface Filter {
    /**
     * Récupère le filtre sous forme de chaîne de caractères.
     *
     * @return le filtre.
     */
    String getFiltre();
}