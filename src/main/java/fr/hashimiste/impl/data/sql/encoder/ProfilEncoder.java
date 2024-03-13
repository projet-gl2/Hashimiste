package fr.hashimiste.impl.data.sql.encoder;

import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.data.sql.SQLEncoder;
import fr.hashimiste.core.joueur.Profil;
import fr.hashimiste.impl.joueur.ProfilImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

/**
 * La classe ProfilEncoder implémente l'interface SQLEncoder pour le type Profil.
 * Elle fournit des méthodes pour encoder un Profil dans un PreparedStatement SQL.
 */
public class ProfilEncoder implements SQLEncoder<Profil> {
    private final Stockage stockage;

    /**
     * Constructeur de ProfilEncoder.
     *
     * @param stockage le stockage à utiliser pour sauvegarder les données.
     */
    public ProfilEncoder(Stockage stockage) {
        this.stockage = stockage;
    }

    /**
     * Retourne le nom de la table pour cet encodeur.
     *
     * @return le nom de la table.
     */
    @Override
    public String getNomTable() {
        return "profil";
    }

    /**
     * Retourne les noms des colonnes pour cet encodeur.
     *
     * @return un tableau contenant les noms des colonnes.
     */
    @Override
    public String[] getColonnes() {
        return new String[]{"id_profil", "nom"};
    }

    /**
     * Sauvegarde un Profil dans un PreparedStatement SQL.
     *
     * @param preparedStatement le PreparedStatement dans lequel sauvegarder le Profil.
     * @param object            le Profil à sauvegarder.
     */
    @Override
    public void sauvegarder(PreparedStatement preparedStatement, Profil object) {
        try {
            if (object.estEnregistre()) {
                preparedStatement.setInt(1, object.getId());
            } else {
                preparedStatement.setNull(1, Types.INTEGER);
            }
            preparedStatement.setString(2, object.getNom());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Effectue des actions post-insertion pour un Profil.
     *
     * @param object    le Profil pour lequel effectuer les actions post-insertion.
     * @param statement le ResultSet contenant les résultats de l'insertion.
     */
    @Override
    public void apresInsertion(Profil object, ResultSet statement) {
        stockage.sauvegarder(((ProfilImpl) object).getStatistiques());
    }
}