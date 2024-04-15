package fr.hashimiste.impl.data.sql.decoder;

import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.data.sql.SQLDecoder;
import fr.hashimiste.core.joueur.Statistique;
import fr.hashimiste.core.joueur.StatistiqueKey;
import fr.hashimiste.impl.joueur.StatistiqueImpl;

import java.sql.ResultSet;

/**
 * La classe StatistiqueDecoder implémente l'interface SQLDecoder pour le type Statistique.
 * Elle fournit des méthodes pour décoder une Statistique à partir d'un ResultSet SQL.
 */
public class StatistiqueDecoder implements SQLDecoder<Statistique> {
    private final Stockage stockage;

    /**
     * Constructeur de StatistiqueDecoder.
     *
     * @param stockage le stockage à utiliser pour charger les données.
     */
    public StatistiqueDecoder(Stockage stockage) {
        this.stockage = stockage;
    }

    /**
     * Retourne le nom du conteneur pour ce décodeur.
     *
     * @return le nom du conteneur.
     */
    @Override
    public String getNomContaineur() {
        return "statistique";
    }

    @Override
    public String getIdColonne() {
        return "id_stat";
    }

    /**
     * Crée une Statistique à partir d'un ResultSet SQL.
     *
     * @param input le ResultSet à partir duquel créer la Statistique.
     * @return la Statistique créée.
     */
    @Override
    public Statistique creer(ResultSet input, Object... args) {
        try {
            return new StatistiqueImpl(
                    stockage,
                    input.getInt("id_stat"),
                    StatistiqueKey.fromNom(input.getString("nom")),
                    input.getInt("id_profil"),
                    input.getInt("id_entity"),
                    input.getInt("valeur")
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}