package fr.hashimiste.impl.data.sql.filter;

/**
 * La classe EqFilter implémente l'interface SQLFilter.
 * Elle fournit une méthode pour générer une clause de filtre SQL pour une égalité.
 */
public class EqFilter implements SQLFilter {

    private final String colonne;
    private final Object value;

    /**
     * Constructeur de EqFilter.
     *
     * @param colonne le nom de la colonne sur laquelle appliquer le filtre.
     * @param value   la valeur à laquelle la colonne doit être égale.
     */
    public EqFilter(String colonne, Object value) {
        this.colonne = colonne;
        this.value = value;
    }

    /**
     * Génère une clause de filtre SQL pour une égalité.
     *
     * @return la clause de filtre SQL.
     */
    @Override
    public String getFiltre() {
        String val;
        if (value instanceof String) {
            val = "'" + value + "'";
        } else if (value instanceof Boolean) {
            val = (boolean) value ? "1" : "0";
        } else if (value == null) {
            val = "NULL";
        } else {
            val = value.toString();
        }
        return colonne + " = " + val;
    }
}