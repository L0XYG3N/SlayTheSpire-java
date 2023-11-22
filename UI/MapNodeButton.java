package UI;

import javax.swing.JButton;

import UI.Listener.MapNodeButtonListener;
import Util.MapGenerator.MapLocation;

public class MapNodeButton extends JButton{
    public int floor;
    public MapLocation location;


    public MapNodeButton(int floor, MapLocation loc) {
        this.floor = floor;
        location = loc;
        if(loc == MapLocation.ENEMY) {
            setText("적");
        }
        addActionListener(new MapNodeButtonListener(this));
    }
}
