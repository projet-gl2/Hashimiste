package fr.hashimiste.core.data.sql;

import fr.hashimiste.core.data.Encoder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * L'interface SQLEncoder étend l'interface Encoder pour le type générique T.
 * Elle fournit des méthodes pour encoder un objet de type T dans un PreparedStatement SQL.
 *
 * @param <T> le type d'objet à encoder.
 */
public interface SQLEncoder<T> extends Encoder<T, PreparedStatement> {

    /**
     * Retourne le nom de la table pour cet encodeur.
     *
     * @return le nom de la table.
     */
    String getNomTable();

    /**
     * Retourne les noms des colonnes pour cet encodeur.
     * Par défaut, il retourne un tableau de la taille retournée par getColumnsCount().
     *
     * @return un tableau contenant les noms des colonnes.
     */
    default String[] getColonnes() {
        if (getNombreColonnes() == -1) {
            throw new UnsupportedOperationException("Vous devez surcharger getNombreColonnes() ou getColonnes().");
        }
        return new String[getNombreColonnes()];
    }

    /**
     * Retourne le nombre de colonnes pour cet encodeur.
     * Par défaut, il retourne -1.
     *
     * @return le nombre de colonnes.
     */
    default int getNombreColonnes() {
        return -1;
    }

    /**
     * Effectue des actions post-insertion pour un objet de type T.
     * Par défaut, cette méthode ne fait rien.
     *
     * @param object    l'objet pour lequel effectuer les actions post-insertion.
     * @param statement le ResultSet contenant les résultats de l'insertion.
     */
    default void apresInsertion(T object, ResultSet statement) {
    }
}