package fr.hashimiste.impl.data.sql.joins;

import fr.hashimiste.core.data.Join;

/**
 * La classe InnerJoin implémente l'interface Join.
 * Elle fournit une méthode pour générer une clause de jointure SQL INNER JOIN.
 */
public class InnerJoin implements Join {
    private final String source;
    private final String sourceAlias;
    private final String colonneSource;
    private final String dest;
    private final String destAlias;
    private final String colonneDest;

    /**
     * Constructeur de InnerJoin.
     *
     * @param source        le nom de la table source.
     * @param colonneSource le nom de la colonne dans la table source.
     * @param dest          le nom de la table de destination.
     * @param colonneDest   le nom de la colonne dans la table de destination.
     */
    public InnerJoin(String source, String colonneSource, String dest, String colonneDest) {
        this(source, null, colonneSource, dest, null, colonneDest);
    }

    /**
     * Constructeur de InnerJoin.
     *
     * @param source        le nom de la table source.
     * @param sourceAlias   l'alias de la table source.
     * @param colonneSource le nom de la colonne dans la table source.
     * @param dest          le nom de la table de destination.
     * @param destAlias     l'alias de la table de destination.
     * @param colonneDest   le nom de la colonne dans la table de destination.
     */
    public InnerJoin(String source, String sourceAlias, String colonneSource, String dest, String destAlias, String colonneDest) {
        this.source = source;
        this.sourceAlias = sourceAlias;
        this.colonneSource = colonneSource;
        this.dest = dest;
        this.destAlias = destAlias;
        this.colonneDest = colonneDest;
    }

    /**
     * Génère une clause de jointure SQL INNER JOIN.
     *
     * @return la clause de jointure SQL.
     */
    @Override
    public String getJointure() {
        return "INNER JOIN " + dest + (destAlias != null ? " " + destAlias : "") + " ON " + (sourceAlias != null ? sourceAlias : source) + "." + colonneSource + " = " + (destAlias != null ? destAlias : dest) + "." + colonneDest;
    }
}