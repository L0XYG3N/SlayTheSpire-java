package Game;

import Util.MapGenerator;
import Util.MapNode;

public class GameMap {
    public MapNode [][] map;
    public int currentFloor;

    public GameMap() {
        map = MapGenerator.generate();
        currentFloor = 0;
    }
}
