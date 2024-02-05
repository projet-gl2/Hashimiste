/**
 * La classe Jeu correspond au jeu lorsqu'on est face à l'écran de jeu avec les boutons ainsi que la grille, il y divers boutons (retour arrière, poser un checkpoint, aide, etc)
 **/
public class Jeu {
    private ArrayList<Boutons> listeBoutons = new ArrayList<Boutons>();
    // grille;
    // grilleComplete;

    /**
     * Méthode de création de la fenetre de jeu avec la grille ainsi que la liste de boutons chacun correpondant à une fonction prédéfinit
     **/
    public Jeu(/*la grille de jeu, la grile solution*/){
        //Implémentation des boutons
        //Bouton retour en arrière
        //Bouton poser un checkpoint
        //Bouton charger le checkpoint
        //Bouton vérifier si grille correcte
        //Bouton aide
        //this.grille = /* la grille de jeu */;
        //this.grilleComplete = /* la grille solution */

    }

    /**
     * Méthode qui permet de retourner à la grille en annulant de coup précédent
     **/
    public void retourArriere(){
        //Si sauvegarde existante alors chargement sauvegarde (Bouton cliquable)

        //Sinon ne rien faire (Bouton non cliquable)
    }

    /**
     * Méthode qui permet à l'utilisateur de poser un point de sauvegarde rapide
     **/
    public void poserCheckpoint(){
        //Si sauvegarde existe alors on supprime l'ancienne

        //On créer un nouveau checkpoint
    }

    /**
     * Méthode qui permet de revenir à un point de sauvegarde rapide que l'utilisateur pourra créer
     **/
    public void chargerCheckpoint(){
        //Si checkpoint existe alors on charge (Bouton cliquable)

        //Sinon checkpoint n'existe pas (Bouton non cliquable)
    }

    /**
     * Méthode qui vérifie île par île si la grille est bonne
     **/
    public void verifierGrille(){
        //Appel de la fonction de map

    }

}