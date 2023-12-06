package UI;

import java.awt.Color;

import javax.swing.JButton;

import Game.Game;
import UI.Listener.MapNodeButtonListener;
import Util.MapNode;

public class MapNodeButton extends JButton{
    public int floor;
    public MapNode node;


    public MapNodeButton(int floor, MapNode node) {
        this.floor = floor;
        this.node = node;

        

        switch(node.mapLocation) {
            case ENEMY :
                setText("적");
                break;

            case BOSS :
                setText("보스");
                break;

            case ELITE :
                setText("엘리트");
                break;

            case MERCHANT :
                setText("상인");
                break;

            case REST :
                setText("휴식");
                break;

            case TREASURE :
                setText("상자");
                break;
            case UNKNOWN :

                setText("?");
                break;
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
