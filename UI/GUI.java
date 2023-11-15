package UI;

import UI.Pane.*;

import javax.swing.*;
import Game.Player;

public class GUI{
    
    private Player player = Player.getInstance();

    private static MainFrame frame;
    private static BattlePane battlePane;
    private static JLayeredPane shopPane;
    private static MapPane mapPane;
    private static MainMenuPane mainMenuPane;

    public enum ScreenState {MAIN,BATTLE,SHOP,MAP}

    //public CardExhaustedPane exhaustedPane;

    public final boolean debugMode = false;

    public GUI() {
        initAllPanes();
        frame = new MainFrame();
        //frame.updatePane(battlePane);
        
        
    }

    public static void initAllPanes() {
        battlePane = new BattlePane();
        mainMenuPane = new MainMenuPane();
        mapPane = new MapPane();
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
            break;
            case MAIN:
            frame.updatePane(mainMenuPane);
            break;
        }
        frame.repaint();
    }



}
