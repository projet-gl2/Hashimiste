package fr.hashimiste.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;

import java.awt.event.MouseAdapter;

import fr.hashimiste.gui.*;

import java.util.function.Supplier;
import java.util.ArrayList;
import java.util.regex.Pattern;

/** Classe pour représenter le Premier Menu du jeu (gestion/connexion/création de profil  )
  @author Teissier Antoine 
  @version 1
*/
public class MenuProfilCreation extends JFrame {
    public final static int  Max = 10;

    private static ArrayList<Profil> listeProfil=new ArrayList<Profil>();
    private static ArrayList<String> listeNom=new ArrayList<String>();
    /** Constructeur de la classe MenuProfilCreation permet de créer une interface graphique. 
     * 
    */
    private JPanel jpListeProfil=new JPanel();
    private JPanel jp = new JPanel();

    public MenuProfilCreation(){
        this.setTitle("Hashimiste");
        this.setIconImage(new ImageIcon("../resources/images/iconTransparent.png").getImage());

        this.setSize(500, 300);
        this.setMinimumSize(new java.awt.Dimension(1000, 720));
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        

        jp.setBackground(Couleur.COULEUR_FOND);
        jpListeProfil.setBackground(Couleur.COULEUR_TRANSPARENT);
        jp.setLayout(new GridBagLayout());
        jpListeProfil.setLayout(new BoxLayout(jpListeProfil, BoxLayout.X_AXIS));

        affichageListe();
        this.setVisible(true) ;
    }

    /**Permet de créer un bouton pour la sélection des  profils. 
     * @return Le bouton créer 
     */
    public JButton creerButtonProfil(String chaine, Runnable ajout){
        JButton but =new JButton(chaine);
        but.setBackground(Couleur.COULEUR_BOUTON);
        but.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        but.setAlignmentY(JLabel.CENTER_ALIGNMENT);
        
        but.addActionListener(
            (e)->{ ((JButton)e.getSource()).setBackground(Couleur.COULEUR_BOUTON);
                if (ajout != null) {
                    ajout.run();
                }
            }
        );
        but.addMouseListener(new MouseAdapter(){
                public void	mousePressed(MouseEvent e){
                     ((JButton)e.getSource()).setBackground(Couleur.COULEUR_BOUTON);
                }
                public void mouseEntered(MouseEvent e){ 
                    ((JButton)e.getSource()).setBackground(Couleur.COULEUR_BOUTON);
                    
                }
                public void mouseExited(MouseEvent e){ 
                    ((JButton)e.getSource()).setBackground(Couleur.COULEUR_BOUTON);
                }
            });
        return but;
    }

    /** Verifie si un String passer en parametre  
     *  @return un boolean 
     */
   private static boolean isBlank(String s) {
        return s.isEmpty() || s.chars().allMatch(c -> c == ' ');
   }

    /** Verifie si un nom passer en parametre existe pas et respacte un nombre de caractere 
     *  @return un boolean 
     */
    private Boolean verifFormatNom(String nom){
        if(nom!= null){
            if((nom.length() > Max || isBlank(nom))){
                return false;
            }
            for(String elem : listeNom){
                if(nom.equals(elem)){
                    return false;
                }
            }
        }
        return true;
    }

    /**Permet de créer un Profil dans la sélection des profils et l ajoute a la liste . 
     * 
     */
    private void creerProfil(){
        JOptionPane jOp =new JOptionPane();
        String nom=jOp.showInputDialog(this,"Nom du profil : ");
        boolean check = verifFormatNom(nom);
        while(!check){
            nom=jOp.showInputDialog(this,"Erreur lors de la vérification du nom !\nNom du profil : ");
            check = verifFormatNom(nom);
        }
        if(nom != null){
            listeNom.add(nom);
            listeProfil.add(new Profil(nom));
            affichageListe();
        }
        
    }


    /**Met à jour l'affichage avec les nouveaux profils.
     * 
     */
    private void affichageListe(){
        jp.remove(jpListeProfil);
        jpListeProfil.removeAll();
        if(!listeProfil.isEmpty()){
            listeProfil.forEach((p)-> {jpListeProfil.add(creerButtonProfil(p.nom,null));
            jpListeProfil.add(Box.createHorizontalStrut(10));
            });
        }
        jpListeProfil.add(creerButtonProfil("+", () -> creerProfil()));
        jp.add(jpListeProfil);
        this.add(jp);
        this.pack();
        this.repaint();
    }
   public static void main(String[] args){
    MenuProfilCreation ecran=new MenuProfilCreation();
   }
}
