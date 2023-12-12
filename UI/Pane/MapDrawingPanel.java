package UI.Pane;

import java.awt.BasicStroke;  // 추가: BasicStroke를 import
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;  // 추가: Graphics2D를 import
import java.util.ArrayList;

import javax.swing.*;

import UI.MainFrame;
import UI.MapNodeButton;
import Game.*;

import java.awt.Image;

public class MapDrawingPanel extends JPanel {
    GameMap gameMap;
    Graphics g;
    private Image backgroundImage;  
    
    public double scrollMeasure = 0.0;

    // node draw size
    public final int nodeX = 70;
    public final int nodeY = 30;

    ArrayList<MapNodeButton> btnList;

    public MapDrawingPanel(GameMap gameMap) {
        Dimension size = new Dimension(MainFrame.SCREEN_WIDTH, MainFrame.SCREEN_HEIGHT);
        this.gameMap = gameMap;
        setSize(size);
        setLayout(null);
        btnList = new ArrayList<MapNodeButton>();
        
        // 배경 이미지 로드 추가
        backgroundImage = new ImageIcon("resource/Map.jpg").getImage();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g = g;
        
        // 배경 이미지 그리기 추가
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        
        drawMap();
    }

    public void drawMap() {
        for (JButton btn : btnList) {
            remove(btn);
        }
        btnList.clear();

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 7; j++) {
                if (gameMap.map[i][j] == null)
                    continue;

                int xPadding = 130;
                int yPadding = 160;
                
                int xOffset = MainFrame.SCREEN_WIDTH / 2 - (xPadding * 6) / 2;
                int yOffset = (int) (scrollMeasure * (yPadding * 17 - MainFrame.SCREEN_HEIGHT));

                int x = j * xPadding + xOffset;
                int y = 670 - (i * yPadding) + yOffset;

                MapNodeButton b = new MapNodeButton(i, gameMap.map[i][j]);
                b.setFont(new Font("gothic", Font.PLAIN, 11));
                
                b.setBounds(x - nodeX / 2, y - nodeY / 2, nodeX, nodeY);

                if (gameMap.currentFloor == 0 && i == 0) {
                    b.setEnabled(true);
                }

                btnList.add(b);
                add(b);

                // 두꺼운 선 그리기
                Graphics2D g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(2));  // 굵기를 조절하려면 값을 조정하세요

                if (i == 14) {
                    // 보스
                    g.drawLine(x, y, 3 * xPadding + xOffset, 670 - (int) (15.5 * yPadding) + yOffset);

                    continue;
                }

                // 선 그리기
                for (int k = 0; k < gameMap.map[i][j].nextX.size(); k++) {
                    g.drawLine(x, y, gameMap.map[i][j].nextX.get(k) * xPadding + xOffset,
                            670 - ((i + 1) * yPadding) + yOffset);
                }

                // 스트로크를 기본 값으로 재설정
                g2d.setStroke(new BasicStroke());
            }
        }

        MapNodeButton boss = new MapNodeButton(15, gameMap.map[15][3]);
        int bossX = 3 * 130 + MainFrame.SCREEN_WIDTH / 2 - (130 * 6) / 2;
        int bossY = 670 - (int) (15.5 * 160) + (int) (scrollMeasure * (160 * 17 - MainFrame.SCREEN_HEIGHT));

        boss.setBounds(bossX - nodeX / 2, bossY - nodeY / 2, nodeX, nodeY);
        btnList.add(boss);

        add(boss);
    }
}