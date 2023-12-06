package UI;

import UI.Pane.*;
import Util.MapGenerator.MapLocation;

import javax.swing.*;

import Game.Field;

public class GUI{

    private static MainFrame frame;
    private static BattlePane battlePane;
    private static ShopPane shopPane;
    private static MapPane mapPane;
    private static MainMenuPane mainMenuPane;
    private static RewardPane rewardPane;
    private static RestPane restPane;

    public enum ScreenState {MAIN,BATTLE,SHOP,MAP,REWARD,REST};

    public GUI() {
        initAllPanes();
        frame = new MainFrame();
        //frame.updatePane(battlePane);
        
        
    }

    public static void initAllPanes() {
        battlePane = BattlePane.getInstance();
        mainMenuPane = new MainMenuPane();
        mapPane = new MapPane();
        rewardPane = RewardPane.getInstance();
        shopPane = ShopPane.getInstance();
        restPane = RestPane.getInstance();
    }

    public static void changeScreen(ScreenState s) {
        switch(s) {
            case BATTLE:
            frame.updatePane(battlePane);
            break;
            case SHOP:
            frame.updatePane(shopPane);
            shopPane.updateGoldLabel();
            shopPane.displayCards();
            break;
            case MAP:
            frame.updatePane(mapPane);
            mapPane.drawingPanel.repaint();
            break;
            case MAIN:
            frame.updatePane(mainMenuPane);
            break;
            case REWARD:
            frame.updatePane(rewardPane);
            break;
            case REST:
            frame.updatePane(restPane);
            break;
        }
        frame.repaint();
    }

    public static void startBattle(int floor, MapLocation loc) {
        switch(loc) {
            case BOSS:
            case ENEMY:
            case ELITE:
            Field.getInstance().initStage(3);
            break;
            case REST, TREASURE, MERCHANT, UNKNOWN:
            //경고 제거용 더미 케이스, 이 4가지 케이스는 호출될일 없음
            return;
            

        }
        changeScreen(ScreenState.BATTLE);
        battlePane.initBattlePane();

    }

    public static void updateScreen() {
        battlePane.drawMonsters();
        //mainMenuPane.
        //battlePane.
        
    }

}
