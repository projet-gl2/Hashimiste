package fr.hashimiste.gui;

import javax.swing.*;
import java.awt.*;

public class Test extends JFrame {



    public Test()
    {
        PreviewComponent p = new PreviewComponent(Color.BLACK);
        PreviewComponent p2 = new PreviewComponent(Color.CYAN);
        PreviewComponent p3 = new PreviewComponent(Color.GRAY);
        PreviewComponent p4 = new PreviewComponent(Color.MAGENTA);

        JButton b = new JButton();
        JButton b2 = new JButton();
        JButton b8 = new JButton();
        JButton b3 = new JButton();
        JButton b4 = new JButton();
        JButton b5 = new JButton();
        JButton b6 = new JButton();
        JButton b7 = new JButton();



        this.setSize(400,400);
        this.setLayout(new GridLayout(2,2));
        setLocationRelativeTo(null);

        add(p2);
        add(p);
        add(p3);
        add(p4);
        /*add(b4);
        add(b6);
        add(b7);*/
        paint(getGraphics());
        setVisible(true);
        //pack();

    }

    public static void main(String[] args) {
        new Test();
    }

}
