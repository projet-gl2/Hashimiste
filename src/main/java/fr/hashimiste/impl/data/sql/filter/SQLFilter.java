package fr.hashimiste.impl.data.sql.filter;

import fr.hashimiste.core.data.Filter;

/**
 * L'interface SQLFilter étend l'interface Filter.
 * Elle fournit une méthode statique pour créer un filtre d'égalité SQL.
 */
public interface SQLFilter extends Filter {

    /**
     * Crée un filtre d'égalité SQL.
     *
     * @param colonne le nom de la colonne sur laquelle appliquer le filtre.
     * @param valeur  la valeur à laquelle la colonne doit être égale.
     * @return un filtre d'égalité SQL.
     */
    static SQLFilter eq(String colonne, Object valeur) {
        return new EqFilter(colonne, valeur);
    }
}