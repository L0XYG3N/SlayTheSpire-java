package UI;

import UI.Pane.*;
import Util.MapGenerator.MapLocation;

import javax.swing.*;

import Game.Field;

public class GUI{

    private static MainFrame frame;
    private static BattlePane battlePane;
    private static JLayeredPane shopPane;
    private static MapPane mapPane;
    private static MainMenuPane mainMenuPane;
    private static RewardPane rewardPane;

    public enum ScreenState {MAIN,BATTLE,SHOP,MAP,REWARD};

    //public CardExhaustedPane exhaustedPane;

    //public final boolean debugMode = false;

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
        /*
        shopPane = DefaultPaneGetter.getNewJLayeredPane();
        */
    }

    public static void changeScreen(ScreenState s) {
        switch(s) {
            case BATTLE:
            frame.updatePane(battlePane);
            break;
            case SHOP:
            frame.updatePane(shopPane);
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
        }
        frame.repaint();
    }

    public static void startBattle(int floor, MapLocation loc) {
        switch(loc) {
            case BOSS:
            case ENEMY:
            case ELITE:
            Field.getInstance().initStage(3);
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
