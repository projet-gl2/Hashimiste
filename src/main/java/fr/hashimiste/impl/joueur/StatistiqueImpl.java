package fr.hashimiste.impl.joueur;

import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.data.sql.Identifiable;
import fr.hashimiste.core.joueur.Profil;
import fr.hashimiste.core.joueur.Statistique;
import fr.hashimiste.core.joueur.StatistiqueKey;
import fr.hashimiste.impl.data.sql.SQLStockage;
import fr.hashimiste.impl.data.sql.filter.EqFilter;

/**
 * Cette classe représente une statistique de joueur.
 * Elle implémente les interfaces Statistique et Identifiable.__UNSAFE__.
 */
public class StatistiqueImpl implements Statistique, Identifiable.UNSAFE {

    private final StatistiqueKey key;
    private final Profil profil;
    private final int entityId;
    private int valeur;
    private int id;

    /**
     * Constructeur de la classe StatistiqueImpl.
     *
     * @param key    la clé de la statistique.
     * @param profil le profil du joueur.
     * @param valeur la valeur de la statistique.
     */
    public StatistiqueImpl(StatistiqueKey key, Profil profil, int valeur) {
        this(-1, key, profil, valeur);
    }

    /**
     * Constructeur de la classe StatistiqueImpl.
     *
     * @param id     l'identifiant de la statistique.
     * @param key    la clé de la statistique.
     * @param profil le profil du joueur.
     * @param valeur la valeur de la statistique.
     */
    public StatistiqueImpl(int id, StatistiqueKey key, Profil profil, int valeur) {
        this(id, key, profil, -1, valeur);
    }

    /**
     * Constructeur de la classe StatistiqueImpl.
     *
     * @param key      la clé de la statistique.
     * @param profil   le profil du joueur.
     * @param entityId l'identifiant de l'entité.
     * @param valeur   la valeur de la statistique.
     */
    public StatistiqueImpl(StatistiqueKey key, Profil profil, int entityId, int valeur) {
        this(-1, key, profil, entityId, valeur);
    }

    /**
     * Constructeur de la classe StatistiqueImpl.
     *
     * @param stockage le stockage des données.
     * @param id       l'identifiant de la statistique.
     * @param key      la clé de la statistique.
     * @param profilId l'identifiant du profil du joueur.
     * @param entityId l'identifiant de l'entité.
     * @param valeur   la valeur de la statistique.
     */
    public StatistiqueImpl(Stockage stockage, int id, StatistiqueKey key, int profilId, int entityId, int valeur) {
        this(id,
                key,
                ((SQLStockage) stockage).getDepuisCache(Profil.class, p -> p.getId() == profilId)
                        .orElseGet(() -> stockage.get(Profil.class, new EqFilter("id_profil", profilId))),
                entityId,
                valeur);
    }

    /**
     * Constructeur de la classe StatistiqueImpl.
     *
     * @param id       l'identifiant de la statistique.
     * @param key      la clé de la statistique.
     * @param profil   le profil du joueur.
     * @param entityId l'identifiant de l'entité.
     * @param valeur   la valeur de la statistique.
     */
    public StatistiqueImpl(int id, StatistiqueKey key, Profil profil, int entityId, int valeur) {
        this.id = id;
        this.key = key;
        this.profil = profil;
        this.entityId = entityId;
        this.valeur = valeur;
    }

    /**
     * Cette méthode est utilisée pour obtenir la clé de la statistique.
     *
     * @return la clé de la statistique.
     */
    @Override
    public StatistiqueKey getKey() {
        return key;
    }

    /**
     * Cette méthode est utilisée pour obtenir le profil du joueur.
     *
     * @return le profil du joueur.
     */
    @Override
    public Profil getProfil() {
        return profil;
    }

    /**
     * Cette méthode est utilisée pour obtenir la valeur de la statistique.
     *
     * @return la valeur de la statistique.
     */
    @Override
    public int getValeur() {
        return valeur;
    }

    /**
     * Cette méthode est utilisée pour définir la valeur de la statistique.
     *
     * @param valeur la nouvelle valeur de la statistique.
     */
    @Override
    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    /**
     * Cette méthode est utilisée pour incrémenter la valeur de la statistique.
     */
    @Override
    public void incrementer() {
        this.valeur++;
    }

    /**
     * Cette méthode est utilisée pour décrémenter la valeur de la statistique.
     */
    @Override
    public void decrementer() {
        this.valeur--;
    }

    /**
     * Cette méthode est utilisée pour réinitialiser la valeur de la statistique.
     */
    @Override
    public void reset() {
        this.valeur = 0;
    }

    /**
     * Cette méthode est utilisée pour obtenir l'identifiant de l'entité.
     *
     * @return l'identifiant de l'entité.
     */
    @Override
    public int getEntityId() {
        return entityId;
    }

    /**
     * Cette méthode est utilisée pour obtenir une représentation sous forme de chaîne de caractères de la statistique.
     *
     * @return une représentation sous forme de chaîne de caractères de la statistique.
     */
    @Override
    public String toString() {
        return "StatistiqueImpl{" +
                "id=" + id +
                ", key=" + key +
                ", profil(id)=" + profil.getId() +
                (entityId == -1 ? "" : ", entityId=" + entityId) +
                ", valeur=" + valeur +
                '}';
    }

    /**
     * Cette méthode est utilisée pour obtenir l'identifiant de la statistique.
     *
     * @return l'identifiant de la statistique.
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Cette méthode est utilisée pour définir l'identifiant de la statistique.
     *
     * @param id l'identifiant de la statistique.
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
        return "id_stat";
    }
}