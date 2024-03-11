package fr.hashimiste.core.data;

/**
 * L'interface Decoder définit les méthodes que doivent implémenter les classes qui peuvent déchiffrer des données.
 *
 * @param <I> le type de données en entrée.
 * @param <T> le type de données en sortie.
 */
public interface Decoder<I, T> {
    /**
     * Récupère le nom du conteneur.
     *
     * @return le nom du conteneur.
     */
    String getNomContaineur();

    /**
     * Crée un nouvel objet de type T à partir des données en entrée.
     *
     * @param input les données en entrée.
     * @return un nouvel objet de type T.
     */
    T creer(I input);
}