public class Jeu {
    ArrayList<Boutons> listeBoutons = new ArrayList<Boutons>();
    /* */ grille;
    /* */ grilleComplete;

    public Jeu(/*la grille de jeu*/, /* la grile solution*/){
        //Implémentation des boutons
        //Bouton retour en arrière
        //Bouton poser un checkpoint
        //Bouton charger le checkpoint
        //Bouton vérifier si grille correcte
        //Bouton aide
        this.grille = /* la grille de jeu */;
        this.grilleComplete = /* la grille solution */

    }

    public void retourArriere(){
        /* Si sauvegarde existante alors chargement sauvegarde (Bouton cliquable) */

        /* Sinon ne rien faire (Bouton non cliquable) */
    }

    public void poserCheckpoint(){
        /* Si sauvegarde existe alors on supprime l'ancienne */

        /* On créer un nouveau checkpoint*/
    }

    public void chargerCheckpoint(){
        /* Si checkpoint existe alors on charge (Bouton cliquable)*/
        /* Sinon checkpoint n'existe pas (Bouton non cliquable) */
    }

    /* Méthode qui vérifie île par île si la grille est bonne*/
    public void verifierGrille(){
        /* Appel de la fonction de map */

    }

}