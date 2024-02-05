import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.*;
import java.awt.event.MouseAdapter;
import java.util.function.Consumer;

/** Classe pour représenter le Premier Menu du jeu (gestion/connexion/création de profil  )
  @author Teissier Antoine 
  @version 1
*/
public class MenuProfilCreation extends JFrame {
    public Color blue=new Color(0xA09EBC);
    /** Constructeur de la classe MenuProfilCreation permet de créer une interface graphique. 
     * 
    */
    public MenuProfilCreation(){
        super("Hashimiste");
        this.setIconImages(getIconImages());
        this.setSize(500, 300);
        this.setMinimumSize(new java.awt.Dimension(1500, 720));
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        JPanel jpListeProfil=new JPanel();
        JPanel jp = new JPanel();
        
        jp.setBackground(Color.white);
        jpListeProfil.setBackground(Color.white);
        jp.setLayout(new GridBagLayout());
        jpListeProfil.setLayout(new BoxLayout(jpListeProfil, BoxLayout.X_AXIS));
        
        jpListeProfil.add(creerButtonProfil("Antoine",null,null));
        jpListeProfil.add(Box.createHorizontalStrut(10));
        
        jpListeProfil.add(creerButtonProfil("Ajouter",null,null));
        jp.add(jpListeProfil);
        this.add(jp);
        this.pack();
        this.setVisible(true) ;
    }

    /**Permet de créer un bouton pour la sélection des  profils. 
     * @return Le bouton créer 
     */
    public JButton creerButtonProfil(String chaine, Consumer<Void> ajout, Consumer<Void> charger){
        JButton but =new JButton(chaine);
        but.setBackground(blue);
        but.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        but.setAlignmentY(JLabel.CENTER_ALIGNMENT);
        but.addActionListener(
            (e)->{ ((JButton)e.getSource()).setBackground(Color.white);
            JOptionPane.showMessageDialog(null, "cliquer");
            MenuProfilCreation ecran=new MenuProfilCreation();
            if (ajout != null) {
                ajout.accept(null);
            }
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
                    ((JButton)e.getSource()).setBackground(blue);
                }
            });
        return but;
    }
   public static void main(String[] args){
    MenuProfilCreation ecran=new MenuProfilCreation();
   }
}


