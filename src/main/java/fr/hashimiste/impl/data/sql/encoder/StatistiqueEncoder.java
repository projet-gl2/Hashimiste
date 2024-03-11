package fr.hashimiste.impl.data.sql.encoder;

import fr.hashimiste.core.data.sql.SQLEncoder;
import fr.hashimiste.core.joueur.Statistique;

import java.sql.PreparedStatement;
import java.sql.Types;

/**
 * La classe StatistiqueEncoder implémente l'interface SQLEncoder pour le type Statistique.
 * Elle fournit des méthodes pour encoder une Statistique dans un PreparedStatement SQL.
 */
public class StatistiqueEncoder implements SQLEncoder<Statistique> {

    /**
     * Sauvegarde une Statistique dans un PreparedStatement SQL.
     *
     * @param preparedStatement le PreparedStatement dans lequel sauvegarder la Statistique.
     * @param object            la Statistique à sauvegarder.
     */
    @Override
    public void sauvegarder(PreparedStatement preparedStatement, Statistique object) {
        try {
            if (object.estEnregistre()) {
                preparedStatement.setInt(1, object.getId());
            } else {
                preparedStatement.setNull(1, Types.INTEGER);
            }
            preparedStatement.setInt(2, object.getProfil().getId());
            preparedStatement.setString(3, object.getNom());
            preparedStatement.setInt(4, object.getEntityId());
            preparedStatement.setInt(5, object.getValeur());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retourne le nom de la table pour cet encodeur.
     *
     * @return le nom de la table.
     */
    @Override
    public String getNomTable() {
        return "statistique";
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
}