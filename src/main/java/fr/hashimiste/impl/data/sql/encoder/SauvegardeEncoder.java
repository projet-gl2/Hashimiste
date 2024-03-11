package fr.hashimiste.impl.data.sql.encoder;

import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.data.sql.SQLEncoder;
import fr.hashimiste.core.jeu.Sauvegarde;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * La classe SauvegardeEncoder implémente l'interface SQLEncoder pour le type Sauvegarde.
 * Elle fournit des méthodes pour encoder une Sauvegarde dans un PreparedStatement SQL.
 */
public class SauvegardeEncoder implements SQLEncoder<Sauvegarde> {

    private final Stockage stockage;

    /**
     * Constructeur de SauvegardeEncoder.
     *
     * @param stockage le stockage à utiliser pour sauvegarder les données.
     */
    public SauvegardeEncoder(Stockage stockage) {
        this.stockage = stockage;
    }

    /**
     * Sauvegarde une Sauvegarde dans un PreparedStatement SQL.
     *
     * @param preparedStatement le PreparedStatement dans lequel sauvegarder la Sauvegarde.
     * @param object            la Sauvegarde à sauvegarder.
     */
    @Override
    public void sauvegarder(PreparedStatement preparedStatement, Sauvegarde object) {
        try {
            preparedStatement.setInt(1, object.getProfil().getId());
            preparedStatement.setString(2, object.getNom());
            preparedStatement.setTimestamp(3, object.getReference().getTimestamp());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retourne le nom de la table pour cet encodeur.
     *
     * @return le nom de la table.
     */
    @Override
    public String getNomTable() {
        return "sauvegarde";
    }

    /**
     * Retourne les noms des colonnes pour cet encodeur.
     *
     * @return un tableau contenant les noms des colonnes.
     */
    @Override
    public String[] getColonnes() {
        return new String[]{"id_profil", "nom", "reference"};
    }

    /**
     * Effectue des actions post-insertion pour une Sauvegarde.
     *
     * @param object    la Sauvegarde pour laquelle effectuer les actions post-insertion.
     * @param statement le ResultSet contenant les résultats de l'insertion.
     */
    @Override
    public void apresInsertion(Sauvegarde object, ResultSet statement) {
        stockage.sauvegarder(object.getReference());
    }
}