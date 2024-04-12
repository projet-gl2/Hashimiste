package fr.hashimiste.impl.data.sql.decoder;

import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.data.sql.SQLDecoder;
import fr.hashimiste.core.joueur.Profil;
import fr.hashimiste.impl.joueur.ProfilImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * La classe ProfilDecoder implémente l'interface SQLDecoder pour le type Profil.
 * Elle fournit des méthodes pour décoder un Profil à partir d'un ResultSet SQL.
 */
public class ProfilDecoder implements SQLDecoder<Profil> {
    private final Stockage stockage;

    /**
     * Constructeur de ProfilDecoder.
     *
     * @param stockage le stockage à utiliser pour charger les données.
     */
    public ProfilDecoder(Stockage stockage) {
        this.stockage = stockage;
    }

    /**
     * Retourne le nom du conteneur pour ce décodeur.
     *
     * @return le nom du conteneur.
     */
    @Override
    public String getNomContaineur() {
        return "profil";
    }

    /**
     * Crée un Profil à partir d'un ResultSet SQL.
     *
     * @param input le ResultSet à partir duquel créer le Profil.
     * @return le Profil créé.
     */
    @Override
    public Profil creer(ResultSet input, Object... args) {
        try {
            return new ProfilImpl(stockage, input.getInt("id_profil"), input.getString("nom"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}