package UI.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Game.Game;
import Game.GameMap;
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
        GameMap gameMap = Game.getInstance().gameMap;

        // 선택한 노드와 연결된 다음 노드 활성화
        for(int i = 0; i < button.node.nextX.size();i++) {
            int nextX = button.node.nextX.get(i);
            gameMap.map[gameMap.currentFloor + 1][nextX].available = true;
        }

        RewardPane rewardPane = RewardPane.getInstance();

        switch(button.node.mapLocation) {
            case BOSS:
                rewardPane.setGoldReward(3);
                GUI.startBattle(button.floor,button.node.mapLocation);
                break;
            case ELITE:
                rewardPane.setGoldReward(2);
                GUI.startBattle(button.floor,button.node.mapLocation);
                break;
            case ENEMY:
                rewardPane.setGoldReward(1);
                GUI.startBattle(button.floor,button.node.mapLocation);
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
