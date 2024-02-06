package fr.hashimiste.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Classe pour l'affichage du menu des techniques
 * 
 * @author Henzo
 * @version 16.0.2
 */

public class MenuTechnique extends JFrame implements ActionListener{
    private JPanel jp = new JPanel();
    private JPanel jp2 = new JPanel();
    private DefaultListModel<String> dlm = new DefaultListModel<>();
    private JList<String> jl = new JList<>();
    private JButton butMenu = new JButton("Menu");

    private JLabel description = new JLabel();

    private ArrayList<String> listDesc = new ArrayList<>();

    private int tailleMinX = 950;
    private int tailleMinY = 400;
    private int tailleMaxX = 1920;
    private int tailleMaxY = 1080;

    /**
     * Constructeur pour créer tous les composants de la fenêtre
     */
    public MenuTechnique(){
        this.setTitle("Hashimiste");
        this.setIconImages(getIconImages());

        //Taille minimum pour la fenêtre
        this.setMinimumSize(new Dimension(tailleMinX, tailleMinY));
        //Preferred size pour la fenêtre
        this.setPreferredSize(new Dimension(tailleMinX, tailleMinY));
        //Taille maximum pour la fenêtre
        this.setMaximumSize(new Dimension(tailleMaxX, tailleMaxY));

        //Bloquer le redimensionnement de la fenêtre
        this.setResizable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));

        //Ajout d'un ActionListener pour appeler la méthode qui ferme cette page et ouvre la page du menu
        butMenu.addActionListener((e) -> pageMenu());

        //Taille minimum des composants
        butMenu.setMinimumSize(new Dimension(30, 20));
        jl.setMinimumSize(new Dimension(200, 200));
        description.setMinimumSize(jl.getMinimumSize());

        //Preferred size des composants
        butMenu.setPreferredSize(new Dimension(30, 20));
        jl.setPreferredSize(new Dimension(200, 200));
        description.setPreferredSize(jl.getPreferredSize());

        //Taille maximum des composants
        butMenu.setMaximumSize(new Dimension(80, 20));
        jl.setMaximumSize(new Dimension(300, 200));
        description.setMaximumSize(jl.getMaximumSize());

        butMenu.setAlignmentX(JButton.LEFT_ALIGNMENT);
        description.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        butMenu.setBackground(new Color(160, 158, 188));
        description.setBackground(Color.WHITE);

        butMenu.setForeground(new Color(251, 250, 242));

        butMenu.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e){
                butMenu.setBackground(new Color(251, 250, 242));
                butMenu.setForeground(new Color(160, 158, 188));
            }

            public void mouseExited(MouseEvent e){
                butMenu.setBackground(new Color(160, 158, 188));
                butMenu.setForeground(new Color(251, 250, 242));
            }
        });

        jp.add(butMenu);
        //jp.add(Box.createVerticalStrut(10));

        jp2.setLayout(new BoxLayout(jp2, BoxLayout.X_AXIS));
        jp2.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        //Ajout des descriptions dans la liste de descriptions des techniques
        listDesc.add("Si une île a un chiffre pair et son nombre de voisins correspond à n/2 alors elle est reliée 2 fois à chacun de ses voisins.");
        listDesc.add("Si une île a le chiffre 1 ou 2 et qu’elle possède seulement 1 voisin alors elle sera forcément reliée à ce voisin, cela fonctionne seulement le chiffre 1 et 2 puisque dans le hashi il peut seulement exister maximum 2 ponts entre 2 îles.");
        listDesc.add("Si une île a un chiffre impair > 1 elle à au minimum n%2+1 voisins. Si elle a exactement ce nombre de voisins alors elle est obligatoirement relié au moins une fois à chacun de ses voisins");
        listDesc.add("Si parmi ses voisins il y a une île avec le chiffre 1, alors l'île sera relié 2 fois à tous ses autres voisins.");
        listDesc.add("Voici une liste du nombre maximum de voisins 1 que peut avoir une ile : <br>" + //
                        "(chiffre | nb voisin 1 max) <br>" + //
                        "1 = 0, 2 = 1, 3 = 2, 4 = 3, 5 = 3, 6 = 2, 7 = 1, 8 = 0");
        listDesc.add("Si une ile à plusieurs voisins et que l’un deux implique que l’ile soit séparer du reste de l’ensemble alors ce n’est pas la bonne solution.");
        listDesc.add("Parfois le plus gros ensemble d’îles connectés peut être amené à devenir une structure isolée, pour éviter cela on préfèrera connecter la structure à une île ouverte.");
        listDesc.add("Si la création d’un pont tends à insoler une partie de la structure principal il faut se raviser et effectuer le chemin inverse île par île pour vérifier s’il n’existe pas un meilleur chemin.");

        dlm.addElement("Technique 1");
        dlm.addElement("Technique 2");
        dlm.addElement("Technique 3");
        dlm.addElement("Technique 3.5");
        dlm.addElement("Technique 4");
        dlm.addElement("Technique 5");
        dlm.addElement("Technique 6");
        dlm.addElement("Technique 7");

        jl.setModel(dlm);
        jl.setOpaque(true);

        //Ajout d'un MouseListener à la liste pour changer la description de la technique
        jl.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                description.setOpaque(true);
                description.setText("<html>"+listDesc.get(jl.getSelectedIndex())+"</html>");
            }
        });

        jp2.add(jl);
        jp2.add(Box.createHorizontalStrut(20));
        jp2.add(description);
        jp.add(jp2);

        this.setContentPane(jp);

        this.pack();
        this.setVisible(true);
    }

    /**
     * Méthode pour afficher la page du menu et détruire la page des techniques
     */
    public void pageMenu(){
        Menu m = new Menu();
        this.dispose();
    }

    /**
     * Exception levée pour les actions effectués par des composants
     * @param e Action qui vient d'être effectuée
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    /**
     * Méthode main pour lancer la fenêtre (individuellement)
     * @param args Arguments du main (entrés en ligne de commande)
     */
    public static void main(String args[]){
        MenuTechnique t = new MenuTechnique();
    }
}
