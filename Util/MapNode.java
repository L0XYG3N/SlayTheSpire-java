package Util;
import java.util.ArrayList;

public class MapNode {
	public ArrayList<Integer> nextX = new ArrayList<Integer>();
	public MapGenerator.MapLocation mapLocation;
	public boolean visited;
	public boolean available;

	public MapNode(MapGenerator.MapLocation mapLocation) {
		visited = false;
		available = false;
		this.mapLocation = mapLocation;
	}
}
