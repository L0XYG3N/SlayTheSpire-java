package UI;

import javax.swing.JButton;

import UI.Listener.MapNodeButtonListener;
import Util.MapNode;
import Util.MapGenerator.MapLocation;

public class MapNodeButton extends JButton{
    public int floor;
    public MapLocation location;
    public MapNode node;


    public MapNodeButton(int floor, MapLocation loc, MapNode node) {
        this.floor = floor;

        location = loc;

        this.node = node;
        if(loc == MapLocation.ENEMY) {
            setText("적");
        }
        if(node.visited) setEnabled(false);

        
        addActionListener(new MapNodeButtonListener(this));
    }
}
