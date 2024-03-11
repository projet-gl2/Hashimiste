package fr.hashimiste.impl.data.sql.encoder;

import fr.hashimiste.core.data.sql.SQLEncoder;
import fr.hashimiste.core.jeu.Ile;
import fr.hashimiste.impl.jeu.IleImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * La classe IleEncoder implémente l'interface SQLEncoder pour le type Ile.
 * Elle fournit des méthodes pour encoder une Ile dans un PreparedStatement SQL.
 */
public class IleEncoder implements SQLEncoder<Ile> {

    /**
     * Retourne le nom de la table pour cet encodeur.
     *
     * @return le nom de la table.
     */
    @Override
    public String getNomTable() {
        return "ile";
    }

    /**
     * Retourne les noms des colonnes pour cet encodeur.
     *
     * @return un tableau contenant les noms des colonnes.
     */
    @Override
    public String[] getColonnes() {
        return new String[]{"id_map", "x", "y", "n"};
    }

    /**
     * Sauvegarde une Ile dans un PreparedStatement SQL.
     *
     * @param preparedStatement le PreparedStatement dans lequel sauvegarder l'Ile.
     * @param object            l'Ile à sauvegarder.
     */
    @Override
    public void sauvegarder(PreparedStatement preparedStatement, Ile object) {
        try {
            preparedStatement.setInt(1, object.getGrille().getId());
            preparedStatement.setInt(2, object.getX());
            preparedStatement.setInt(3, object.getY());
            preparedStatement.setInt(4, object.getN());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Effectue des actions post-insertion pour une Ile.
     *
     * @param object    l'Ile pour laquelle effectuer les actions post-insertion.
     * @param statement le ResultSet contenant les résultats de l'insertion.
     */
    @Override
    public void apresInsertion(Ile object, ResultSet statement) {
        try {
            ((IleImpl) object).setId(statement.getInt("last_insert_rowid()"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}