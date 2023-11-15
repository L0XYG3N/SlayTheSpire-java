package UI.Pane;

import java.awt.Dimension;

import javax.swing.*;

import UI.MainFrame;

public class MapPane extends JLayeredPane{
    public MapPane() {
        Dimension size = new Dimension(MainFrame.SCREEN_WIDTH, MainFrame.SCREEN_HEIGHT);
        setLocation(0,0);
        setSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        setPreferredSize(size);
        setLayout(null);
        setEnabled(true);
        setVisible(true);
        setOpaque(true);
    }
}
