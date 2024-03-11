package fr.hashimiste.core.utils;

import java.util.Arrays;
import java.util.List;

/**
 * La classe CollectionsUtils est une classe utilitaire pour les collections.
 * Elle contient des méthodes pour créer des listes à partir d'un tableau d'éléments.
 * Cette classe ne peut pas être instanciée.
 */
public class CollectionsUtils {
    /**
     * Constructeur privé pour empêcher l'instanciation de la classe.
     * Lève une exception si une tentative d'instanciation est faite.
     */
    private CollectionsUtils() {
        throw new IllegalStateException("Classe utilitaire");
    }

    /**
     * Crée une liste à partir d'un tableau d'éléments.
     *
     * @param elements le tableau d'éléments.
     * @return une liste contenant les éléments du tableau.
     */
    public static <T> List<T> listeDe(T... elements) {
        return Arrays.asList(elements);
    }
}