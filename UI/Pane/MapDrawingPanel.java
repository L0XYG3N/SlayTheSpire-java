package UI.Pane;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.*;

import UI.MainFrame;
import UI.MapNodeButton;
import Game.*;



public class MapDrawingPanel extends JPanel {
    GameMap gameMap;
    Graphics g;

    public double scrollMeasure = 0.0;

    
    //node draw size
    public final int nodeX = 55;
    public final int nodeY = 30;

    ArrayList<MapNodeButton> btnList;


    public MapDrawingPanel(GameMap gameMap) {
        Dimension size = new Dimension(MainFrame.SCREEN_WIDTH, MainFrame.SCREEN_HEIGHT);
        this.gameMap = gameMap;
        setSize(size);
        setLayout(null);
        btnList = new ArrayList<MapNodeButton>();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g = g;
        drawMap();
    }

    public void drawMap() {

        for(JButton btn : btnList) {
            remove(btn);
        }
        btnList.clear();

        for(int i = 0; i <15;i++) {
            for(int j = 0; j < 7;j++) {
                if (gameMap.map[i][j] == null)
                    continue;

                
                int xPadding = 130;
                int yPadding = 160;
                
                int xOffset = MainFrame.SCREEN_WIDTH/2 - (xPadding * 6)/2;
                int yOffset =(int)(scrollMeasure * (yPadding * 17 - MainFrame.SCREEN_HEIGHT));

                int x = j * xPadding + xOffset;
                int y = 670 - (i * yPadding) + yOffset;


                MapNodeButton b = new MapNodeButton(i, gameMap.map[i][j].mapLocation, gameMap.map[i][j]);
                b.setBounds(x - nodeX/2 , y - nodeY/2, nodeX, nodeY);

                btnList.add(b);
                add(b);

                g.fillOval(x - nodeX/2 , y - nodeY/2, nodeX, nodeY);
                
                if (i == 14) {
                    //보스한테 가는 선 그리기
                        g.drawLine(x, y, 3 * xPadding + xOffset, 670 - (int)(15.5 * yPadding)+yOffset);
                        
                    continue;
                }

                //선 그리기
                for (int k = 0; k < gameMap.map[i][j].nextX.size(); k++) {
                    g.drawLine(x,y, gameMap.map[i][j].nextX.get(k) * xPadding + xOffset, 670 - ((i + 1) * yPadding)+yOffset);
                }
            }
        }

    }
}


