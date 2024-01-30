package fr.hashimiste.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ModeLibre extends JFrame implements MouseListener {

    // Variable de test
    PreviewComponent[] previewTab = new PreviewComponent[10];

    JPanel panelFacile = new JPanel();
    JPanel panelMoyen = new JPanel();
    JPanel panelDifficile = new JPanel();
    JPanel panelPreview = new JPanel();

    PreviewComponent preview = new PreviewComponent(Color.PINK);
    PreviewComponent preview2 = new PreviewComponent(Color.CYAN);
    private final int tailleMinX = 500;
    private final int tailleMinY = 300;
    private final int tailleMaxX = 1920;
    private final int tailleMaxY = 1080;

    JLabel facileLabel = new JLabel();
    JLabel moyenLabel = new JLabel();
    JLabel difficileLabel = new JLabel();
    JButton menuButton = new JButton();

    public ModeLibre()
    {
        this.setTitle("Hashimiste > Mode Libre");
        this.setIconImages(getIconImages());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(tailleMinX, tailleMinY);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        this.add(facileLabel);
        //test
        for(int i = 0; i <= 9; i++)
        {
            Color c = new Color((int)(Math.random() * 0x1000000));
            panelFacile.add(new PreviewComponent(c));
            panelMoyen.add(new PreviewComponent(c));
            panelDifficile.add(new PreviewComponent(c));
        }

        getContentPane().setBackground(Color.white);

        // Minimum size pour la fenêtre
        this.setMinimumSize(new java.awt.Dimension(tailleMinX, tailleMinY));
        // Preferred size pour la fenêtre
        this.setPreferredSize(new java.awt.Dimension(tailleMinX, tailleMinY));
        // Taille maximum pour la fenêtre
        this.setMaximumSize(new java.awt.Dimension(tailleMaxX, tailleMaxY));

        // Components
        facileLabel.setText("Facile");
        moyenLabel.setText("Moyen");
        difficileLabel.setText("Difficile");
        //facileLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        //facileLabel.setAlignmentY(JLabel.TOP);
        menuButton.setText("MENU");


        panelPreview.add(menuButton);
        //panelFacile.add(facileLabel);
        this.add(moyenLabel);
        this.add(difficileLabel);



        panelFacile.setBackground(Color.WHITE);
        panelMoyen.setBackground(Color.WHITE);
        panelDifficile.setBackground(Color.WHITE);
        panelPreview.setBackground(Color.WHITE);

        panelFacile.setLayout(new GridLayout(2,5));
        panelMoyen.setLayout(new GridLayout(2,5));
        panelDifficile.setLayout(new GridLayout(2,5));

        this.add(panelFacile);
        this.add(panelMoyen);
        this.add(panelDifficile);
        this.add(panelPreview);
        this.setMinimumSize(new Dimension(400,400));
        paint(getGraphics());
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new ModeLibre();
    }






    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void paint(Graphics g) {
        int h = getHeight()- getInsets().top-facileLabel.getHeight()*3;
        int w = getWidth()-(getWidth()/3);
        panelFacile.setBounds(0,facileLabel.getHeight(), w, h/3);
        panelMoyen.setBounds(0,h/3+facileLabel.getHeight()*2, w, h/3);
        panelDifficile.setBounds(0,(h/3)*2+facileLabel.getHeight()*3, w, h/3);
        panelPreview.setBounds(w,0,getWidth()/3, getHeight());
        menuButton.setBounds(0,0,getWidth()/3, h/10);
        System.out.println(facileLabel.getHeight());
        facileLabel.setBounds(10,0,100,20);
        moyenLabel.setBounds(10, h/3+facileLabel.getHeight(), 100,20);
        difficileLabel.setBounds(10,(h/3)*2+facileLabel.getHeight()*2,100,20);
        super.paint(g);
    }
}
