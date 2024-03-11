package fr.hashimiste.impl.data.sql.decoder;

import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.data.sql.SQLDecoder;
import fr.hashimiste.core.jeu.Historique;
import fr.hashimiste.core.jeu.Sauvegarde;
import fr.hashimiste.core.joueur.Profil;
import fr.hashimiste.impl.data.sql.SQLStockage;
import fr.hashimiste.impl.data.sql.filter.EqFilter;
import fr.hashimiste.impl.jeu.SauvegardeImpl;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * La classe SauvegardeDecoder implémente l'interface SQLDecoder pour le type Sauvegarde.
 * Elle fournit des méthodes pour décoder une Sauvegarde à partir d'un ResultSet SQL.
 */
public class SauvegardeDecoder implements SQLDecoder<Sauvegarde> {

    private final Stockage stockage;

    /**
     * Constructeur de SauvegardeDecoder.
     *
     * @param stockage le stockage à utiliser pour charger les données.
     */
    public SauvegardeDecoder(Stockage stockage) {
        this.stockage = stockage;
    }

    /**
     * Retourne le nom du conteneur pour ce décodeur.
     *
     * @return le nom du conteneur.
     */
    @Override
    public String getNomContaineur() {
        return "sauvegarde";
    }

    /**
     * Crée une Sauvegarde à partir d'un ResultSet SQL.
     *
     * @param input le ResultSet à partir duquel créer la Sauvegarde.
     * @return la Sauvegarde créée.
     */
    @Override
    public Sauvegarde creer(ResultSet input) {
        try {
            Timestamp tsReference = input.getTimestamp("reference");
            Historique reference = ((SQLStockage) stockage).getDepuisCache(Historique.class, m -> m.getTimestamp().equals(tsReference))
                    .orElseGet(() -> stockage.get(Historique.class, new EqFilter("date", tsReference.getTime())));
            String nom = input.getString("nom");
            int idProfil = input.getInt("id_profil");
            Profil profil = ((SQLStockage) stockage).getDepuisCache(Profil.class, m -> m.getId() == idProfil)
                    .orElseGet(() -> stockage.get(Profil.class, new EqFilter("id_profil", idProfil)));
            return new SauvegardeImpl(profil, nom, reference);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}