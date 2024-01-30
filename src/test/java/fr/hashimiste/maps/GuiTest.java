package fr.hashimiste.maps;

import javax.swing.*;
import java.awt.*;

public class GuiTest {
    public static void main(String[] args) {
        Ile ile1 = new Ile(100, 100, 3);
        Ile ile2 = new Ile(200, 100, 2);
        Ile ile3 = new Ile(100, 200, 1);

        Pont pont = ile1.creerPont(ile2, 1);
        Pont pont2 = ile1.creerPont(ile3, 1);

        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                ile1.paint(g);
                ile2.paint(g);
                ile3.paint(g);
                pont.paint(g);
                pont2.paint(g);
            }
        });
        frame.setVisible(true);
    }
}
