package UI.Pane;

import java.awt.Dimension;

import javax.swing.*;

import UI.MainFrame;
import UI.Listener.MapPaneListener;
import Game.*;

public class MapPane extends JLayeredPane{

    private GameMap gameMap;

    //선 그리기용 JPanel, paintComponent가 JLayeredPane에 없음
    public MapDrawingPanel drawingPanel;

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

        gameMap = Game.getInstance().gameMap;

        MapPaneListener listener = new MapPaneListener(this);
        // addMouseListener(listener);
        // addMouseMotionListener(listener);
        addMouseWheelListener(listener);
        
        drawingPanel = new MapDrawingPanel(gameMap);
        add(drawingPanel);
    }

    public void resetMap() {
        gameMap = Game.getInstance().gameMap;
        remove(drawingPanel);
        drawingPanel = new MapDrawingPanel(gameMap);
        add(drawingPanel);
    }

     
}

