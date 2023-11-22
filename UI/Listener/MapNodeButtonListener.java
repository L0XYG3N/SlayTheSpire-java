package UI.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import UI.GUI;
import UI.MapNodeButton;
import UI.GUI.ScreenState;
import UI.Pane.BattlePane;

public class MapNodeButtonListener implements ActionListener{
    MapNodeButton button;
    public MapNodeButtonListener(MapNodeButton button) {
        this.button = button;
        
    }

    public void actionPerformed(ActionEvent e) {
        switch(button.location) {
            case BOSS:
            case ELITE:
            case ENEMY:
            GUI.startBattle(button.floor,button.location);
                break;
            case MERCHANT:
                break;
            case REST:
                break;
            case TREASURE:
                break;
            case UNKNOWN:
                break;
        }
    }
}
