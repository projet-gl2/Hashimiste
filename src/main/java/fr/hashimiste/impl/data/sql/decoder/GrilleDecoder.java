package fr.hashimiste.impl.data.sql.decoder;

import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.data.sql.SQLDecoder;
import fr.hashimiste.core.jeu.Difficulte;
import fr.hashimiste.core.jeu.Grille;
import fr.hashimiste.core.jeu.Ile;
import fr.hashimiste.impl.data.sql.filter.EqFilter;
import fr.hashimiste.impl.jeu.GrilleImpl;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * La classe GrilleDecoder implémente l'interface SQLDecoder pour le type Grille.
 * Elle fournit des méthodes pour décoder une Grille à partir d'un ResultSet SQL.
 */
public class GrilleDecoder implements SQLDecoder<Grille> {
    private final Stockage stockage;

    /**
     * Constructeur de GrilleDecoder.
     *
     * @param stockage le stockage à utiliser pour charger les données.
     */
    public GrilleDecoder(Stockage stockage) {
        this.stockage = stockage;
    }

    /**
     * Retourne le nom du conteneur pour ce décodeur.
     *
     * @return le nom du conteneur.
     */
    @Override
    public String getNomContaineur() {
        return "map";
    }

    /**
     * Crée une Grille à partir d'un ResultSet SQL.
     *
     * @param input le ResultSet à partir duquel créer la Grille.
     * @return la Grille créée.
     */
    @Override
    public Grille creer(ResultSet input) {
        try {
            int id = input.getInt("id_map");
            String nom = input.getString("nom");
            Difficulte difficulte = Difficulte.values()[input.getInt("difficulte")];
            int largeur = input.getInt("largeur");
            int hauteur = input.getInt("hauteur");
            GrilleImpl grille = new GrilleImpl(id, new Dimension(largeur, hauteur), difficulte);
            List<Ile> iles = stockage.charger(Ile.class, new EqFilter("id_map", id));
            iles.forEach(grille::poserIle);
            return grille;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}