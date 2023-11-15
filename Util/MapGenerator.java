package Util;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class MapNode {
    public ArrayList<Integer> nextX = new ArrayList<Integer>();
    public MapGenerator.MapLocation mapLocation;
}

public class MapGenerator {
    
    public enum MapLocation {
        ENEMY, REST, TREASURE, MERCHANT, UNKNOWN, ELITE, BOSS
    };

    public static MapNode[][] generate() {
        MapNode[][] tempMap = new MapNode[15][7];
        for (int iter = 0; iter < 6; iter++) {

            int currentPosition = (int) (Math.random() * 7);

            for (int floor = 0; floor < 15; floor++) {

                if (tempMap[floor][currentPosition] == null) {
                    tempMap[floor][currentPosition] = new MapNode();
                }

                int nextDir;
                if(currentPosition == 0) {
                    nextDir = (int) (Math.random() * 2);
                } else if (currentPosition == 6) {
                    nextDir = (int) (Math.random() * 2) - 1;
                } else {
                    nextDir = (int) (Math.random() * 3) - 1;
                }

                int nextPosition = currentPosition + nextDir;

                if (currentPosition > 0) {
                    if (tempMap[floor][currentPosition - 1] != null) {
                        for (int i = 0; i < tempMap[floor][currentPosition - 1].nextX.size(); i++) {
                            if (tempMap[floor][currentPosition - 1].nextX.get(i) == currentPosition
                                    && nextPosition == currentPosition - 1) {
                                nextDir = (int) (Math.random() * 2);
                                nextPosition = currentPosition + nextDir;
                                if (nextPosition > 6)
                                    nextPosition = 6;
                            }
                        }
                    }
                }
                if (currentPosition < 6) {
                    if (tempMap[floor][currentPosition + 1] != null) {
                        for (int i = 0; i < tempMap[floor][currentPosition + 1].nextX.size(); i++) {
                            if (tempMap[floor][currentPosition + 1].nextX.get(i) == currentPosition
                                    && nextPosition == currentPosition + 1) {
                                nextPosition = currentPosition;
                                if (nextPosition < 0)
                                    nextPosition = 0;
                            }
                        }
                    }
                }

                double randomValue = Math.random();
                MapLocation tempLocation = MapLocation.UNKNOWN;

                if (randomValue < 0.05) {
                    tempLocation = MapLocation.MERCHANT;
                } else if (randomValue < 0.17) {
                    if (floor == 13)
                        tempLocation = MapLocation.UNKNOWN;
                    tempLocation = MapLocation.REST;
                } else if (randomValue < 0.39) {
                    tempLocation = MapLocation.UNKNOWN;
                } else if (randomValue < 0.47) {
                    tempLocation = MapLocation.ELITE;
                } else {
                    tempLocation = MapLocation.ENEMY;
                }
                if (floor == 0) {
                    tempLocation = MapLocation.ENEMY;
                } else if (floor == 8) {
                    tempLocation = MapLocation.TREASURE;
                } else if (floor == 14) {
                    tempLocation = MapLocation.REST;
                }
                tempMap[floor][currentPosition].mapLocation = tempLocation;
                tempMap[floor][currentPosition].nextX.add(nextPosition);
                currentPosition = nextPosition;
            }
        }
        return tempMap;
    }
}

class MyPanel extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        MapNode[][] map = MapGenerator.generate();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 7; j++) {
                if (map[i][j] == null)
                    continue;
                switch (map[i][j].mapLocation) {
                    case ENEMY:
                        g.setColor(Color.RED);
                        break;
                    case MERCHANT:
                        g.setColor(Color.CYAN);
                        break;
                    case UNKNOWN:
                        g.setColor(Color.GRAY);
                        break;
                    case REST:
                        g.setColor(Color.ORANGE);
                        break;
                    case ELITE:
                        g.setColor(Color.PINK);
                        break;
                    case TREASURE:
                        g.setColor(Color.YELLOW);
                        break;
                    case BOSS:
                        g.setColor(Color.BLUE);
                        break;
                }
                g.fillOval(j * 50 - 5 + 20, 670 - (i * 40) - 5, 10, 10);
                g.setColor(Color.BLACK);
                if (i == 14)
                    continue;
                for (int k = 0; k < map[i][j].nextX.size(); k++) {
                    g.drawLine(j * 50 + 20, 670 - (i * 40), map[i][j].nextX.get(k) * 50 + 20, 670 - ((i + 1) * 40));
                }

            }

        }
    }
}

