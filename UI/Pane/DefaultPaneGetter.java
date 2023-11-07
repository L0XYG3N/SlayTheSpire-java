package UI.Pane;

import UI.MainFrame;

import java.awt.Dimension;

import javax.swing.JLayeredPane;

public class DefaultPaneGetter {
    public static JLayeredPane getNewJLayeredPane() {
        //  !!필요없어질 예정!!

        Dimension size = new Dimension(MainFrame.SCREEN_WIDTH, MainFrame.SCREEN_HEIGHT);
        JLayeredPane layeredPane = new JLayeredPane();
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
        return layeredPane;
    }
}
