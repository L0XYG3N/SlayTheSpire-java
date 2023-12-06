package UI.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Game.Game;
import Game.GameMap;
import UI.GUI;
import UI.MapNodeButton;
import UI.GUI.ScreenState;
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
                GUI.changeScreen(ScreenState.SHOP);
                break;
            case REST:
                GUI.changeScreen(ScreenState.REST);
                break;
            case TREASURE:
                GUI.changeScreen(ScreenState.REST);
                break;
            case UNKNOWN:
                GUI.changeScreen(ScreenState.REST);
                break;
        }
        button.node.visited = true;
        Game.getInstance().gameMap.currentFloor++;
    }
}
