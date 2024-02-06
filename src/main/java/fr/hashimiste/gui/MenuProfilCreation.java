package fr.hashimiste.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.*;
import java.awt.event.MouseAdapter;
import fr.hashimiste.gui.*;
import java.util.function.Supplier;
import java.util.ArrayList;

/** Classe pour représenter le Premier Menu du jeu (gestion/connexion/création de profil  )
  @author Teissier Antoine 
  @version 1
*/
public class MenuProfilCreation extends JFrame {
   
    /** Constructeur de la classe MenuProfilCreation permet de créer une interface graphique. 
     * 
    */
   private static ArrayList<Profil> listeProfil=new ArrayList<Profil>();
   
    public MenuProfilCreation(){
        this.setTitle("Hashimiste");
        this.setIconImage(new ImageIcon("../resources/images/iconTransparent.png").getImage());

        //try {
        //this.setIconImage(javax.imageio.ImageIO.read(getClass().getResource("resources/images/iconTransparent.png")));
        //} catch(java.io.IOException e) {
        //    e.printStackTrace();
        //}

        this.setSize(500, 300);
        this.setMinimumSize(new java.awt.Dimension(1000, 720));
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        JPanel jpListeProfil=new JPanel();
        JPanel jp = new JPanel();

        jp.setBackground(Couleur.getCouleurFond());
        jpListeProfil.setBackground(Couleur.couleurTransparent);
        jp.setLayout(new GridBagLayout());
        jpListeProfil.setLayout(new BoxLayout(jpListeProfil, BoxLayout.X_AXIS));

        // Liste profile
        // Liste str nom
        // For
            // Verifier str nom
            // Creer boutton
            // Ajouter str nom
        // Rof
        if(!listeProfil.isEmpty()){
            listeProfil.forEach((p)-> {jpListeProfil.add(creerButtonProfil(p.nom,null));
            jpListeProfil.add(Box.createHorizontalStrut(10));
            });
        }

        jpListeProfil.add(creerButtonProfil("+", () -> creerProfil()));
        jp.add(jpListeProfil);
        this.add(jp);
        this.pack();
        this.setVisible(true) ;
    }

    /**Permet de créer un bouton pour la sélection des  profils. 
     * @return Le bouton créer 
     */
    public JButton creerButtonProfil(String chaine, Supplier<Profil> ajout){
        JButton but =new JButton(chaine);
        but.setBackground(Couleur.getCouleurBouton());
        but.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        but.setAlignmentY(JLabel.CENTER_ALIGNMENT);
        
        but.addActionListener(
            (e)->{ ((JButton)e.getSource()).setBackground(Color.white);
            if (ajout != null) {
                Profil nouv=ajout.get();
                listeProfil.add(nouv);
            }
            MenuProfilCreation ecran=new MenuProfilCreation();
            this.dispose();
            }
        );
        but.addMouseListener(new MouseAdapter(){
                public void	mousePressed(MouseEvent e){
                     ((JButton)e.getSource()).setBackground(Color.white);
                }
                public void mouseEntered(MouseEvent e){ 
                    ((JButton)e.getSource()).setBackground(Color.white);
                    
                }
                public void mouseExited(MouseEvent e){ 
                    ((JButton)e.getSource()).setBackground(Couleur.getCouleurBouton());
                }
            });
        return but;
    }

    /**Permet de créer un Profilla sélection des  profils. 
     * @return Le profil creer;
     */
    private Profil creerProfil(){
        JOptionPane jOp =new JOptionPane();
        String nom=jOp.showInputDialog(this,"Nom du profil : ");
        Profil tmp=new Profil(nom);
        
        return tmp;
    }
   public static void main(String[] args){
    MenuProfilCreation ecran=new MenuProfilCreation();
   }
}


