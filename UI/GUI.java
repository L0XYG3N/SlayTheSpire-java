package UI;

import UI.Pane.*;
import Util.MapGenerator.MapLocation;

import javax.swing.*;

import Game.Field;

import java.util.Map;
import java.util.Random;

public class GUI{

   private static int randomID1, randomID2 ,randomID3, randomID4;
    private static MainFrame frame;
    private static BattlePane battlePane;
    private static ShopPane shopPane;
    private static MapPane mapPane;
    private static MainMenuPane mainMenuPane;
    private static RewardPane rewardPane;
    private static RestPane restPane;
    private static DeathPane deathPane; // 사망시 화면 추가 - 승훈

    public enum ScreenState {MAIN,BATTLE,SHOP,MAP,REWARD,REST,Death};

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
        deathPane = new DeathPane(); // 추가 - 스훈
    }

    public static void changeScreen(ScreenState s) {
        switch(s) {
            case BATTLE:
            frame.updatePane(battlePane);
            battlePane.revalidate();
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
            rewardPane.setRandomCardReward();
            break;
            case REST:
            frame.updatePane(restPane);
            break;
            case Death:
            frame.updatePane(deathPane);
            break;
        }
        frame.repaint();
    }

    public static void startBattle(int floor, MapLocation loc) {
        Random random = new Random();
        randomID1 = random.nextInt(5) + 1;  //easy ENEMY
        randomID2 = random.nextInt(10) + 1; //일반 ENEMY
        randomID3 = random.nextInt(1) + 1; //앨리트 ENEMY
        randomID4 = random.nextInt(2) + 1; //보스 BOSS
        switch(loc) {
            case ENEMY:
                if(floor == 0 || floor == 1 || floor == 2) {
                    Field.getInstance().initEasyStage(randomID1);
                    System.out.println("이지요");
                }
                else {
                    Field.getInstance().initStage(randomID2);
                    System.out.println("에너미요");
                }
                break;
            case ELITE:
                Field.getInstance().initEliteStage(randomID3);
                System.out.println("앨리트요");
                break;
            case BOSS:
                Field.getInstance().initBossStage(randomID4);
                System.out.println("보스요");
                break;
            case REST, TREASURE, MERCHANT, UNKNOWN:
                // 경고 제거용 더미 케이스, 이 4가지 케이스는 호출될일 없음
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

    public static void redrawMap() {
        mapPane.resetMap();
    }

}