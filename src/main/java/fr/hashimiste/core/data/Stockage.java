package fr.hashimiste.core.data;

import fr.hashimiste.core.data.sql.Identifiable;
import fr.hashimiste.impl.data.sql.filter.EqFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * L'interface Stockage définit les méthodes que doivent implémenter les classes qui peuvent gérer le stockage des données.
 */
public interface Stockage {

    /**
     * Charge une liste d'objets de type T à partir du stockage.
     *
     * @param clazz la classe des objets à charger.
     * @param extra un paramètre supplémentaire pour la requête.
     * @return une liste d'objets de type T.
     */
    <T> List<T> charger(Class<T> clazz, String extra);

    /**
     * Charge une liste d'objets de type T à partir du stockage en utilisant des jointures et un filtre.
     *
     * @param clazz     la classe des objets à charger.
     * @param jointures la liste des jointures à utiliser.
     * @param filtre    le filtre à utiliser.
     * @return une liste d'objets de type T.
     */
    <T> List<T> charger(Class<T> clazz, List<Join> jointures, Filter filtre);

    /**
     * Charge une liste d'objets de type T à partir du stockage en utilisant des jointures.
     *
     * @param clazz     la classe des objets à charger.
     * @param jointures la liste des jointures à utiliser.
     * @return une liste d'objets de type T.
     */
    default <T> List<T> charger(Class<T> clazz, List<Join> jointures) {
        return charger(clazz, jointures, null);
    }

    /**
     * Charge une liste d'objets de type T à partir du stockage en utilisant un filtre.
     *
     * @param clazz  la classe des objets à charger.
     * @param filtre le filtre à utiliser.
     * @return une liste d'objets de type T.
     */
    default <T> List<T> charger(Class<T> clazz, Filter filtre) {
        return charger(clazz, null, filtre);
    }

    /**
     * Charge une liste d'objets de type T à partir du stockage.
     *
     * @param clazz la classe des objets à charger.
     * @return une liste d'objets de type T.
     */
    default <T> List<T> charger(Class<T> clazz) {
        return charger(clazz, null, null);
    }

    /**
     * Récupère un objet de type T à partir du stockage en utilisant un filtre.
     *
     * @param clazz  la classe de l'objet à récupérer.
     * @param filtre le filtre à utiliser.
     * @return un objet de type T.
     */
    default <T> T get(Class<T> clazz, Filter filtre) {
        return charger(clazz, null, filtre).stream().findFirst().orElse(null);
    }

    /**
     * Sauvegarde une liste d'objets dans le stockage.
     *
     * @param list la liste des objets à sauvegarder.
     */
    <T> void sauvegarder(List<T> list);

    /**
     * Supprime des objets de type T du stockage en utilisant un filtre.
     *
     * @param clazz  la classe des objets à supprimer.
     * @param filtre le filtre à utiliser.
     */
    <T> void supprimer(Class<T> clazz, Filter filtre);

    /**
     * Supprime un objet identifiable du stockage.
     *
     * @param t l'objet à supprimer.
     */
    default <T extends Identifiable> void supprimer(T t) {
        supprimer(t.getClass(), new EqFilter(t.getColonneId(), t.getId()));
    }

    /**
     * Sauvegarde un objet dans le stockage.
     *
     * @param t l'objet à sauvegarder.
     */
    default <T> void sauvegarder(T t) {
        List<T> list = new ArrayList<>();
        list.add(t);
        sauvegarder(list);
    }

    /**
     * Génère une exception si la classe n'est pas gérée par le stockage.
     *
     * @param clazz la classe non supportée.
     */
    default Supplier<UnsupportedOperationException> getNonGerer(Class<?> clazz) {
        return () -> new UnsupportedOperationException("La classe " + clazz.getName() + " n'est pas gérée par ce stockage");
    }

    /**
     * Envoie une exception si la classe n'est pas gérée par le stockage.
     *
     * @param clazz la classe non supportée.
     */
    default void throwNonGerer(Class<?> clazz) {
        throw getNonGerer(clazz).get();
    }
}