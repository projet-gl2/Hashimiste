package fr.hashimiste.impl.data.sql.decoder;

import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.data.sql.SQLDecoder;
import fr.hashimiste.core.jeu.Grille;
import fr.hashimiste.core.jeu.Ile;
import fr.hashimiste.impl.data.sql.SQLStockage;
import fr.hashimiste.impl.jeu.IleImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * La classe IleDecoder implémente l'interface SQLDecoder pour le type Ile.
 * Elle fournit des méthodes pour décoder une Ile à partir d'un ResultSet SQL.
 */
public class IleDecoder implements SQLDecoder<Ile> {
    private final Stockage stockage;

    /**
     * Constructeur de IleDecoder.
     *
     * @param stockage le stockage à utiliser pour charger les données.
     */
    public IleDecoder(Stockage stockage) {
        this.stockage = stockage;
    }

    /**
     * Retourne le nom du conteneur pour ce décodeur.
     *
     * @return le nom du conteneur.
     */
    @Override
    public String getNomContaineur() {
        return "ile";
    }

    /**
     * Crée une Ile à partir d'un ResultSet SQL.
     *
     * @param input le ResultSet à partir duquel créer l'Ile.
     * @return l'Ile créée.
     */
    @Override
    public Ile creer(ResultSet input, Object... args) {
        try {
            int id = input.getInt("id_ile");
            int idMap = input.getInt("id_map");
            Grille map = ((SQLStockage) stockage).getDepuisCache(Grille.class, m -> m.getId() == idMap).orElse(null);
            int x = input.getInt("x");
            int y = input.getInt("y");
            int n = input.getInt("n");
            IleImpl ile = new IleImpl(x, y, n, map);
            ile.setId(id);
            return ile;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}