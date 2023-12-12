package UI.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import Game.Game;
import Game.GameMap;
import UI.GUI;
import UI.MapNodeButton;
import UI.GUI.ScreenState;
import UI.Pane.RewardPane;
import Util.MapGenerator.MapLocation;

public class MapNodeButtonListener implements ActionListener {
    MapNodeButton button;

    public MapNodeButtonListener(MapNodeButton button) {
        this.button = button;
    }

    public void actionPerformed(ActionEvent e) {
        GameMap gameMap = Game.getInstance().gameMap;

        // 선택한 노드와 연결된 다음 노드 활성화
        for (int i = 0; i < button.node.nextX.size(); i++) {
            int nextX = button.node.nextX.get(i);
            gameMap.map[gameMap.currentFloor + 1][nextX].available = true;
        }

        RewardPane rewardPane = RewardPane.getInstance();

        switch (button.node.mapLocation) {
        case BOSS:
            rewardPane.setGoldReward(3);
            GUI.startBattle(button.floor, button.node.mapLocation);
            break;
        case ELITE:
            rewardPane.setGoldReward(2);
            GUI.startBattle(button.floor, button.node.mapLocation);
            break;
        case ENEMY:
            rewardPane.setGoldReward(1);
            GUI.startBattle(button.floor, button.node.mapLocation);
            break;
        case MERCHANT:
            GUI.changeScreen(ScreenState.SHOP);
            break;
        case REST:
            GUI.changeScreen(ScreenState.REST);
            break;
        /*case TREASURE:
            GUI.changeScreen(ScreenState.REST);
            break;*/
        case UNKNOWN:
            // 랜덤으로 ENEMY, ELITE, MERCHANT, REST 중 하나 선택
           String[] locations = {"ENEMY", "ELITE", "MERCHANT", "REST"};
            Random random = new Random();
            double randomValue = random.nextDouble(); // 0.0 이상 1.0 미만의 랜덤 실수

            if (randomValue < 0.5) { // 50% 확률
               rewardPane.setGoldReward(1);
                GUI.startBattle(button.floor, MapLocation.ENEMY);
            } else if (randomValue < 0.75) { // 25% 확률
               rewardPane.setGoldReward(2);
                GUI.startBattle(button.floor, MapLocation.ELITE);
            } else if (randomValue < 0.9) { // 15% 확률
                GUI.changeScreen(ScreenState.REST);
            } else { // 나머지 10% 확률
                GUI.changeScreen(ScreenState.SHOP);
            }
            break;
           }
        button.node.visited = true;
        Game.getInstance().gameMap.currentFloor++;
    }
}