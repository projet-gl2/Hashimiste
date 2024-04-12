package fr.hashimiste.core.data.sql;

import fr.hashimiste.core.data.Decoder;

import java.sql.ResultSet;

/**
 * L'interface SQLDecoder étend l'interface Decoder pour le type générique T.
 * Elle définit une méthode pour décoder un objet de type T à partir d'un ResultSet SQL.
 *
 * @param <T> le type d'objet à décoder.
 */
public interface SQLDecoder<T> extends Decoder<ResultSet, T> {

    default void apresCreation(T object, ResultSet statement) {
    }
}