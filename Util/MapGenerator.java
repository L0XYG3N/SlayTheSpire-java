package Util;


public class MapGenerator {
    public enum MapLocation {
        ENEMY, REST, TREASURE, MERCHANT, UNKNOWN, ELITE, BOSS
    };

    public static MapNode[][] generate() {
        MapNode[][] tempMap = new MapNode[16][7];
        for (int iter = 0; iter < 6; iter++) {

            int currentPosition = (int) (Math.random() * 7);

            for (int floor = 0; floor < 15; floor++) {

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

                if (tempMap[floor][currentPosition] == null) {
                    tempMap[floor][currentPosition] = new MapNode(tempLocation);
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

                
                tempMap[floor][currentPosition].mapLocation = tempLocation;
                if(floor == 14) {
                    tempMap[floor][currentPosition].nextX.add(3);
                }else {
                    tempMap[floor][currentPosition].nextX.add(nextPosition);
                }
                currentPosition = nextPosition;
            }
        }
        tempMap[15][3] = new MapNode(MapLocation.BOSS);
        return tempMap;
    }
}