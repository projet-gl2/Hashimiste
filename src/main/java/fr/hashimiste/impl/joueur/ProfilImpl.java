package fr.hashimiste.impl.joueur;

import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.data.sql.Identifiable;
import fr.hashimiste.core.joueur.Profil;
import fr.hashimiste.core.joueur.Statistique;
import fr.hashimiste.core.joueur.StatistiqueKey;
import fr.hashimiste.impl.data.sql.filter.EqFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Cette classe représente un profil de joueur.
 * Elle implémente les interfaces Profil et Identifiable.__UNSAFE__.
 */
public class ProfilImpl implements Profil, Identifiable.UNSAFE {

    private final Stockage stockage;
    private final String nom;
    private List<Statistique> statistiques;
    private int id;

    /**
     * Constructeur de la classe ProfilImpl.
     *
     * @param nom le nom du profil.
     */
    public ProfilImpl(String nom) {
        this(null, -1, nom);
    }

    /**
     * Constructeur de la classe ProfilImpl.
     *
     * @param stockage le stockage des données.
     * @param id       l'identifiant du profil.
     * @param nom      le nom du profil.
     */
    public ProfilImpl(Stockage stockage, int id, String nom) {
        this.stockage = stockage;
        this.id = id;
        this.nom = nom;
        if (stockage != null && Arrays.stream(Thread.currentThread().getStackTrace()).noneMatch(e -> e.toString().contains("StatistiqueImpl"))) {
            this.statistiques = stockage.charger(Statistique.class, new EqFilter("id_profil", id));
        } else {
            this.statistiques = new ArrayList<>();
        }
    }

    /**
     * Cette méthode est utilisée pour obtenir le nom du profil.
     *
     * @return le nom du profil.
     */
    @Override
    public String getNom() {
        return nom;
    }

    /**
     * Cette méthode est utilisée pour obtenir une représentation sous forme de chaîne de caractères du profil.
     *
     * @return une représentation sous forme de chaîne de caractères du profil.
     */
    @Override
    public String toString() {
        return "Profil{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", statistiques=" + statistiques +
                '}';
    }

    /**
     * Cette méthode est utilisée pour obtenir une statistique spécifique du profil.
     *
     * @param key      la clé de la statistique.
     * @param entityId l'identifiant de l'entité.
     * @return la statistique spécifiée.
     */
    @Override
    public Statistique getStatistique(StatistiqueKey key, int entityId) {
        return statistiques.stream()
                .filter(statistique -> statistique.getKey().equals(key))
                .filter(statistique -> statistique.getEntityId() == entityId)
                .findFirst()
                .orElseGet(() -> {
                    Statistique statistique = new StatistiqueImpl(key, this, entityId, 0);
                    statistiques.add(statistique);
                    sauvegarder();
                    rechargerStats();
                    return statistique;
                });
    }

    /**
     * Cette méthode est utilisée pour obtenir l'identifiant du profil.
     *
     * @return l'identifiant du profil.
     */
    @Override
    public int getId() {
        if (id == -1) {
            throw new IllegalStateException("Profil non sauvegardé, impossible de récupérer l'id.");
        }
        return id;
    }

    /**
     * Cette méthode est utilisée pour définir l'identifiant du profil.
     *
     * @param id l'identifiant du profil.
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Cette méthode est utilisée pour obtenir le nom de la colonne de l'identifiant.
     *
     * @return le nom de la colonne de l'identifiant.
     */
    @Override
    public String getColonneId() {
        return "id_profil";
    }

    /**
     * Cette méthode est utilisée pour sauvegarder le profil.
     */
    @Override
    public void sauvegarder() {
        stockage.sauvegarder(this);
    }

    /**
     * Cette méthode est utilisée pour recharger les statistiques du profil.
     */
    private void rechargerStats() {
        statistiques = stockage.charger(Statistique.class, new EqFilter("id_profil", id));
    }

    /**
     * Cette méthode est utilisée pour obtenir les statistiques du profil.
     *
     * @return les statistiques du profil.
     */
    public List<Statistique> getStatistiques() {
        return statistiques;
    }
}