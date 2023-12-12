package Util;

import java.util.HashSet;
import java.util.Set;

public class MapGenerator {
    public enum MapLocation {
        ENEMY, REST, TREASURE, MERCHANT, UNKNOWN, ELITE, BOSS
    }

    public static MapNode[][] generate() {
        MapNode[][] tempMap = new MapNode[16][7];
        Set<Integer> merchantFixedFloors = Set.of(3, 7, 11);

        for (int iter = 0; iter < 6; iter++) {
            int currentPosition = (int) (Math.random() * 7);
            Set<MapLocation> usedLocations = new HashSet<>();

            for (int floor = 0; floor < 15; floor++) {
                double randomValue = Math.random();
                MapLocation tempLocation;

             // 13층일 때는 ENEMY 또는 ELITE만 나오도록 설정
                if (floor == 13) {
                    if (randomValue < 0.7) {
                        tempLocation = MapLocation.ENEMY;
                    } else {
                        tempLocation = MapLocation.ELITE;
                    }
                } else if (floor == 12) {
                    // 12층에서는 ENEMY 또는 UNKNOWN만 나오도록 설정
                    if (randomValue < 0.5) {
                        tempLocation = MapLocation.ENEMY;
                    } else {
                        tempLocation = MapLocation.UNKNOWN;
                    }
                } else if (floor == 1) {
                    // 1층에서는 ENEMY 또는 UNKNOWN만 나오도록 설정
                    if (randomValue < 0.6) {
                        tempLocation = MapLocation.ENEMY;
                    } else {
                        tempLocation = MapLocation.UNKNOWN;
                    }
                } else if (floor == 4) {
                    // 4층에서는 ENEMY, ELITE, UNKNOWN 중 하나가 랜덤으로 나오도록 설정                  
                    if (randomValue < 0.33) {
                        tempLocation = MapLocation.ENEMY;
                    } else if (randomValue < 0.67) {
                        tempLocation = MapLocation.ELITE;
                    } else {
                        tempLocation = MapLocation.UNKNOWN;
                    }
                } else if (floor == 5) {
                    // 5층에서는 REST의 확률이 60%, ENEMY의 확률이 40%로 나오도록 설정
                    if (randomValue < 0.6) {
                        tempLocation = MapLocation.REST;
                    } else {
                        tempLocation = MapLocation.ENEMY;
                    }
                } else if (floor == 6) {
                    // 6층에서는 ENEMY, ELITE, UNKNOWN 중 하나가 랜덤으로 나오도록 설정
                    if (randomValue < 0.33) {
                        tempLocation = MapLocation.ENEMY;
                    } else if (randomValue < 0.67) {
                        tempLocation = MapLocation.ELITE;
                    } else {
                        tempLocation = MapLocation.UNKNOWN;
                    }
                } else if (floor == 8) {
                    // 8층에서는 ENEMY, ELITE, UNKNOWN 중 하나가 랜덤으로 나오도록 설정                  
                    if (randomValue < 0.33) {
                        tempLocation = MapLocation.ENEMY;
                    } else if (randomValue < 0.67) {
                        tempLocation = MapLocation.ELITE;
                    } else {
                        tempLocation = MapLocation.UNKNOWN;
                    }
                } else if (floor == 9) {
                    // 9층에서는 REST의 확률이 60%, ENEMY의 확률이 40%로 나오도록 설정
                    if (randomValue < 0.6) {
                        tempLocation = MapLocation.REST;
                    } else {
                        tempLocation = MapLocation.ENEMY;
                    }
                } else if (floor == 10) {
                    // 10층에서는 ENEMY, ELITE, UNKNOWN 중 하나가 랜덤으로 나오도록 설정
                    if (randomValue < 0.33) {
                        tempLocation = MapLocation.ENEMY;
                    } else if (randomValue < 0.67) {
                        tempLocation = MapLocation.ELITE;
                    } else {
                        tempLocation = MapLocation.UNKNOWN;
                    }
                }
                else {
                    // 나머지 부분은 이전과 동일하게 유지
                    if (merchantFixedFloors.contains(floor)) {
                        tempLocation = MapLocation.MERCHANT;
                    } else {
                        if (floor == 14) {
                            // floor == 14일 때 랜덤으로 REST 또는 MERCHANT 선택
                            tempLocation = (randomValue < 0.5) ? MapLocation.REST : MapLocation.MERCHANT;
                        } else {
                            if (randomValue < 0.45) {
                                tempLocation = MapLocation.ENEMY;
                            } else if (randomValue < 0.6) {
                                tempLocation = MapLocation.UNKNOWN;
                            } else if (randomValue < 0.75) {
                                tempLocation = MapLocation.ELITE;
                            } else {
                                tempLocation = MapLocation.REST;
                            }
                        }
                    }
                }


                // floor == 0 일때 ENEMY로 고정
                if (floor == 0) {
                    tempLocation = MapLocation.ENEMY;
                }

                if (tempMap[floor][currentPosition] == null) {
                    tempMap[floor][currentPosition] = new MapNode(tempLocation);
                }

                int nextDir;
                if (currentPosition == 0) {
                    nextDir = (int) (Math.random() * 2);
                } else if (currentPosition == 6) {
                    nextDir = (int) (Math.random() * 2) - 1;
                } else {
                    nextDir = (int) (Math.random() * 3) - 1;
                }

                int nextPosition = currentPosition + nextDir;

                // 중복 확인 및 처리
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
                if (floor == 14) {
                    tempMap[floor][currentPosition].nextX.add(3);
                } else {
                    tempMap[floor][currentPosition].nextX.add(nextPosition);
                }
                currentPosition = nextPosition;
            }
        }
        tempMap[15][3] = new MapNode(MapLocation.BOSS);
        //System.out.println(tempMap[15][3].mapLocation);
        return tempMap;
    }
}