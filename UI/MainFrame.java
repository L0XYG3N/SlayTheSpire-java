package UI;

import java.awt.Dimension;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;


public class MainFrame extends JFrame{
    public static final int SCREEN_WIDTH = 1366;
    public static final int SCREEN_HEIGHT = 768;
    public JLayeredPane layeredPane;

    public MainFrame() {
        setTitle("Game");

        Dimension size = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);

        setSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        setPreferredSize(size);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setVisible(true);

        layeredPane = new JLayeredPane();
        layeredPane.setLocation(0,0);
        layeredPane.setSize(size);
        layeredPane.setMaximumSize(size);
        layeredPane.setMinimumSize(size);
        layeredPane.setPreferredSize(size);
        layeredPane.setLayout(null);
        //layeredPane.setBackground(배경);
        layeredPane.setEnabled(true);
        layeredPane.setVisible(true);
        layeredPane.setOpaque(true);

        this.add(layeredPane);
    }

    public void addComponent(Component comp, int layer) {
        layeredPane.add(comp, layer);
    }
}
