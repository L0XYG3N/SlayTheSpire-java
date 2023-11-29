package UI;

import java.awt.Color;

import javax.swing.JButton;

import Game.Game;
import UI.Listener.MapNodeButtonListener;
import Util.MapNode;
import Util.MapGenerator.MapLocation;

public class MapNodeButton extends JButton{
    public int floor;
    public MapNode node;


    public MapNodeButton(int floor, MapNode node) {
        this.floor = floor;
        this.node = node;

        if(node.mapLocation == MapLocation.ENEMY) {
            setText("적");
        }

        if(node.visited)  {
            setBackground(Color.gray);
        }

        setEnabled(false);

        if (Game.getInstance().gameMap.currentFloor == floor && node.available) {
            setEnabled(true);
        }
        
        addActionListener(new MapNodeButtonListener(this));
        
        
    }
}
