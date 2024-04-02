package fr.hashimiste.impl.data.sql.encoder;

import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.data.sql.SQLEncoder;
import fr.hashimiste.core.jeu.Grille;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

/**
 * La classe GrilleEncoder implémente l'interface SQLEncoder pour le type Grille.
 * Elle fournit des méthodes pour encoder une Grille dans un PreparedStatement SQL.
 */
public class GrilleEncoder implements SQLEncoder<Grille> {
    private final Stockage stockage;

    /**
     * Constructeur de GrilleEncoder.
     *
     * @param stockage le stockage à utiliser pour sauvegarder les données.
     */
    public GrilleEncoder(Stockage stockage) {
        this.stockage = stockage;
    }

    /**
     * Retourne le nom de la table pour cet encodeur.
     *
     * @return le nom de la table.
     */
    @Override
    public String getNomTable() {
        return "map";
    }

    /**
     * Retourne le nombre de colonnes pour cet encodeur.
     *
     * @return le nombre de colonnes.
     */
    @Override
    public int getNombreColonnes() {
        return 5;
    }

    /**
     * Sauvegarde une Grille dans un PreparedStatement SQL.
     *
     * @param preparedStatement le PreparedStatement dans lequel sauvegarder la Grille.
     * @param object            la Grille à sauvegarder.
     */
    @Override
    public void sauvegarder(PreparedStatement preparedStatement, Grille object) {
        try {
            if (object.estEnregistre()) {
                preparedStatement.setInt(1, object.getId());
            } else {
                preparedStatement.setNull(1, Types.INTEGER);
            }
            preparedStatement.setString(2, "");
            preparedStatement.setInt(3, object.getDifficulte().ordinal());
            preparedStatement.setInt(4, (int) object.getDimension().getWidth());
            preparedStatement.setInt(5, (int) object.getDimension().getHeight());
            preparedStatement.setBoolean(6, object.estAventure());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Effectue des actions post-insertion pour une Grille.
     *
     * @param object    la Grille pour laquelle effectuer les actions post-insertion.
     * @param statement le ResultSet contenant les résultats de l'insertion.
     */
    @Override
    public void apresInsertion(Grille object, ResultSet statement) {
        stockage.sauvegarder(object.getIles());
    }
}