package UI.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import UI.GUI;
import UI.MapNodeButton;
import UI.GUI.ScreenState;
import UI.Pane.BattlePane;
import UI.Pane.RewardPane;

public class MapNodeButtonListener implements ActionListener{
    MapNodeButton button;
    public MapNodeButtonListener(MapNodeButton button) {
        this.button = button;
        
    }

    public void actionPerformed(ActionEvent e) {
        RewardPane rewardPane = RewardPane.getInstance();

        switch(button.location) {
            case BOSS:
                rewardPane.setGoldReward(3);
                GUI.startBattle(button.floor,button.location);
                break;
            case ELITE:
                rewardPane.setGoldReward(2);
                GUI.startBattle(button.floor,button.location);
                break;
            case ENEMY:
                rewardPane.setGoldReward(1);
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
        button.node.visited = true;
    }
}
