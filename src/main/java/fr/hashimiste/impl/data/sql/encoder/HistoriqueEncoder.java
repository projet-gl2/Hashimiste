package fr.hashimiste.impl.data.sql.encoder;

import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.data.sql.SQLEncoder;
import fr.hashimiste.core.jeu.Historique;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * La classe HistoriqueEncoder implémente l'interface SQLEncoder pour le type Historique.
 * Elle fournit des méthodes pour encoder un Historique dans un PreparedStatement SQL.
 */
public class HistoriqueEncoder implements SQLEncoder<Historique> {

    private final Stockage stockage;

    /**
     * Constructeur de HistoriqueEncoder.
     *
     * @param stockage le stockage à utiliser pour sauvegarder les données.
     */
    public HistoriqueEncoder(Stockage stockage) {
        this.stockage = stockage;
    }

    /**
     * Sauvegarde un Historique dans un PreparedStatement SQL.
     *
     * @param preparedStatement le PreparedStatement dans lequel sauvegarder l'Historique.
     * @param object            l'Historique à sauvegarder.
     */
    @Override
    public void sauvegarder(PreparedStatement preparedStatement, Historique object) {
        try {
            preparedStatement.setTimestamp(1, object.getTimestamp());
            preparedStatement.setInt(2, object.getGrille().getId());
            preparedStatement.setInt(3, object.getIle1() == null ? 0 : object.getIle1().getId());
            preparedStatement.setInt(4, object.getIle2() == null ? 0 : object.getIle2().getId());
            preparedStatement.setInt(5, object.getAction().ordinal());
            preparedStatement.setTimestamp(6, object.getAvant() == null ? null : object.getAvant().getTimestamp());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retourne le nombre de colonnes pour cet encodeur.
     *
     * @return le nombre de colonnes.
     */
    @Override
    public int getNombreColonnes() {
        return 6;
    }

    /**
     * Retourne le nom de la table pour cet encodeur.
     *
     * @return le nom de la table.
     */
    @Override
    public String getNomTable() {
        return "historique";
    }

    /**
     * Effectue des actions post-insertion pour un Historique.
     *
     * @param object    l'Historique pour lequel effectuer les actions post-insertion.
     * @param statement le ResultSet contenant les résultats de l'insertion.
     */
    @Override
    public void apresInsertion(Historique object, ResultSet statement) {
        if (object.getAvant() != null) {
            stockage.sauvegarder(object.getAvant());
        }
    }
}