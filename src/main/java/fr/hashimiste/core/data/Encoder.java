package fr.hashimiste.core.data;

/**
 * L'interface Encoder définit les méthodes que doivent implémenter les classes qui peuvent encoder des objets en un certain format.
 *
 * @param <T> le type de l'objet à encoder.
 * @param <C> le type du conteneur dans lequel l'objet encodé sera stocké.
 */
public interface Encoder<T, C> {
    /**
     * Sauvegarde un objet encodé dans un conteneur.
     *
     * @param container le conteneur dans lequel l'objet encodé sera stocké.
     * @param object    l'objet à encoder.
     */
    void sauvegarder(C container, T object);
}