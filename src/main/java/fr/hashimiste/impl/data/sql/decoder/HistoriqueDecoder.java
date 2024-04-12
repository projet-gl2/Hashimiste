package fr.hashimiste.impl.data.sql.decoder;

import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.data.sql.SQLDecoder;
import fr.hashimiste.core.jeu.Grille;
import fr.hashimiste.core.jeu.Historique;
import fr.hashimiste.core.jeu.Ile;
import fr.hashimiste.core.utils.DevUtils;
import fr.hashimiste.impl.data.sql.SQLStockage;
import fr.hashimiste.impl.data.sql.filter.EqFilter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * La classe HistoriqueDecoder implémente l'interface SQLDecoder pour le type Historique.
 * Elle fournit des méthodes pour décoder un Historique à partir d'un ResultSet SQL.
 */
public class HistoriqueDecoder implements SQLDecoder<Historique> {
    private final Stockage stockage;

    /**
     * Constructeur de HistoriqueDecoder.
     *
     * @param stockage le stockage à utiliser pour charger les données.
     */
    public HistoriqueDecoder(Stockage stockage) {
        this.stockage = stockage;
    }

    /**
     * Retourne le nom du conteneur pour ce décodeur.
     *
     * @return le nom du conteneur.
     */
    @Override
    public String getNomContaineur() {
        return "historique";
    }

    @Override
    public String getIdColonne() {
        return "date";
    }

    /**
     * Crée un Historique à partir d'un ResultSet SQL.
     *
     * @param input le ResultSet à partir duquel créer l'Historique.
     * @return l'Historique créé.
     */
    @Override
    public Historique creer(ResultSet input, Object... args) {
        try {
            Timestamp date = input.getTimestamp("date");
            Historique avant = null;
            if (input.getTimestamp("avant") != null) {
                Timestamp tsAvant = input.getTimestamp("avant");
                avant = ((SQLStockage) stockage).getDepuisCache(Historique.class, m -> m.getTimestamp().equals(tsAvant))
                        .orElseGet(() -> stockage.get(Historique.class, new EqFilter("date", tsAvant.getTime())));
            }
            int idGrille = input.getInt("id_map");
            Grille map = ((SQLStockage) stockage).getDepuisCache(Grille.class, m -> m.getId() == idGrille)
                    .orElseGet(() -> stockage.get(Grille.class, new EqFilter("id_map", idGrille)));
            int idIle1 = input.getInt("id_ile1");
            Ile ile1 = null;
            if (idIle1 > 0) {
                ile1 = ((SQLStockage) stockage).getDepuisCache(Ile.class, m -> m.getId() == idIle1)
                        .orElseGet(() -> stockage.get(Ile.class, new EqFilter("id_ile", idIle1)));
            }
            int idIle2 = input.getInt("id_ile2");
            Ile ile2 = null;
            if (idIle2 > 0) {
                ile2 = ((SQLStockage) stockage).getDepuisCache(Ile.class, m -> m.getId() == idIle2)
                        .orElseGet(() -> stockage.get(Ile.class, new EqFilter("id_ile", idIle2)));
            }
            Historique.Action action = Historique.Action.values()[input.getInt("action")];
            return new Historique(date, avant, map, ile1, ile2, action);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}