package fr.hashimiste.impl.data.sql.joins;

import fr.hashimiste.core.data.Join;

/**
 * La classe NaturalJoin implémente l'interface Join.
 * Elle fournit une méthode pour générer une clause de jointure SQL NATURAL JOIN.
 */
public class NaturalJoin implements Join {

    private final String table;

    /**
     * Constructeur de NaturalJoin.
     *
     * @param table le nom de la table avec laquelle effectuer la jointure naturelle.
     */
    public NaturalJoin(String table) {
        this.table = table;
    }

    /**
     * Génère une clause de jointure SQL NATURAL JOIN.
     *
     * @return la clause de jointure SQL.
     */
    @Override
    public String getJointure() {
        return "NATURAL JOIN " + table;
    }
}