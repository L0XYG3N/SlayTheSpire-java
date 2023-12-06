package Game;

import Util.MapGenerator;
import Util.MapNode;
import Util.MapGenerator.MapLocation;

public class GameMap {
    public MapNode [][] map;
    public int currentFloor;

    public GameMap() {
        currentFloor = 0;
        map = MapGenerator.generate();
    }
}
